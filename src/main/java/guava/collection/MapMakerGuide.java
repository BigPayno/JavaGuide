package guava.collection;

import com.google.common.collect.MapMaker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author payno
 * @date 2019/10/29 11:31
 * @description
 */
public class MapMakerGuide {
    public static void main(String[] args) {
        ConcurrentMap<Integer,String> concurrentMap=new MapMaker()
                .concurrencyLevel(2)
                .initialCapacity(20)
                .weakKeys()
                .weakValues()
                .makeMap();
    }
}
