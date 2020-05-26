package jdkguide.thread.volat;

import jdkguide.thread.Threads;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

/**
 * @author payno
 * @date 2020/5/24 13:35
 * @description
 */
public class FastStart {
    public boolean normalRunning = true;
    public volatile boolean volatileRunning = true;
    public int sum=0;

    @Test
    public void base1(){
        new Thread(()->{
            while(normalRunning){ }
            System.out.println("thread1 stop!");
        }).start();
        Threads.sleep(1000);
        new Thread(()->{
            while(normalRunning){ }
            System.out.println("thread2 stop!");
        }).start();
        normalRunning = false;
        while (true){}
    }

    @Test
    public void base2(){
        new Thread(()->{
            while(normalRunning){ }
            System.out.println("thread1 stop!");
        }).start();
        new Thread(()->{
            while(normalRunning){
                System.out.print("");
            }
            System.out.println("thread2 stop!");
        }).start();
        Threads.sleep(1000);
        normalRunning = false;
        while (true){}
    }

    @Test
    public void base3(){
        new Thread(()->{
            while(normalRunning){ }
            System.out.println("thread1 stop!");
        }).start();
        new Thread(()->{
            while(volatileRunning){
            }
            System.out.println("thread2 stop!");
        }).start();
        Threads.sleep(1000);
        normalRunning = false;
        volatileRunning = false;
        while (true){}
    }

    @Test
    public void base4(){
        new Thread(()->{
            for(int i=0;i<100000;i++){
                sum+=i;
            }
        }).start();
        if(sum!=0){
            System.out.println(sum);
        }
        Threads.sleep(1000);
        System.out.println(sum);
        System.out.println(IntStream.range(0,100000).reduce((i,j)->i+j).getAsInt());
    }

    @Test
    public void base5(){

        for(int i=0;i<8;i++){
            Executors.newFixedThreadPool(8).execute(new Thread(()->{
                for(int j=0;j<10000;j++){
                    sum+=j;
                }
            }));
        }
        Threads.sleep(60000);
        System.out.println(sum);
        System.out.println(8*IntStream.range(0,10000).reduce((i,j)->i+j).getAsInt());
    }

    @Test
    public void base6(){
        new Thread(()->{
            while(normalRunning){ }
            System.out.println("thread1 stop!");
        }).start();
        new Thread(()->{
            int i=0;
            while(normalRunning){
                if(i<10000){
                    sum+=i;
                    i++;
                }else{
                    normalRunning = false;
                }
            }
            System.out.println("thread2 stop!");
        }).start();
        Threads.sleep(5000);
        System.out.println(normalRunning);
        System.out.println(sum);
        while(true){}
    }


    @Test
    public void test1(){
        new Thread(()->{
            int i=0;
            while(normalRunning){
                if(i<10000){
                    sum+=i;
                    i++;
                }else{
                    normalRunning = false;
                }
            }
            System.out.println("thread2 stop!");
        }).start();
        Threads.sleep(1000);
        System.out.println(normalRunning);
    }
}
