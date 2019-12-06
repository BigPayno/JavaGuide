package guava.limit;

import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author payno
 * @date 2019/12/6 10:05
 * @description
 * double    acquire()
 * 从RateLimiter获取一个许可，该方法会被阻塞直到获取到请求
 * double 	acquire(int permits)
 * 从RateLimiter获取指定许可数，该方法会被阻塞直到获取到请求
 * static RateLimiter 	create(double permitsPerSecond)
 * 根据指定的稳定吞吐率创建RateLimiter，这里的吞吐率是指每秒多少许可数（通常是指QPS，每秒多少查询）
 * static RateLimiter 	create(double permitsPerSecond, long warmupPeriod, TimeUnit unit)
 * 根据指定的稳定吞吐率和预热期来创建RateLimiter，这里的吞吐率是指每秒多少许可数（通常是指QPS，每秒多少个请求量），在这段预热时间内，RateLimiter每秒分配的许可数会平稳地增长直到预热期结束时达到其最大速率。（只要存在足够请求数来使其饱和）
 * double 	getRate()
 * 返回RateLimiter 配置中的稳定速率，该速率单位是每秒多少许可数
 * void 	setRate(double permitsPerSecond)
 * 更新RateLimite的稳定速率，参数permitsPerSecond 由构造RateLimiter的工厂方法提供。
 * String 	toString()
 * 返回对象的字符表现形式
 * boolean 	tryAcquire()
 * 从RateLimiter 获取许可，如果该许可可以在无延迟下的情况下立即获取得到的话
 * boolean 	tryAcquire(int permits)
 * 从RateLimiter 获取许可数，如果该许可数可以在无延迟下的情况下立即获取得到的话
 * boolean 	tryAcquire(int permits, long timeout, TimeUnit unit)
 * 从RateLimiter 获取指定许可数如果该许可数可以在不超过timeout的时间内获取得到的话，或者如果无法在timeout 过期之前获取得到许可数的话，那么立即返回false （无需等待）
 * boolean 	tryAcquire(long timeout, TimeUnit unit)
 * 从RateLimiter 获取许可如果该许可可以在不超过timeout的时间内获取得到的话，或者如果无法在timeout 过期之前获取得到许可的话，那么立即返回false（无需等待）
 */
public class RateLimiterGuide {
    /**
     * 每秒释放两个令牌(令牌桶)
     */
    private RateLimiter rateLimiter=RateLimiter.create(2);
    /**
     * 只有corePool和queue生效
     * 无界任务队列，线程池线程数为4
     */
    private ThreadPoolExecutor executor=new ThreadPoolExecutor(
            4,4,1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>()
    );

    @Test
    public void test(){
        final AtomicLong index=new AtomicLong(0);
        while(true){
            executor.submit(()->{
                boolean token=rateLimiter.tryAcquire(2,1,TimeUnit.SECONDS);
                if(token){
                    System.out.println("execute "+index.getAndIncrement());
                }else{
                    System.out.println("execute without wait"+index.getAndIncrement());
                }
            });
        }
    }

    @Test
    public void test2() throws Exception{
        final AtomicLong index=new AtomicLong(0);
        MoreExecutors.addDelayedShutdownHook(executor,20,TimeUnit.SECONDS);
        executor.submit(()->{
            while(true){
                executor.submit(()->{
                    rateLimiter.acquire(2);
                    System.out.println("execute "+index.getAndIncrement());
                });
            }
        });
    }
}
