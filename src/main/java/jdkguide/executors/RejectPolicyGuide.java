package jdkguide.executors;


import org.junit.Test;

import java.util.concurrent.*;

import static java.util.concurrent.ThreadPoolExecutor.*;

/**
 * @author payno
 * @date 2019/11/23 13:01
 * @description
 *      了解常用线程池的拒绝策略及运用场景
 *      拒绝策略发生在当我们核心线程数、阻塞队列、最大线程数都到达上限的时候。
 *      优先级
 *      生成核心线程运行任务，加入队列，生成临时线程直到最大，拒绝
 */
public class RejectPolicyGuide {
    public static void sleep(long millis){
        try{
            Thread.sleep(millis);
        }catch (Exception e){
        }
    }
    public static Runnable longTask=()->{
        System.out.printf("Task [%s] start!%n",Thread.currentThread().getName());
        sleep(5000);
        System.out.printf("Task [%s] end!%n",Thread.currentThread().getName());
    };

    public void test(RejectedExecutionHandler policy){
        ThreadPoolExecutor service=new ThreadPoolExecutor(
                2,4,10, TimeUnit.SECONDS,
                //new ArrayBlockingQueue<Runnable>(4));
                new ArrayBlockingQueue<Runnable>(4));
        service.setRejectedExecutionHandler(policy);
        /**
         * 注意使用的队列是无界还是有界
         * 默认策略，超出抛弃任务并报异常
         * submit
         */
        try{
            for(int i=0;i<11;i++){
                service.submit(longTask);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        sleep(50000);
    }

    /**
     * corePoolSize the number of threads to keep in the pool, even
     *      if they are idle, unless {@code allowCoreThreadTimeOut} is set
     *maximumPoolSize the maximum number of threads to allow in the
     *      pool
     *keepAliveTime when the number of threads is greater than
     *      the core, this is the maximum time that excess idle threads
     *      will wait for new tasks before terminating.
     * 功能：当触发拒绝策略时，直接抛出拒绝执行的异常，打断当前执行流程
     * 使用场景：这个就没有特殊的场景了，但是一点要正确处理抛出的异常。
     */
    @Test
    public void abortPolicy() throws Exception{
       test(new AbortPolicy());
    }

    /**
     * 功能：当触发拒绝策略时，只要线程池没有关闭，就由提交任务的当前线程处理。
     * 使用场景：一般在不允许失败的、对性能要求不高、并发量较小的场景下使用，
     * 因为线程池一般情况下不会关闭，也就是提交的任务一定会被运行，但是由于是调用者线程自己执行的，
     * 当多次提交任务时，就会阻塞后续任务执行，性能和效率自然就慢了。
     * 不抛弃不放弃，还是无界队列好
     */
    @Test
    public void caller(){
        test(new CallerRunsPolicy());
    }

    /**
     *功能：发生拒绝策略时，不触发任何动作,另一个是抛弃老的任务
     * 使用场景：如果你提交的任务无关紧要，你就可以使用它 。
     * 因为它就是个空实现，会悄无声息的吞噬你的的任务。所以这个策略基本上不用了
     */
    @Test
    public void discard(){
        test(new DiscardPolicy());
        test(new DiscardOldestPolicy());
    }
}
