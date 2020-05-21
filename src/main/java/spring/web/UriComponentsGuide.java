package spring.web;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Base64;
import java.util.Collections;

/**
 * @author payno
 * @date 2019/11/16 21:16
 * @description
 */
public class UriComponentsGuide {
    private static class EasyUri{
        public static void main(String[] args) {
            UriComponents uriComponents=UriComponentsBuilder.newInstance()
                    .scheme("http").host("www.baidu.com").path("/junit-5").build();
            System.out.println(uriComponents.toString());
            System.out.println(uriComponents.toUriString());
        }
    }

    private static class EncodeUri{
        public static void main(String[] args) {
            UriComponents uriComponents = UriComponentsBuilder.newInstance()
                    .scheme("http").host("www.baeldung.com").path("/junit 5").build().encode();
            System.out.println(uriComponents.toUriString());
        }
    }

    private static class TemplateUri{
        /**
         * 更优雅的构建get请求
         */
        public static void main(String[] args) {
            UriComponents uriComponents = UriComponentsBuilder.fromUriString("http:/www.baidu.com/junit5")
                    .query("a={keyword}").buildAndExpand("search");
            System.out.println(uriComponents);
            UriComponents uriComponents2 = UriComponentsBuilder.fromUriString("http:/www.baidu.com/junit5")
                    .queryParam("a","1","2").queryParam("b","13").build();
            System.out.println(uriComponents2);
            MultiValueMap<String,String> map=new LinkedMultiValueMap<>();
            map.add("a","11");
            map.add("b","a");
            System.out.println(
                    UriComponentsBuilder.fromUriString("/")
                    .queryParams(map).build()
            );
        }
    }

    private static class RegexUri{
        public static void main(String[] args) {
            String template = "/myurl/{name:[a-z]{1,5}}/show";
            UriComponents uriComponents = UriComponentsBuilder.fromUriString(template)
                    .build();
            uriComponents = uriComponents.expand(Collections.singletonMap("name", "test"));
            System.out.println(uriComponents);
        }
    }

    private static class ExpendUri{
        public static void main(String[] args) {
            UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://Server-Provider/user/{id}")
                    .build().expand("123").encode();
            System.out.println(uriComponents);
        }
    }

    @Test
    public void test1(){
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.setInterceptors(Lists.newArrayList((ClientHttpRequestInterceptor) (httpRequest, bytes, execution)->{
            httpRequest.getHeaders().set("authorization",String.format("%s %s", "Bearer","b69b99f7-efc2-4054-bc43-591f61aef321"));
            return execution.execute(httpRequest,bytes);
        }));
        String obj = restTemplate.postForObject(
                "http://localhost:8081/api/hello", null,String.class);
        System.out.println(obj);
    }

    public RestTemplate rest(String user,String secret){
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.setInterceptors(Lists.newArrayList((ClientHttpRequestInterceptor) (httpRequest, bytes, execution)->{
            httpRequest.getHeaders().setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            httpRequest.getHeaders().set(
                    "Authorization",
                    "Basic "+Base64.getEncoder().encodeToString(
                            String.format("%s:%s",user,secret).getBytes()
                    )
            );
            return execution.execute(httpRequest,bytes);
        }));
        return restTemplate;
    }

    @Test
    public void test2(){
        JSONObject request = new JSONObject();
        request.put("token","aaaaaaaa");
        Object obj = rest("app1","112233").postForObject(
                "http://localhost:8080/oauth/check_token", request,Object.class);
        System.out.println(obj);
    }

    @Test
    public void test3(){
        Object obj = rest("app1","112233").postForObject(
                "http://localhost:8080/oauth/token", null,Object.class);
        System.out.println(obj);
    }
}
