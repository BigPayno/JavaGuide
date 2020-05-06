package utils.json;

import com.alibaba.fastjson.JSONPath;

/**
 * @author payno
 * @date 2020/5/6 15:05
 * @description
 */
public final class JSON {
    private JSON(){

    }

    public static ObjWrapper read(String json,String path){
        return ObjWrapper.wrap(
                JSONPath.read(json,path));
    }

    public static ObjWrapper eval(Object json,String path){
        return ObjWrapper.wrap(
                JSONPath.eval(json, path));
    }
}
