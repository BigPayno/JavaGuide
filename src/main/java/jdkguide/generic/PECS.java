package jdkguide.generic;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author payno
 * @date 2020/5/29 16:11
 * @description
 */
public class PECS {
    /**
     *      List<? extends 水果> list
     *          list可能是 List<苹果>，也可以是 List<香蕉>
     *              如果你放入一个水果(苹果)，但 list不是 List<苹果>，就有问题
     *          由于？的下界并不存在，所以无论放入任何元素都可能出问题
     *              唯一能保证的取出来的是水果，这个list是个生产者，里面的元素可以被消费
     *      相似的是，List<? super 水果>，可以存任何水果的子类及水果本身
     *          因为List<水果>是可以放香蕉、苹果的
     *      P extends C super
     *
     *      如果需要既做生产者，又作消费者，推荐T不使用Extends、Super
     *      或者使用Collection.copy()
     */

    @Test
    public void producer(){
        List<? extends Number> numbers= Lists.newArrayList(Integer.valueOf(1),2);
        List<? extends Number> numbers2= new ArrayList<Long>();
        //numbers.add(Long.valueOf(1)); 非法
        numbers.stream().map(num->((Number) num).intValue()).forEach(System.out::println);
    }

    @Test
    public void consumer(){
        List<? super Number> ints = Lists.newArrayList(Integer.valueOf(1),2);
        ints.add(Integer.valueOf(1));
        ints.add(Long.valueOf(2));
        Object val = ints.get(1);
        ints.stream()
                .filter(item->((Object) item).getClass().isAssignableFrom(Integer.class))
                .map(item->(Number)item)
                .forEach(System.out::println);
    }

    @Test
    public void exchange(){
        List<? extends Number> source= Lists.newArrayList(Integer.valueOf(1),2);
        List<? super Number> sink=Lists.newArrayList(2,2);
        Collections.copy(sink,source);
        sink.forEach(System.out::println);
    }
}
