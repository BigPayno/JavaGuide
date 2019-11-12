package jdkguide.forkjoin;

import java.util.concurrent.*;

/**
 * @author payno
 * @date 2019/10/28 14:47
 * @description
 */
public class InterfaceGuide {
    public static void main(String[] args) {
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable");
            }
        };
        Callable<String> callable=new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("callable");
                return "why!!!";
            }
        };
        Future<String> a=new Future<String>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public String get() throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public String get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        };
    }
}
