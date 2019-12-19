package spring.web;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.*;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

/**
 * @author payno
 * @date 2019/12/18 11:33
 * @description
 *      今天下午就看这个了
 *          HttpMessageConverter，多么熟悉的Mvc组件
 *          很好奇，RestTemplate->Mvc->Cloud
 *
 */
public class RestTemplateSupport {

    /**
     * SpringRestTemplate是个SPI,具体的实现根据工厂决定
     */
    @Test
    public void restFactory(){
        ClientHttpRequestFactory factory=new HttpComponentsClientHttpRequestFactory();
        RestTemplate template=new RestTemplate(factory);
    }

    @Test
    public void direct(){
        SimpleClientHttpRequestFactory factory=new SimpleClientHttpRequestFactory();
        /**
         * 减少中间的内存消耗，使流流向底层，不推荐这样传大文件
         */
        factory.setBufferRequestBody(false);
        RestTemplate restTemplate=new RestTemplate();

    }

    @Test
    public void sourceStream(){
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<byte[]> responseEntity=
                restTemplate.postForEntity("http://localhost:8080/body/stream",null,byte[].class);
        System.out.println(new String(responseEntity.getBody()));
    }

    @Test
    public void sinkStream(){
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.setInterceptors(Lists.newArrayList(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution execution) throws IOException {
                httpRequest.getHeaders().set("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
                httpRequest.getHeaders().set("Accept",MediaType.TEXT_HTML_VALUE);
                return execution.execute(httpRequest,bytes);
            }
        }));
        ResponseEntity<byte[]> responseEntity=
                restTemplate.postForEntity("http://localhost:8080/body/stream/upload","hello".getBytes(),byte[].class);
    }


}
