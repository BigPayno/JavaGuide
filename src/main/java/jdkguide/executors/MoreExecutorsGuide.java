package jdkguide.executors;

import com.google.common.util.concurrent.*;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.concurrent.*;

/**
 * @author payno
 * @date 2019/11/23 16:57
 * @description
 */
public class MoreExecutorsGuide {
    public void sleep(long millis){
        try{
            Thread.sleep(millis);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 正常用户线程结束，线程池线程不会停止
     * ME提供了关闭线程池线程的方法
     */
    @Test
    public void shutDownExecutor() throws Exception{
        //ThreadFactory threadFactory=new ThreadFactoryBuilder().setDaemon(true).setNameFormat("async-pool-%d").build();
        ExecutorService service=new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        /**
         * 默认不是守护线程，守护线程在用户线程结束后结束
         * 该方法使线程在主线程结束后一定时间内，slowdown线程池
         */
        MoreExecutors.addDelayedShutdownHook(service,7,TimeUnit.SECONDS);
        long millis=System.currentTimeMillis();
        while (System.currentTimeMillis()-millis<10000){
            Thread.sleep(1000);
            service.submit(()->{
                sleep(5000);
                System.out.println("still running!");
            });
        }
        System.out.println("test method end!");
    }

    /**
     * 可监听的线程池
     * 已经被CompletableFuture替代
     */
    @Test
    public void listeningDecorator(){
        ListeningExecutorService service= MoreExecutors.listeningDecorator(
                new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
                        new SynchronousQueue<Runnable>())
        );
        ListenableFuture<String> future=service.submit(()->{
            System.out.println("run task");
            return "hello";
        });
        Futures.addCallback(future,new FutureCallback(){
            @Override
            public void onSuccess(@Nullable Object result) {
                System.out.println("run success!");
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        },MoreExecutors.directExecutor());
    }
}
