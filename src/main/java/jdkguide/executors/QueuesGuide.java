package jdkguide.executors;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author payno
 * @date 2019/11/23 13:01
 * @description
 *      了解线程池执行的各种Queue
 */
public class QueuesGuide {
    /**
     * 缓存池使用SynchronousQueue
     */
    @Test
    public void cachedPool() throws Exception{
        /**
         * Creates a thread pool that creates new threads as needed, but
         * will reuse previously constructed threads when they are
         * available.  These pools will typically improve the performance
         * of programs that execute many short-lived asynchronous tasks.
         *
         * 特点:1.可以控制公平与非公平模式
         *      2.一般线程上限为无限(不拒绝任务)
         *      3.得到任务时，优先使用已经可以使用的线程，没有就新建
         * 用途:短生命周期的异步任务
         */
        ExecutorService executorService=new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        /**
         * HashMultimap.create()快要被取消了，推荐新的创建方式
         * Multimap<String,Integer> multimap=MultimapBuilder.hashKeys().hashSetValues().build()
         * 不过这里还是用set吧
         */
        Multiset<String> multiset= HashMultiset.create(1<<15);
        AtomicLong countRunnable=new AtomicLong();
        long millis=System.currentTimeMillis();
        while(System.currentTimeMillis()-millis<1024){
            executorService.submit(()->{
                System.out.println(Thread.currentThread().getName()+" run!");
                multiset.add(Thread.currentThread().getName());
                countRunnable.getAndIncrement();
            });
        }
        Thread.sleep(2000);
        multiset.elementSet().forEach(ele->{
            int count=multiset.count(ele);
            if(count!=1){
                System.out.println(ele+" count "+count);
            }
        });
        System.out.println(countRunnable.get());
    }
    /**
     * newSingleThreadExecutor
     * Creates an Executor that uses a single worker thread operating
     * off an unbounded queue. (Note however that if this single
     * thread terminates due to a failure during execution prior to
     * shutdown, a new one will take its place if needed to execute
     * subsequent tasks.
     *
     * newFixedThreadPool
     * Creates a thread pool that reuses a fixed number of threads
     * operating off a shared unbounded queue.  At any point, at most
     * {@code nThreads} threads will be active processing tasks.
     */
    public void linkedBlock(){
        Executors.newSingleThreadExecutor();
        Executors.newFixedThreadPool(2);
    }

    /**
     * 我很熟悉延迟队列，优先级和数组
     * 主要用于定时任务
     */
    @Test
    public void delayed() throws Exception{
        ScheduledExecutorService executorService=Executors.newScheduledThreadPool(1);
        executorService.schedule(()->{
            System.out.println("delay 2 s");
        },2,TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(()->{
            System.out.println("delay at fix 1 s/2 s");
        },1,2,TimeUnit.SECONDS);
        executorService.scheduleWithFixedDelay(()->{
            System.out.println("delay with fix 1 s/2 s");
        },1,2,TimeUnit.SECONDS);
        Thread.sleep(11000);
    }

    /**
     * 保持谨慎
     */
    public void forkJoin(){
        Executors.newWorkStealingPool();
    }
}
