package jdkguide.thread.juc.sync;

import java.util.concurrent.CountDownLatch;

/**
 * @author payno
 * @date 2019/11/9 11:41
 * @description
 */
public class CountDownLatchGuide {
    public static class ReadyForStart{
        private CountDownLatch countDownLatch;
        public ReadyForStart(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }
        public void readyToStart(){
            System.out.println(Thread.currentThread().getName()+" is ready");
            countDownLatch.countDown();
        }
        public void start() throws InterruptedException{
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName()+" start");
        }
    }

    public static class Runner extends Thread{
        private ReadyForStart readyForStart;

        public Runner(ReadyForStart readyForStart){
            this.readyForStart=readyForStart;
        }

        @Override
        public void run() {
            try {
                readyForStart.readyToStart();
                readyForStart.start();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ReadyForStart readyForStart=new ReadyForStart(new CountDownLatch(4));
        new Runner(readyForStart).start();
        new Runner(readyForStart).start();
        new Runner(readyForStart).start();
        new Runner(readyForStart).start();
    }
}
