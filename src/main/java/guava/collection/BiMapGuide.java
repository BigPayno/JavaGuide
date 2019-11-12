package guava.collection;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * @author payno
 * @date 2019/10/29 11:31
 * @description
 */
public class BiMapGuide {
    public static void main(String[] args) {
        BiMap<Integer,String> biMap= HashBiMap.create();
        biMap.put(1,"Monday");
        biMap.get(1);
        biMap.inverse().get("Monday");
    }
}
