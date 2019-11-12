package jdkguide.thread.balking;

/**
 * @author payno
 * @date 2019/11/7 10:31
 * @description
 */
public class Sence {
    public static void main(String[] args) {
        Data data = new Data("data.txt", "(empty)");
        new ChangerThread("ChangerThread", data).start();
        new SaverThread("SaverThread", data).start();
    }
}
