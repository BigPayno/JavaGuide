package spring.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import utils.collection.Maps;

import java.net.URI;
import java.util.Collections;

/**
 * @author payno
 * @date 2019/11/16 21:12
 * @description
 */
public class RestTemplateGuide {
    public static void main(String[] args) {
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.getForEntity("http:www.baidu.com/{1}",String.class,"32072319961193816");
        restTemplate.getForEntity("http://locolhost:8000/query",String.class, Collections.emptyMap());
        restTemplate.getForObject("hello",String.class);
        HttpStatus status=restTemplate.getForEntity("http://locolhost:8000/query",String.class, Collections.emptyMap())
                .getStatusCode();
        if(status.is2xxSuccessful()){
            //sth happened
        }
    }
}
