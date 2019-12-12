package jdkguide.thread;

/**
 * @author payno
 * @date 2019/12/12 10:33
 * @description
 */
public final class Threads {
    public static void sleep(long millis){
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
