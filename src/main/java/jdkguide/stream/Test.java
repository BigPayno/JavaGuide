package jdkguide.stream;

import com.google.common.collect.ImmutableList;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author payno
 * @date 2019/10/16 15:31
 * @description
 */
public class Test {
    public static class Nums{
        public static Predicate<Integer> biggerThan(int limit){
            return v->{
                return (v>limit);
            };
        }
    }
    public static void main(String[] args) {

    }
}
