package guava.order;

import com.google.common.collect.Comparators;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author payno
 * @date 2019/10/30 09:05
 * @description
 */
public class OrderGuide {
    public static void main(String[] args) {
        List<String> list= ImmutableList.copyOf(new String[]{"liam","shadoll","payno","1314","9527"});
        Ordering<String> naturalOrder=Ordering.natural();
        Ordering<Object> toStringNaturalOrder=Ordering.usingToString();
        Ordering<Object> arbitraryOrder=Ordering.arbitrary();

        System.out.println("max of a and 1 :"+naturalOrder.max("a","1"));
        System.out.println(toStringNaturalOrder.immutableSortedCopy(list));
        System.out.println(arbitraryOrder.compare("a","1"));
        list.stream().sorted(Ordering.natural()).reduce((a,b)->a).ifPresent(System.out::println);

        List<People> peopleList= ImmutableList.copyOf(new People[]{
                new People("male","001","payno"),
                new People("male","002","liam"),
                new People("female","003","swift"),
                new People("male","004","dell")
        });
        Ordering<People> ordering=Ordering.from(Comparator.comparing(People::getSex))
                .compound(Comparator.comparing(People::getId));
        System.out.println(ordering.sortedCopy(peopleList));

        List<Integer> integers= Ints.asList(1,2,4,5);
        List<Integer> integers1= Arrays.asList(1,2,null);
        System.out.println(Ordering.natural().nullsFirst().sortedCopy(integers));
        System.out.println(Ordering.natural().nullsFirst().sortedCopy(integers1));
    }
}
