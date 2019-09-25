package jdkguide.collections.util;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author payno
 * @date 2019/9/11 19:10
 * @description
 */
public class HashSetGuide {
    public static void main(String[] args) {
        int[] nums=new int[]{4,4,66,3,2,65,8};
        Set<Integer> set= new HashSet<>(60);
        for(int i:nums){
            set.add(i);
        }
        set.forEach(System.out::println);
        Arrays.stream(nums).distinct().sorted().forEach(System.out::println);
        Arrays.stream(nums).distinct().sorted().boxed().collect(Collectors.toList());
    }
}
