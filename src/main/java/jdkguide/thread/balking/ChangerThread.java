package jdkguide.thread.balking;

import java.io.IOException;
import java.util.Random;

/**
 * @author payno
 * @date 2019/11/7 10:37
 * @description
 */
public class ChangerThread extends Thread{
    private Data data;
    private Random random = new Random();
    public ChangerThread(String name, Data data) {
        super(name);
        this.data = data;
    }
    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                data.change("No." + i);
                Thread.sleep(random.nextInt(1000));
                data.save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
