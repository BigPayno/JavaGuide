package jdkguide.comlpetablefature;

import guava.time.StopWatchRunnable;

import java.util.concurrent.CompletableFuture;

/**
 * @author payno
 * @date 2019/11/19 15:43
 * @description
 *
 *  CompletionStage代表异步计算过程中的某一个阶段，一个阶段完成以后可能会触发另外一个阶段
 *  一个阶段的计算执行可以是一个Function，Consumer或者Runnable。
 *   比如：stage.thenApply(x -> square(x)).thenAccept(x -> System.out.print(x)).thenRun(() -> System.out.println())
 * 一个阶段的执行可能是被单个阶段的完成触发，也可能是由多个阶段一起触发
 */
public class CompletableFuture2Guide {
    public int addOneSecond(int x){
        try{
         Thread.sleep(1000);
        }catch (Exception e){
        }
        return x+1;
    }
    public static void main(String[] args) {
        CompletableFuture2Guide base=new CompletableFuture2Guide();
        StopWatchRunnable.time("sync apply chain",()->{
            CompletableFuture.supplyAsync(()->1)
                    .thenApply(base::addOneSecond)
                    .thenApply(base::addOneSecond)
                    .whenComplete((integer, throwable) -> {
                        System.out.println(integer);
                    });
        });
        StopWatchRunnable.time("async apply chain",()->{
            CompletableFuture.supplyAsync(()->1)
                    .thenApplyAsync(base::addOneSecond)
                    .thenApplyAsync(base::addOneSecond)
                    .whenCompleteAsync((integer, throwable) -> {
                        System.out.println(integer);
                    });
        });
    }
}
