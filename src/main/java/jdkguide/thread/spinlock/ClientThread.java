package jdkguide.thread.spinlock;

import java.util.Random;

/**
 * @author payno
 * @date 2019/11/7 10:02
 * @description
 */
public class ClientThread extends Thread{
    private Random random;
    private RequestQueue requestQueue;
    public ClientThread(String name, long seed, RequestQueue requestQueue) {
        super(name);
        this.random = new Random(seed);
        this.requestQueue = requestQueue;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Request request = new Request("No." + i);
            System.out.println(Thread.currentThread().getName() + " requests" + request);
            requestQueue.putRequest(request);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
