package jdkguide.thread.pipe;

import java.util.Random;

/**
 * @author payno
 * @date 2019/11/7 10:58
 * @description
 */
public class Producer extends Thread{
    private final Random random;
    private final Channel table;
    private static int id = 0;
    public Producer(String name, Channel table, long seed) {
        super(name);
        this.table = table;
        this.random = new Random(seed);
    }
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(random.nextInt(1000));
                String cake = "[ Cake No." + nextId() + " by " + getName() + " ]";
                table.put(cake);
            }
        } catch (InterruptedException e) {
        }
    }
    private static synchronized int nextId() {
        return id++;
    }
}
