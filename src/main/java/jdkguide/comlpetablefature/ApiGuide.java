package jdkguide.comlpetablefature;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

/**
 * @author payno
 * @date 2019/11/19 15:58
 * @description
 */
public class ApiGuide {
    public static class Base{
        static Base base=new Base();
        public void runnable(){
            try{
                System.out.println("runnable start run!");
                Thread.sleep(1000);
                System.out.println("runnable has been finished!");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        public void runnableAndThrowable(){
            int x=1/0;
        }

        public String supplier(){
            try{
                System.out.println("runnable start run!");
                Thread.sleep(2000);
                System.out.println("runnable has been finished!");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "finished";
        }

        @Test
        public void constractor(){
            /**
             * 有线程池，使用对应线程池，没有的话默认使用
             * ForkJoinPool.commonPool
             * 默认参数;Runnable,Supplier
             */
            CompletableFuture.runAsync(base::runnable);
            CompletableFuture.supplyAsync(base::supplier);
        }

        @Test
        /**
         * complete(T t) 完成异步执行，并返回future的结果
         * completeExceptionally(Throwable ex) 异步执行不正常的结束
         * 如果任务已经完成，则方法失效
         */
        public void complete() throws Exception{
            CompletableFuture<String> future=CompletableFuture.supplyAsync(base::supplier);
            future.complete("change");
            System.out.println(future.get());

            CompletableFuture<String> future2=CompletableFuture.supplyAsync(base::supplier);
            Thread.sleep(3000);
            future2.complete("change");
            System.out.println(future2.get());
        }

        /**
         * thenApply(Function<? super T,? extends U> fn)
         * 接受一个Function<? super T,? extends U>参数用来转换CompletableFuture thenApplyAsync(Function<? super T,? extends U> fn)
         * 接受一个Function<? super T,? extends U>参数用来转换CompletableFuture，使用ForkJoinPool thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
         * 接受一个Function<? super T,? extends U>参数用来转换CompletableFuture，使用指定的线程池
         */
        public void pipe(){
            Executor executor=ForkJoinPool.commonPool();
            CompletableFuture.supplyAsync(base::supplier,executor)
                    .thenApply(String::toUpperCase);
            CompletableFuture.supplyAsync(base::supplier,executor)
                    .thenApplyAsync(String::toLowerCase,executor);
        }

        @Test
        public void finisher() throws Exception{
            Executor executor=ForkJoinPool.commonPool();
            CompletableFuture.supplyAsync(base::supplier).whenComplete((s, throwable) -> {
                System.out.println(s);
            });
            CompletableFuture.supplyAsync(base::supplier,executor)
                    .whenCompleteAsync((s, throwable) -> {
                        System.out.println(s);
                    },executor);
            /**
             * 必须加这个否则单元测试结束了，还没显示
             */
            Thread.sleep(3000);
        }

        /**
         * 两个任务是串行执行的
         * 和thenCombine不同
         * 这里会对一个元素进行多次处理
         * 而thenCombine是对多个元素进行一次处理
         * A-1>A-2->A-3
         *
         * A-1>
         *      AB-1
         * B-1>
         */
        @Test
        public void reduceSync() throws Exception{
            CompletableFuture.supplyAsync(base::supplier)
                    .thenCompose(s->CompletableFuture.supplyAsync(()->s+"abc"))
                    .thenCompose(s->CompletableFuture.supplyAsync(()->s+"abc"))
                    .whenComplete((s, throwable) -> {
                        System.out.println(s);
                    });
            Thread.sleep(3000);
        }

        @Test
        /**
         * T,T->V
         * thenAcceptBoth跟thenCombine类似，但是返回CompletableFuture<Void>类型。
         * 两个异步任务是并行的
         * 这样最终得到的任务流是没有被消费的
         * 后面可以添加消费操作
         * 是一个生产者
         */
        public void reduceParallel() throws Exception{
            CompletableFuture.supplyAsync(base::supplier)
                    .thenCombine(
                            CompletableFuture.supplyAsync(base::supplier),
                            (a,b)->a+b
                            );
        }

        @Test
        /**
         * 任务流的元素一旦被消费就没了
         * thenAccept是消费一个
         * thenAcceptBoth是阻塞等待最终消费两个
         */
        public void consumer() throws Exception{
            CompletableFuture.supplyAsync(base::supplier)
                    .thenAccept(System.out::println)
                    .thenAccept(System.out::println);
            CompletableFuture.supplyAsync(base::supplier)
                    .thenAcceptBoth(
                      CompletableFuture.supplyAsync(base::supplier),
                            (a,b)->{
                                System.out.println(a);
                                System.out.println(b);
                            }
                    );
            Thread.sleep(3000);
        }

        @Test
        /**
         * 这里在pipeline中结束后对结果进行处理
         */
        public void handle(){
            CompletableFuture.runAsync(base::runnableAndThrowable)
                    .whenComplete(((aVoid, throwable) -> {
                        System.out.println("task has been finished!"); }))
                    .handle(((aVoid, throwable) -> {
                        return "error"; }))
                    .whenComplete(((s, throwable) -> {
                        if("error".equals(s)){
                            System.out.println("Something is error!");
                        }
                    }));

        }

    }
}
