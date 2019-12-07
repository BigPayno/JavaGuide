package jdkguide.thread.volat;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author payno
 * @date 2019/12/7 14:45
 * @description
 */
public class EasyDemo {
    private boolean running=true;
    AtomicBoolean semaphore=new AtomicBoolean(false);

    public static void main(String[] args) throws Exception{
        EasyDemo obj=new EasyDemo();
        new Thread(()->{
            obj.semaphore.getAndSet(true);
            while(obj.running){ }
            System.out.println("stop!");
        }).start();
        /**
         * 主线程休眠保证
         * 保证异步线程最开始读进工作内存的obj.running就是true
         */
        Thread.sleep(1000);
        obj.running=false;
        if (obj.semaphore.get()){
            System.out.println("The thread has running!");
        }
        System.out.println("main stop");
    }
}
