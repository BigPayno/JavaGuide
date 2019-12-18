package spring.web;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

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
     * 构建请求的实现，HttpComponents，OkHttp等
     */
    public void restFactory(){
        ClientHttpRequestFactory factory=new HttpComponentsClientHttpRequestFactory();
        RestTemplate template=new RestTemplate(factory);
        /*template.setRequestFactory();
        template.setMessageConverters();
        template.setErrorHandler();
        template.setInterceptors();*/
    }
}
