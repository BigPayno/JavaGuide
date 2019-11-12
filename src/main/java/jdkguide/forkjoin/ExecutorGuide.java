package jdkguide.forkjoin;

import java.util.concurrent.*;

/**
 * @author payno
 * @date 2019/10/28 15:31
 * @description
 */
public class ExecutorGuide {
    private static int CORE_POOL_SIZE=10;
    private static int MAX_POOL_SIZE=30;
    private static long KEEP_ALIVE_TIME=10;
    private static BlockingQueue<Runnable> runnableBlockingQueue;
    public static void main(String[] args) {
        runnableBlockingQueue=new ArrayBlockingQueue<Runnable>(30,true);
        Executor executor=new ThreadPoolExecutor(
                CORE_POOL_SIZE,MAX_POOL_SIZE,KEEP_ALIVE_TIME,TimeUnit.MINUTES,runnableBlockingQueue);
        for(int i=0;i<100;i++){
            executor.execute(()->System.out.println("why not!"));
        }
    }
}
