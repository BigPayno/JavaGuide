package jdkguide.stream;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

/**
 * @author payno
 * @date 2019/9/20 10:05
 * @description
 */
public class ReduceGuide {
    private static final List<Integer> NUM_LIST= ImmutableList.copyOf(new Integer[]{1,5,2,3});
    @Test
    public void max(){
        NUM_LIST.stream()
                .reduce((a,b)->a>b?a:b)
                .ifPresent(System.out::println);
        NUM_LIST.stream()
                .max(Comparator.naturalOrder())
                .ifPresent(System.out::println);
    }
    @Test
    public void sum(){
        NUM_LIST.stream()
                .reduce((a,b)->a+b)
                .ifPresent(System.out::println);
    }
}
