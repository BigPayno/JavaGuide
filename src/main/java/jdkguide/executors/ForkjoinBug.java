package jdkguide.executors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import jdkguide.thread.Threads;
import lombok.extern.slf4j.Slf4j;

/**
 * @author payno
 * @date 2020/3/5 14:41
 * @description
 */
@Slf4j
public class ForkjoinBug {
    public static void main(String[] args) {
        int i=1000;
        while(i>0){
            ImmutableList.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15).parallelStream().forEachOrdered(character -> {
                //System.out.printf("Thread[%s]:%s%n",Thread.currentThread().getName(),character)
                if(Thread.currentThread().getName().equals("main")){
                    System.out.println("wrong!!!!!!!");
                }
                log.info("{}",character);
            });
            i--;
        }
    }
}
