package fastjson;

import com.alibaba.fastjson.JSONPath;

/**
 * @author payno
 * @date 2019/9/6 08:29
 * @description
 */
public class JSONPathGuide {
    private static final String TEST_TXT="";
    private static final Object ROOT_OBJ=new Object();
    private static void path(){
        JSONPath path=JSONPath.compile("$");
        path.contains(ROOT_OBJ);
        path.containsValue(ROOT_OBJ,"ABC");
        path.eval(ROOT_OBJ);
        path.remove(ROOT_OBJ);
        path.size(ROOT_OBJ);
        path.set(ROOT_OBJ,"ABC");
    }
    public static void main(String[] args) {

    }
}
