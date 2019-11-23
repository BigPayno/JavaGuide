package jdkguide.forkjoin;

import guava.time.StopWatchRunnable;
import org.junit.Test;

import java.util.stream.LongStream;

/**
 * @author payno
 * @date 2019/11/23 17:37
 * @description
 */
public class NewForkJoinGuide {
    public long sumByForI(long end){
        long sum=0L;
        for(long i=0L;i<end;i++){
            sum+=i;
        }
        return sum;
    }

    public long sumByStream(long end){
        /**
         * 就是使用ForkJoin并优化的
         */
        return LongStream.rangeClosed(0L,end).parallel().sum();
    }

    public void test(long end){
        StopWatchRunnable.time("for i"+end,()->{
            sumByForI(end);
        });
        StopWatchRunnable.time("stream fork join"+end,()->{
            sumByStream(end);
        });
        System.out.println();
    }

    public void test2(long end){
        StopWatchRunnable.time("test2::for i"+end,()->{
            long sum=0L;
            for(long i=0L;i<end;i++){
                sum+=sumByForI(i);
            }
        });
        StopWatchRunnable.time("test2::parallel stream fork join"+end,()->{
            LongStream.range(0L,end).parallel().map(num->sumByForI(num)).sum();
        });
        System.out.println();
    }

    /**
     * 任务特别简单，for快
     * 任务比较稍微复制，forkJoin有优势
     */
    @Test
    public void main(){
        test(10);
        test(100000);
        test2(100000);
        test(1000000000L);
        test(100000000000L);
    }
}
