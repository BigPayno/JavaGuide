package jdkguide.comlpetablefature;

import guava.time.StopWatch;
import guava.time.StopWatchRunnable;
import jdkguide.thread.future.Future;

import java.util.concurrent.CompletableFuture;

/**
 * @author payno
 * @date 2019/11/18 22:22
 * @description
 */
public class CompletableFutureGuide {
    public static interface Task{
        String computeTask();
        CompletableFuture<String> computeTaskAsync();
    }
    public static class TaskImpl implements Task{
        @Override
        public String computeTask() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "hello, world";
        }

        @Override
        public CompletableFuture<String> computeTaskAsync() {
            return CompletableFuture.supplyAsync(this::computeTask);
        }
    }

    public static void doOther(){
        try{
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void doWithResult(String result){
        try{
            Thread.sleep(2000);
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TaskImpl task=new TaskImpl();
        StopWatchRunnable.time("sync",()->{
            String result=task.computeTask();
            doOther();
            doWithResult(result);
        });
        StopWatchRunnable.time("futureCallBack",()->{
            task.computeTaskAsync().whenComplete((result, throwable) ->{
                doWithResult(result);
            });
            doOther();
        });
    }
}
