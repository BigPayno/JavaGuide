package jdkguide.forkjoin;

import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author payno
 * @date 2019/10/28 15:49
 * @description
 */
public class FutrueGuide {
    public static void main(String[] args) throws Exception{
        Future<String> result=new AsyncResult<>("abc");

        ExecutorService executorService= Executors.newCachedThreadPool();
        FutureTask<String> futureTask=new FutureTask<String>(()->{
            try{
                Thread.sleep(100);
            }catch (Exception e){
                return "something wrong!";
            }
            return "future thread has been finished!";
        });
        executorService.submit(futureTask);
        System.out.println("main thread is running!");
        while(!futureTask.isDone()){
            System.out.println("please wait for the result of futureTask!");
        }
        System.out.println(futureTask.get());
    }
}
