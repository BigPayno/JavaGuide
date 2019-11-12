package jdkguide.forkjoin;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author payno
 * @date 2019/10/29 15:32
 * @description
 */
public class ForkJoinGuide1 {
    private ForkJoinPool pool=new ForkJoinPool();
    public class CountForkJoinTask extends RecursiveTask<Long>{
        private int threshold=10;
        private long start;
        private long end;
        public CountForkJoinTask(long start,long end){
            this.start=start;
            this.end=end;
        }
        @Override
        protected Long compute() {
            if(end-start<=threshold){
                long count=0;
                for(long i=start;i<=end;i++){
                    count+=i;
                }
                return count;
            }else{
                long slip=(end-start)/3;
                CountForkJoinTask task1=new CountForkJoinTask(start,start+slip);
                CountForkJoinTask task2=new CountForkJoinTask(start+slip+1,start+slip+slip);
                CountForkJoinTask task3=new CountForkJoinTask(start+slip+slip+1,end);
                invokeAll(task1,task2,task3);
                return task1.join()+task2.join()+task3.join();
            }
        }
    }

    @Test
    public void main() {
        long end=100000000;
        Stopwatch stopWatch=Stopwatch.createStarted();
        CountForkJoinTask task=new CountForkJoinTask(1,end);
        pool.invoke(task);
        if (task.isCompletedAbnormally()) {
            Throwable throwable = task.getException();
            if (Objects.nonNull(throwable)) {
                System.out.println(throwable.getMessage());
            }
        }
        System.out.printf("计算为：%s, 耗时：%s",task.join(),stopWatch.stop());
        System.out.println();
        Stopwatch stopwatch2=Stopwatch.createStarted();
        long count=0;
        for(long i=0;i<=end;i++){
            count+=i;
        }
        System.out.printf("计算为：%s, 耗时：%s",count,stopwatch2.stop());
    }
}
