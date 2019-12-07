package jdkguide.thread.volat;

/**
 * @author payno
 * @date 2019/12/7 15:08
 * @description
 */
public class T {
    boolean running = true;
    public static void main(String[] args) throws Exception{
        T t=new T();
        Object sync=new Object();
        new Thread(()->{
            System.out.println("r1 run!");
            while(t.running){}
            System.out.println("r1 stop!");
        }).start();
        new Thread(()->{
            System.out.println("r2 run!");
            while(t.running){
                synchronized (sync){}
            }
            System.out.println("r2 stop!");
        }).start();
        Thread.sleep(1000);
        t.running=false;
    }
}
