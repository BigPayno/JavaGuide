package jdkguide.thread.juc.locks;

import java.time.Instant;
import java.util.concurrent.locks.LockSupport;

/**
 * @author payno
 * @date 2019/11/16 16:09
 * @description
 */
public class LockSupportGuide {
    public static class TestThread extends Thread{
        long parkSecond;
        public TestThread(long parkSecond){
            this.parkSecond=parkSecond;
        }
        @Override
        public void run() {
            try{
                while(true){
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName());
                    LockSupport.parkUntil(Instant.now().plusSeconds(parkSecond).toEpochMilli());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws Exception{
        TestThread a=new TestThread(0);
        TestThread b=new TestThread(5);
        TestThread c=new TestThread(5);
        a.start();
        b.start();
        c.start();
        Thread.sleep(2000);
        LockSupport.unpark(c);
    }
}
