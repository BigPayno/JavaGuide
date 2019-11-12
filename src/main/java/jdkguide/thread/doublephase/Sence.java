package jdkguide.thread.doublephase;

/**
 * @author payno
 * @date 2019/11/8 16:33
 * @description
 */
public class Sence {
    public static void main(String[] args) {
        System.out.println("main: BEGIN");
        try {
            CountThread t = new CountThread();
            t.start();
            Thread.sleep(10000);
            System.out.println("main: shutdownRequest");
            t.shutDown();
            System.out.println("main: join");
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main: END");
    }
}
