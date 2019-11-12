package jdkguide.thread.atomic;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author payno
 * @date 2019/11/5 09:04
 * @description
 */
@Getter
public class Provider {
    private Integer count=0;
    private Integer count1=0;
    private AtomicInteger count2=new AtomicInteger();
    private volatile int count3=0;
    public void addCount(){
        count++;
    }
    public synchronized void addCount1(){
        count1++;
    }
    public void addCount2(){
        count2.incrementAndGet();
    }
    public void addCount3(){
        AtomicIntegerFieldUpdater.newUpdater(Provider.class,"count3").incrementAndGet(this);
    }
}
