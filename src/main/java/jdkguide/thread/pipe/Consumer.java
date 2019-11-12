package jdkguide.thread.pipe;

import java.util.Random;

/**
 * @author payno
 * @date 2019/11/7 10:58
 * @description
 */
public class Consumer extends Thread{
    private final Random random;
    private final Channel channel;
    public Consumer(String name, Channel table, long seed) {
        super(name);
        this.channel = table;
        this.random = new Random(seed);
    }
    @Override
    public void run() {
        try {
            while (true) {
                String cake = channel.take();
                Thread.sleep(random.nextInt(3000));
            }
        } catch (InterruptedException e) {
        }
    }
}
