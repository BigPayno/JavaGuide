package jdkguide.forkjoin;

import java.util.concurrent.Executors;

/**
 * @author payno
 * @date 2019/10/28 15:40
 * @description
 */
public class ExecutorsGuide {
    public static void main(String[] args) {
        Executors.callable(()-> System.out.println("callable"),"callable");
        Executors.defaultThreadFactory().newThread(()-> System.out.println("thread"));
        Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(5);
        Executors.newScheduledThreadPool(5);
        Executors.newSingleThreadExecutor();
        Executors.newWorkStealingPool(5);
    }
}
