package jdkguide.thread.threadlocal;

import com.google.common.util.concurrent.MoreExecutors;
import pattern.callBack.Executor;

import java.util.concurrent.*;

/**
 * @author payno
 * @date 2019/11/8 16:38
 * @description
 *  解决并发问题的一种思路，利用空间换取时间，互不影响
 *  早期的 ThreadLocal 是每个 ThreadLocal 类都会去创建一个 Map，然后以线程 id作为 key，
 *  要存储的局部变量作为 value，这样就可以达到线程隔离的效果。但是这样的话,这个存储数
 *  量是 Thread 的数量决定,当线程销毁之后还要去维护 Map中的那份 k-v 让它也随之销毁。后
 *  来的版本是这么设计的：每个线程都维护一个 ThreadlocalMap 哈希表(类似HashMap),这个哈
 *  希表的 key 是 ThreadLocal 对象本身，value 是要存储的局部副本值，这样的话存储数量是
 *  ThreadLocal 的数量决定的。当 Thread 销毁之后,ThreadLocalMap 也会被随之销毁,减少内
 *  存占用。而ThreadLocalMap的实现原理跟HashMap 差不多，内部有一个 Entry 数组，一个
 *  Entry通常至少包括key,value , 特殊的是这个Entry继承了 WeakReference 也就是说它是
 *  弱引用的所以可能会有 内存泄露 的情况。这个后面再说。ThreadLocal 负责管理 ThreadLocalMap
 *  ，包括插入，删除 等等.另一方面来说 ThreadLocal 基本上就相当于 门面设计模式中的一个Facade类。
 *  key就是 ThreadLocal 对象自己，同时，很重要的一点:就ThreadLocal把 Map 存储在当前线程对象里面。
 */
public class ThreadLocalGuide {
    public static void main(String[] args) throws Exception{
        ThreadLocal<String> threadNames=new ThreadLocal<>();
        ExecutorService service= new ThreadPoolExecutor(
                4,4,10, TimeUnit.SECONDS,
                //new ArrayBlockingQueue<Runnable>(4));
                new LinkedBlockingQueue<Runnable>());
        /**
         * 加一个钩子，防止主线程跑完，线程池就挂了，没法打印出来
         */
        MoreExecutors.addDelayedShutdownHook(service,4,TimeUnit.SECONDS);
        for(int i=0;i<4;i++){
            service.submit(()->{
                threadNames.set(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName()+" set "+threadNames.get());
            });
        }
        Thread.sleep(3000);
        for (int i=0;i<10;i++){
            service.submit(()->{
                System.out.println(Thread.currentThread().getName()+" get "+threadNames.get());
            });
        }
    }
}
