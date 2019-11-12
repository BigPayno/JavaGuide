package jdkguide.thread.juc.sync;

import org.junit.runner.Runner;

import java.util.concurrent.Semaphore;

/**
 * @author payno
 * @date 2019/11/9 11:41
 * @description
 */
public class SemaphoreGuide {
    public static class Swimmer extends Thread{
        private Semaphore swimPool;
        public Swimmer(Semaphore semaphore){
            this.swimPool=semaphore;
        }

        @Override
        public void run() {
            try{
                System.out.println(Thread.currentThread().getName()+" want to swim!");
                swimPool.acquire();
                System.out.println(Thread.currentThread().getName()+" start to swim!");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+" ready to leave!");
                swimPool.release();
                System.out.println("permit: "+swimPool.availablePermits());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(2);
        Swimmer swimmer=new Swimmer(semaphore);
        Swimmer swimmer1=new Swimmer(semaphore);
        Swimmer swimmer2=new Swimmer(semaphore);
        swimmer.start();
        swimmer1.start();
        swimmer2.start();
    }
}
