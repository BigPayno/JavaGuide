package jsonpath;

import com.google.common.io.Resources;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import static com.jayway.jsonpath.Criteria.where;

/**
 * @author payno
 * @date 2020/5/7 11:46
 * @description
 *      是时候投入jsonpath+Jackson/gson的怀抱了
 *      垃圾fastjson
 */
public class JsonPredicatesGuide {

    String json;

    @Before
    public void init(){
        try{
            json = Resources.asCharSource(
                    Resources.getResource("jsonpath.json"), Charset.defaultCharset()).read();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void inline(){
        List<Map<String, Object>> books =  JsonPath.parse(json)
                .read("$.store.book[?(@.price < 10)]");
        System.out.println(books);
    }

    @Test
    public void predicate(){
        Predicate predicate = predicateContext -> {
            //  太舒适了 想怎样就怎样
            return predicateContext.item(Map.class).containsKey("isbn");
        };
        List<String> books =
                JsonPath.parse(json).read("$.store.book[?].isbn", List.class, predicate);
        System.out.println(books);
    }

    @Test
    public void filter(){
        //  神仙语法
        Filter cheapFictionFilter = Filter.filter(
                where("category").is("fiction").and("price").lte(10D));
        List<Map<String, Object>> books =
                JsonPath.parse(json).read("$.store.book[?]", cheapFictionFilter);
        System.out.println(books);
    }
}
