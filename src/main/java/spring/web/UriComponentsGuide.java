package spring.web;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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
}
