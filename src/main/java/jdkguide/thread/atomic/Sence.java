package jdkguide.thread.atomic;

import guava.time.StopWatchRunnable;

/**
 * @author payno
 * @date 2019/11/5 09:05
 * @description
 */
public class Sence {
    public static void main(String[] args) throws Exception{
        Provider provider=new Provider();
        StopWatchRunnable.time("count normal",()->{
            for (int i = 0; i <1<<10 ; i++) {
                Consumer0 consumer0=new Consumer0(provider,provider1 -> {
                    provider1.addCount();
                });
                consumer0.start();
            }
        });
        Thread.sleep(1000);
        System.out.println(provider.getCount());
        StopWatchRunnable.time("count sync",()->{
            for (int i = 0; i <1<<10 ; i++) {
                Consumer0 consumer0=new Consumer0(provider,provider1 -> {
                    provider1.addCount1();
                });
                consumer0.start();
            }
        });
        Thread.sleep(1000);
        System.out.println(provider.getCount1());
        StopWatchRunnable.time("count atomic",()->{
            for (int i = 0; i <1<<10 ; i++) {
                Consumer0 consumer0=new Consumer0(provider,provider1 -> {
                    provider1.addCount2();
                });
                consumer0.start();
            }
        });
        Thread.sleep(1000);
        System.out.println(provider.getCount2());
        StopWatchRunnable.time("count atomicFieldUpdater",()->{
            for (int i = 0; i <1<<10 ; i++) {
                Consumer0 consumer0=new Consumer0(provider,provider1 -> {
                    provider1.addCount3();
                });
                consumer0.start();
            }
        });
        Thread.sleep(1000);
        System.out.println(provider.getCount3());
    }
}
