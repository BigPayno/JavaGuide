package jdkguide.thread.volat;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author payno
 * @date 2019/12/7 13:48
 * @description
 *      1.volatile保证每次更新数据会立即保存到主存中
 *      2.普通变量仅在进入同步代码块时候才检查线程内存
 */
public class NewVolatileGuide {
    boolean source=false;
    AtomicLong count=new AtomicLong(0);
    AtomicLong count2=new AtomicLong(0);
    private void sleep(long millis){
        try{
            Thread.sleep(millis);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test1(){
        NewVolatileGuide obj=new NewVolatileGuide();
        new Thread(()->{
            sleep(1000);
            obj.source=true;
        }).start();
        new Thread(()->{
            while(true){
                if(obj.source){
                    /**
                     * 假如这个线程看到source的改变，count应该不为0
                     */
                    obj.count.getAndIncrement();
                }
            }
        }).start();
        sleep(3000);
        if(obj.count.get()==0){
            System.out.println("never see it");
        }
    }

    @Test
    public void test2(){
        NewVolatileGuide obj=new NewVolatileGuide();
        final Object lock=new Object();
        new Thread(()->{
            sleep(1000);
            obj.source=true;
        }).start();
        new Thread(()->{
            /**
             * 在线程进入同步代码块时会检查自己的工作空间并更新
             */
            while (!obj.source){
                /**
                 * 保证每次循环都读取新值
                 */
                synchronized (lock){
                    if(obj.source){
                        obj.count.getAndIncrement();
                    }
                }
            }
            /**
             * 防止结束判断后更新为true跳出循环，进行弥补
             */
            if(obj.source){
                if(obj.count.get()==0){
                    obj.count.getAndIncrement();
                }
            }
        }).start();
        new Thread(()->{
            while (!obj.source){
                if(obj.source){
                    obj.count2.getAndIncrement();
                }
            }
            if(obj.source){
                if(obj.count2.get()==0){
                    obj.count2.getAndIncrement();
                }
            }
        }).start();
        sleep(2000);
        System.out.println(obj.count.get());
        System.out.println(obj.count2.get());
    }

    @Test
    public void test3(){
        NewVolatileGuide obj=new NewVolatileGuide();
        new Thread(()->{
            sleep(1000);
            obj.source=true;
        }).start();
        new Thread(()->{
            /**
             * 线程休眠不会刷新工作空间
             */
            sleep(500);
            while(true){
                if(obj.source){
                    obj.count.getAndIncrement();
                }
            }
        }).start();
        sleep(3000);
        if(obj.count.get()!=0){
            System.out.println("see it after thread sleep");
        }
    }
}
