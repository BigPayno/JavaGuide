package guava.time;

import com.google.common.base.Stopwatch;

/**
 * @author payno
 * @date 2019/11/4 15:04
 * @description
 */
public final class StopWatchRunnable {
    public static void time(String event,Runnable runnable){
        Stopwatch stopwatch=Stopwatch.createStarted();
        System.out.printf("[%s] 开始执行!%n",event);
        runnable.run();
        System.out.printf("[%s] 共计耗时 [%s]%n",event,stopwatch.stop());
    }


}
