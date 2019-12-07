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
        new Thread(()->{
            sleep(1000);
            obj.source=true;
        }).start();
        new Thread(()->{
            sleep(2000);
            /**
             * 在线程进入同步代码块时会检查自己的工作空间并更新
             */
            synchronized (new Object()){
                if(obj.source){
                    obj.count.getAndIncrement();
                }
            }
        }).start();
        sleep(3000);
        if(obj.count.get()==1){
            System.out.println("see it after thread visiting sync");
        }
    }
}
