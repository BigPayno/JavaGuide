package jdkguide.generic;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Function;

/**
 * @author payno
 * @date 2019/12/31 16:36
 * @description
 */
public class SuperAndExtendGuide2 {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Container<T>{
        T t;
        private void consume(){
            System.out.println(t);
        }
    }

    public void produce(Container<? extends Number> container){
        /**
         * 只能确定容器内的对象是extends Number的
         * 可能是List<Integer>或者List<Long>,如果放错了会产生问题
         * 因此只允许读取，容器作为生产者，可以被消费
         * 协变
         */
        Number number=container.getT();
        System.out.println(number);
    }

    public void consumer(Container<? super Number> container){
        /**
         * 容器作为消费者，可以生产对象到容器
         * 逆变
         */
        container.setT(Integer.valueOf(2));
    }

    public void consume(Container<? super Number> container){
        container.setT(Integer.valueOf(11));
        container.consume();
    }
}
