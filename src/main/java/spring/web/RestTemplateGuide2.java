package spring.web;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

/**
 * @author payno
 * @date 2020/4/24 16:15
 * @description
 */
public class RestTemplateGuide2 {

    @AllArgsConstructor
    static class RiskTokenInterceptor implements ClientHttpRequestInterceptor{
        RestTemplate restTemplate;
        static final String JWT_HEADER= "Authorization";
        static final String SIGN_UP_URL= "http://localhost:9527/account/sign-in";

        @Override
        public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
            if(!httpRequest.getURI().toString().equals(SIGN_UP_URL)){
                JSONObject userDto = new JSONObject();
                userDto.put("username","payno");
                userDto.put("password","payno1234");
                String jwtToken = JSONObject.parseObject(
                        restTemplate.postForObject(URI.create(SIGN_UP_URL),userDto,String.class))
                        .getString("data");
                httpRequest.getHeaders().set(JWT_HEADER,jwtToken);
            }
            return clientHttpRequestExecution.execute(httpRequest,bytes);
        }
    }

    @Test
    public void exchange(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Lists.newArrayList(new RiskTokenInterceptor(restTemplate)));
        ResponseEntity<String> res = restTemplate.postForEntity("http://localhost:9527/scene/all",null,String.class);
        System.out.println(res.getBody());
    }
}
