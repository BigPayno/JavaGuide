package guava.collection;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author payno
 * @date 2019/10/29 10:24
 * @description
 */
public class SetsGuide {
    public static void main(String[] args) {
        Set<String> colors= ImmutableSet.copyOf(new String[]{"red","yellow","blue"});
        Set<String> animals=ImmutableSet.copyOf(new String[]{"lion","tiger"});

        //笛卡尔积
        Sets.cartesianProduct(colors,animals).forEach(list->{list.forEach(System.out::printf); System.out.println();});
        System.out.println();
        //子集
        Sets.powerSet(animals).forEach(list->{list.forEach(System.out::printf); System.out.println();});
        System.out.println();
        //差集
        Sets.difference(colors,animals).forEach(System.out::println);
        System.out.println();
        //并集
        Sets.union(colors,animals).forEach(System.out::println);
        System.out.println();
    }
}
