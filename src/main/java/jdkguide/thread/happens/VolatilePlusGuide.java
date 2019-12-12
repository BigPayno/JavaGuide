package jdkguide.thread.happens;

import jdkguide.thread.Threads;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author payno
 * @date 2019/12/12 10:50
 * @description
 *      volatile借助Hb原则，实现半同步
 *      volatile更新时会更新线程栈的其他引用（？不确定）和基础类型数据
 *      大概基于热点缓存更新吧
 *
 */
public class VolatilePlusGuide {
    volatile boolean sem=false;
    int x=1;
    public void write(){
        x=2;
        sem=true;
    }
    public int read(){
        Threads.sleep(100);
        if(sem){
            return x;
        }
        return 0;
    }
    @Test
    public void test(){
        AtomicBoolean readWrongValue=new AtomicBoolean(false);
        Runnable runnable=()->{
            /**
             * 由于主线程休眠，此时对于runnable而言
             * 由于线程启动原则，主线程在start之前的都可见
             * 既x=1,sem=false
             */
            if(read()==0){
                readWrongValue.getAndSet(true);
            }
            while (true){
                Threads.sleep(1000);
                /**
                 * 由于volatile原则，x的变化对于runnable可见
                 * 所以while(sem==true),then x=2
                 */
                if(read()==2){
                    System.out.println("read update val");
                }
            }
        };
        new Thread(runnable).start();
        Threads.sleep(100);
        /**
         * 说明异步线程开始执行时，x=1
         */
        System.out.println(readWrongValue.get());
        write();
        Threads.sleep(2000);
    }
}
