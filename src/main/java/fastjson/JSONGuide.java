package fastjson;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @author payno
 * @date 2019/9/20 16:34
 * @description
 */
public class JSONGuide {
    private static final String JSON="{\"a\":\"b\"}";
    @Test
    public void test(){
        JSONObject.class.cast(JSONObject.parse(JSON));
    }
}
