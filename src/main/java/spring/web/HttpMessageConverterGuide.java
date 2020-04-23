package spring.web;

import com.google.common.collect.Lists;
import com.google.common.io.ByteStreams;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.*;
import org.springframework.web.client.RestTemplate;
import utils.charset.Charsets;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Collections;

/**
 * @author payno
 * @date 2019/12/18 15:01
 * @description
 *      在开发REST服务时，不可避免的需要了解HTTP协议的内容，其中，我们经常会用到 Accept 与 Content-Type，那么这两者有什么区别和联系呢？
 * 1. 类型不同
 *
 * 类型不同Accept属于请求头， Content-Type属于实体头。
 *
 * Http报头分为通用报头，请求报头，响应报头和实体报头。
 *
 *     请求方的HTTP报头结构：通用报头|请求报头|实体报头
 *     响应方的HTTP报头结构：通用报头|响应报头|实体报头
 *
 * 2. 作用不同
 *      Accept代表发送端（客户端）希望接受的数据类型。
 *  比如：Accept：text/xml; 代表客户端希望接受的数据类型是xml类型。
 *      Content-Type代表发送端（客户端|服务器）发送的实体数据的数据类型。
 * 比如：Content-Type：text/html; 代表发送端发送的数据格式是html。
 *      二者合起来， Accept:text/xml； Content-Type:text/html ，
 *      即代表希望接受的数据类型是xml格式，本次请求发送的数据的数据格式是html。
 *
 */
public class HttpMessageConverterGuide {
    public class StringPaynoMediaConverter extends AbstractHttpMessageConverter<String>{
        /**
         * 支持什么类型的MediaType
         */
        protected StringPaynoMediaConverter() {
            super(new MediaType("application", "payno", Charsets.UTF_8));
        }

        /**
         * 支持什么类型的Java数据解析
         */
        @Override
        protected boolean supports(Class<?> aClass) {
            return aClass.isAssignableFrom(String.class);
        }

        /**
         * 读取body的流->String
         */
        @Override
        protected String readInternal(Class<? extends String> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
            logger.debug("read Response!!!");
            return new String(ByteStreams.toByteArray(httpInputMessage.getBody()));
        }

        /**
         * String->body
         */
        @Override
        protected void writeInternal(String s, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
            char[] chars=s.toCharArray();
            for(int i=0;i<chars.length;i++){
                if(chars[i]<= '9'&&chars[i]>='0'){
                    chars[i]='*';
                }
            }
            ByteBuffer byteBuffer=Charsets.UTF_8.newEncoder().encode(CharBuffer.wrap(chars));
            httpOutputMessage.getBody().write(byteBuffer.array());
        }
    }

    @Test
    public void customSupport(){
        RestTemplate restTemplate=new RestTemplate();
        /**
         * 在请求发送出去前进行拦截
         */
        restTemplate.setInterceptors(Collections.singletonList(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution execution) throws IOException {
                if(httpRequest.getURI().toString().endsWith("payno")) {
                    httpRequest.getHeaders().set("Content-Type", "application/payno");
                    httpRequest.getHeaders().set("Accept","text/plain;charset=ISO-8859-1");
                }
                return execution.execute(httpRequest,bytes);
            }
        }));
        /**
         * 处理Request与Response，需要支持对应的Media与Class,能够发送和接收的类型
         * 注意顺序，判断是否支持在于class，尽量分开吧
         * 1.写Request时，按照class进行判断合适的Converter，第一个运行的Converter会决定Content-Type
         * 2.读取Response时，优先按照请求头判断
         *
         */
        restTemplate.setMessageConverters(
                Lists.newArrayList(new StringPaynoMediaConverter(),new StringHttpMessageConverter()));
        ResponseEntity<String> result=restTemplate.postForEntity("http://localhost:8080/body/payno","123",String.class);
        System.out.println(result.getBody());

        restTemplate.setMessageConverters(
                Lists.newArrayList(new StringHttpMessageConverter()));
        ResponseEntity<String> result2=restTemplate.postForEntity("http://localhost:8080/body/text","123",String.class);
        System.out.println(result2.getBody());
    }

    /**
     * 基于Xml的RestTemplate
     */
    @Test
    public void xml(){
        RestTemplate template=new RestTemplate();
        template.setInterceptors(Collections.singletonList(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution execution) throws IOException {
                httpRequest.getHeaders().set("Content-Type", "application/xml");
                httpRequest.getHeaders().set("Accept","application/xml");
                return execution.execute(httpRequest,bytes);
            }
        }));
        String source="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Service><Head><serviceId>Nz001</serviceId><serviceName>Nz_Risk</serviceName></Head><Body><request><cityNm>gz</cityNm></request></Body></Service>";
        ResponseEntity<String> response=template.postForEntity("http://localhost:8080/esb/request",source,String.class);
        System.out.println(response.getBody());
    }
}
