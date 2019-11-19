package jdkguide.stream;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import guava.time.StopWatchRunnable;
import jdk.management.resource.internal.inst.InitInstrumentation;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author payno
 * @date 2019/11/19 11:04
 * @description
 */
public class ParallelStreamGuide {
    @Test
    public void intStreamGuide(){
        IntStream.iterate(0,num->num+5).limit(10).forEach(System.out::println);
    }

    /**
     * 网上说流的并发操作不安全
     * 我真的服了，多线程肯定不安全
     * 不安全的不是并发，是list不安全
     * 换而言之，并发操作必须要保证容器是线程安全的
     */
    @Test
    public void parallelStreamTest(){
        List<Integer> list1=new ArrayList<>();
        List<Integer> list2=new ArrayList<>();
        List<Integer> list3=new CopyOnWriteArrayList<>();
        IntStream.range(1,10000).forEach(list1::add);
        IntStream.range(1,10000).parallel().forEach(list2::add);
        IntStream.range(1,10000).parallel().forEach(list3::add);
        System.out.println(list1.size());
        System.out.println(list2.size());
        System.out.println(list3.size());
    }

    /**
     * 那么怎么保证线程安全呢
     * 我在存储过，用的是Guava Lists.part
     */
    @Test
    public void parallelStreamTest2(){
        List<Integer> list=IntStream.range(1,100000).boxed().collect(Collectors.toList());
        Set<Integer> set=list.parallelStream().collect(Collectors.toSet());
        System.out.println(set.size());

        Set<Integer> set1=new HashSet<>();
        /**
         * 是容器不安全的原因
         */
        list.parallelStream().forEach(set1::add);
        System.out.println(set1.size());

        /**
         * 即使提供流的是不可变类型，线程安全的也会出现错误，
         * 本来并发流之间就是没有互相影响的，只是这些多线程在进行添加元素到集合里，新的集合是线程不安全的
         */
        Set<Integer> set2= ImmutableSet.copyOf(list.parallelStream().collect(Collectors.toSet()));
        Set<Integer> set3=new HashSet<>();
        set2.parallelStream().forEach(set3::add);
        System.out.println(set3.size());

        /**
         * 来个线程安全的容器试一试
         */
        Set<Integer> set4=new CopyOnWriteArraySet<>();
        list.parallelStream().forEach(set4::add);
        System.out.println(set4.size());
    }

    @Test
    public void parallelStreamTest3(){
        Set<Integer> set=IntStream.range(1,100000).boxed().collect(Collectors.toSet());
        /**
         * 测试下几种效率
         */
        StopWatchRunnable.time("single collect",()->{
            Set<Integer> set1=set.stream().collect(Collectors.toSet());
            System.out.println(set1.size());
        });

        StopWatchRunnable.time("parallel stream",()->{
            Set<Integer> set1=set.parallelStream().collect(Collectors.toSet());
            System.out.println(set1.size());
        });

        StopWatchRunnable.time("parallel stream with copyOnWrite collection",()->{
            Set<Integer> set1=new CopyOnWriteArraySet<>();
            set.parallelStream().forEach(set1::add);
            System.out.println(set1.size());
        } );

        StopWatchRunnable.time("parallel stream with sync collection",()->{
            Set<Integer> set1= Collections.synchronizedSet(new HashSet<>(100000));
            set.parallelStream().forEach(set1::add);
            System.out.println(set1.size());
        });
    }
}
