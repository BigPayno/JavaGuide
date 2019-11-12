package jdkguide.thread.balking;

import java.io.IOException;

/**
 * @author payno
 * @date 2019/11/7 10:37
 * @description
 */
public class SaverThread extends Thread{
    private Data data;
    public SaverThread(String name, Data data) {
        super(name);
        this.data = data;
    }
    @Override
    public void run() {
        try {
            while (true) {
                data.save(); // 存储资料
                Thread.sleep(1000); // 休息约1秒
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
