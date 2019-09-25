package jparser;

import com.alibaba.fastjson.JSONPath;

/**
 * @author payno
 * @date 2019/9/6 08:25
 * @description
 */
public class DefaultReader implements PathReader{
    @Override
    public Object read(String text, String jsonPath) {
        return JSONPath.read(text, jsonPath);
    }
}
