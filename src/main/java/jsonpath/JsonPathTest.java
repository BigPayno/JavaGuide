package jsonpath;

import com.google.common.io.Resources;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author payno
 * @date 2020/5/7 09:15
 * @description
 *  Operator                    Description
 *  $ 	                        The root element to query. This starts all path expressions.
 *  @ 	                        The current node being processed by a filter predicate.
 *  * 	                        Wildcard. Available anywhere a name or numeric are required.
 *  .. 	                        Deep scan. Available anywhere a name is required.
 *   .<name> 	                Dot-notated child
 *  ['<name>' (, '<name>')] 	Bracket-notated child or children
 *  [<number> (, <number>)] 	Array index or indexes
 *  [start:end] 	            Array slice operator
 *  [?(<expression>)] 	        Filter expression. Expression must evaluate to a boolean value.
 *
 *  Function 	        Description 	                                                Output
 *  min() 	            Provides the min value of an array of numbers 	                Double
 *  max() 	            Provides the max value of an array of numbers 	                Double
 *  avg() 	            Provides the average value of an array of numbers 	            Double
 *  stddev() 	        Provides the standard deviation value of an array of numbers 	Double
 *  length() 	        Provides the length of an array 	                            Integer
 *  sum()            	Provides the sum value of an array of numbers 	                Double
 *
 *  Operator 	Description
 *  == 	        left is equal to right (note that 1 is not equal to '1')
 *  != 	        left is not equal to right
 *  < 	        left is less than right
 *  <= 	        left is less or equal to right
 *  > 	        left is greater than right
 *  >= 	        left is greater than or equal to right
 *  =~ 	        left matches regular expression [?(@.name =~ /foo.*?/i)]
 *  in 	        left exists in right [?(@.size in ['S', 'M'])]
 *  nin 	    left does not exists in right
 *  subsetof 	left is a subset of right [?(@.sizes subsetof ['S', 'M', 'L'])]
 *  anyof 	    left has an intersection with right [?(@.sizes anyof ['M', 'L'])]
 *  noneof 	    left has no intersection with right [?(@.sizes noneof ['M', 'L'])]
 *  size 	    size of left (array or string) should match right
 *  empty 	    left (array or string) should be empty
 */
@Slf4j
public class JsonPathTest {

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

    private void log(Object... objs){
        for(Object obj:objs){
            log.info("obj=[{}],class=[{}]",obj,obj.getClass());
        }
    }

    @Test
    public void read(){
        //  如果只是log JSONArray会报错 可变参数？？？？
        Object parseJson=Configuration.defaultConfiguration().jsonProvider().parse(json);
        TypeRef<List<String>> typeRef = new TypeRef<List<String>>() {};
        log(
                JsonPath.read(json,"$.store.book[*].author"),
                parseJson,
                JsonPath.read(parseJson,"$..book[?(@.isbn)]"),
                JsonPath.parse(json),
                JsonPath.parse(json).read("$.store.book[?(@.price > 10)]", List.class),
                JsonPath.parse(json).read("$.store.book[*].title", typeRef)
        );
        //  泛型信息 需要Jackson or Gson based provider
        List<String> titles = JsonPath.parse(json).read("$.store.book[*].title", typeRef);
    }
}
