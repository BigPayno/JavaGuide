package utils.collection;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author payno
 * @date 2019/7/10 10:20
 * @description 对jsonArray转换为List
 */
public class Lists {
    /**
     * @param jsonArray
     * @param clazz     不可以是String这种无法转换为JSONObject<k,v>的类
     * @author: payno
     * @time: 2019/7/10 10:50
     * @description: 通过JsonArray得到List
     * @return: java.util.List<T>
     */
    public static <T> List<T> toList(JSONArray jsonArray, Class<T> clazz) {
        List<T> list = new ArrayList<T>(jsonArray.size());
        jsonArray.forEach(toListConsumer(o -> {
            JSONObject jo = (JSONObject) o;
            T t = jo.toJavaObject(clazz);
            return t;
        }, list));
        return list;
    }

    public static List<String> toList(JSONArray jsonArray) {
        List<String> list = new ArrayList<String>(jsonArray.size());
        for (int i = 0; i < jsonArray.size(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }

    /**
     * @param toJavaObject
     * @param list
     * @author: payno
     * @time: 2019/7/10 10:49
     * @description: toList中用到的Consumer
     * @return: java.util.function.Consumer<T>
     */
    public static <T, R> Consumer<T> toListConsumer(Function<Object, R> toJavaObject, List<R> list) {
        return t -> {
            R r = toJavaObject.apply(t);
            list.add(r);
        };
    }
}
