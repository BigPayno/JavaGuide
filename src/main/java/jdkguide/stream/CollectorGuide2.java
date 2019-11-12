package jdkguide.stream;

import com.google.common.base.Splitter;
import com.google.common.collect.*;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author payno
 * @date 2019/11/6 10:05
 * @description
 */
public class CollectorGuide2 {
    public void supplier(){
        Supplier<List> listSupplier= ArrayList::new;
        Supplier<Multiset> setSupplier= HashMultiset::create;
        Supplier<StringBuffer> stringBufferSupplier=StringBuffer::new;
    }

    @Test
    public void accumulator(){
        Collector<String,?,List<String>> listCollector=Collector.of(
                ArrayList::new,
                List::add,
                (r1,r2)->{r1.addAll(r2);return r1;});
        List<String> list=Stream.of("a","b").collect(listCollector);
        list.forEach(System.out::println);
    }

    public class FixedCollector<T> implements Collector<T,List<T>,List<List<T>>>{
        private int fixedLength;
        public FixedCollector(int fixedLength){
            this.fixedLength=fixedLength;
        }
        @Override
        public Supplier<List<T>> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<T>, T> accumulator() {
            return (list,item)->{
              list.add(item);
            };
        }

        @Override
        public BinaryOperator<List<T>> combiner() {
            return (list1,list2)->{
                list1.addAll(list2);
                return list1;
            };
        }

        @Override
        public Function<List<T>, List<List<T>>> finisher() {
            return list -> {
              return Lists.partition(list,fixedLength);
            };
        }

        @Override
        public Set<Characteristics> characteristics() {
            return ImmutableSet.of(Characteristics.IDENTITY_FINISH);
        }
    }

    @Test
    public void fixedGroupBy(){
        Set<String> stringSet= ImmutableSet.copyOf(
                Splitter.fixedLength(1).split("payno")
        );
    }
}
