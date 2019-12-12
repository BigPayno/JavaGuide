package multithread;

import jdkguide.lang.T;
import jdkguide.thread.Threads;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author payno
 * @date 2019/12/12 14:45
 * @description
 */
public class ThreadWorkRamGuide {
    private static int a=1;
    public static synchronized void sync(){
    }
    public static void main(String[] args) throws Exception{
        Runnable runnable=()->{
            Threads.sleep(1000);
            a=2;
        };
        new Thread(runnable).start();
        /**
         * 线程休眠也会重新读取内存
         * 500时，读进来a=1并不再更新
         * 1200时，都进来就是a=2
         */
        Threads.sleep(500);
        while (true){
            /**
             * 进入代码块之后也会重新读sync将会影响结果
             * sync();
             */
            if(a==2){
                break;
            }
        }
        System.out.println(a);
    }
}
