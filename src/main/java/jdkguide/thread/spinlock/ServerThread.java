package jdkguide.thread.spinlock;

import java.util.Random;

/**
 * @author payno
 * @date 2019/11/7 10:03
 * @description
 */
public class ServerThread extends Thread{
    private Random random;
    private RequestQueue requestQueue;

    public ServerThread(String name, long seed, RequestQueue requestQueue) {
        super(name);
        this.random = new Random(seed);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Request request = requestQueue.getRequest();
            System.out.println(Thread.currentThread().getName() + " handles " + request);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
