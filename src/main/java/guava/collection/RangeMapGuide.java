package guava.collection;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

/**
 * @author payno
 * @date 2019/10/29 11:34
 * @description
 */
public class RangeMapGuide {
    public static void main(String[] args) {
        RangeMap<Integer,Integer> rangeMap= TreeRangeMap.create();
        rangeMap.put(Range.closed(1,3),3);
        System.out.println(rangeMap.get(2));
        System.out.println(rangeMap.get(3));
        rangeMap.asMapOfRanges().keySet().forEach(System.out::println);
    }
}
