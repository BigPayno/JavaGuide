package jdkguide.thread.sence;

import jdkguide.thread.future.Future;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author payno
 * @date 2019/11/19 14:53
 * @description
 *      监听
 */
public class Sence extends Thread{
    /**
     * 存放未执行任务
     */
    BlockingQueue<FutureTask<Void>> tasks=new LinkedBlockingQueue<>(10);
    /**
     * 存放执行中任务
     */
    List<FutureTask<Void>> executeTasks=new CopyOnWriteArrayList<>();
    ExecutorService executorService=Executors.newCachedThreadPool();
    public void cancel(FutureTask<Void> task){
        if(executeTasks.contains(task)){
            task.cancel(true);
        }
    }
    public void submit(FutureTask<Void> task){
        /**
         * 满了会阻塞
         */
        tasks.offer(task);
    }

    @Override
    public void run() {
        /**
         * 消费者
         */
        while(!tasks.isEmpty()){
            executorService.submit(()->{
                try{
                    FutureTask<Void> task=tasks.take();
                    executeTasks.add(task);
                    executorService.submit(task);
                    while(task.isDone()){
                        executeTasks.remove(task);
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
    }
}
