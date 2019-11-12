package jdkguide.thread.juc.sync;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.*;

/**
 * @author payno
 * @date 2019/11/9 11:43
 * @description
 *      生产者会产生资源，只有到达一定数量时，消费者才会使用
 */
public class CyclicBarrierGuide {
    public static class BarrierRunner extends Thread{
        private CyclicBarrier cyclicBarrier;
        public BarrierRunner(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier=cyclicBarrier;
        }
        @Override
        public void run() {
            try{
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName()+" Wait for the other consumer to join！");
                cyclicBarrier.await(2, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName()+" is so happy to play this");
            }catch (InterruptedException | BrokenBarrierException e){
                if(e instanceof InterruptedException){
                    e.printStackTrace();
                }
            }catch (TimeoutException e){
                System.out.println(Thread.currentThread().getName()+" ready to play it though no 4 consumer");
            }
        }
    }

    public static void main(String[] args) throws Exception{
        CyclicBarrier cyclicBarrier=new CyclicBarrier(4,()->{
            System.out.println("Ready 4 consumer to sit");
        });
        BarrierRunner barrierRunner=new BarrierRunner(cyclicBarrier);
        BarrierRunner barrierRunner1=new BarrierRunner(cyclicBarrier);
        barrierRunner.start();
        barrierRunner1.start();

        Thread.sleep(5000);
        CyclicBarrier cyclicBarrier2=new CyclicBarrier(4,()->{
            System.out.println("Ready 4 consumer to sit");
        });
        for(int i=0;i<9;i++){
            new BarrierRunner(cyclicBarrier2).start();
        }
    }
}
