package jdkguide.thread.juc.sync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author payno
 * @date 2019/11/9 15:02
 * @description
 */
public class CountDownLatchGuide2 {
    public static class Runner extends Thread{
        private static AtomicBoolean finished=new AtomicBoolean(false);
        private CountDownLatch countDownLatch;
        public Runner(CountDownLatch countDownLatch){
            this.countDownLatch=countDownLatch;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" is ready for the task!");
            countDownLatch.countDown();
            try{
                countDownLatch.await(5, TimeUnit.SECONDS);
                if (!finished.get()&&finished.compareAndSet(false,true)){
                    System.out.println(Thread.currentThread().getName()+" finished the task!");
                }else{
                    System.out.println("the task has been finished!");
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch=new CountDownLatch(4);
        Runner runner0=new Runner(countDownLatch);
        Runner runner1=new Runner(countDownLatch);
        Runner runner2=new Runner(countDownLatch);
        runner0.start();
        runner1.start();
        runner2.start();
    }
}
