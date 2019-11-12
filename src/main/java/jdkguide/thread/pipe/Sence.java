package jdkguide.thread.pipe;

/**
 * @author payno
 * @date 2019/11/7 11:07
 * @description
 */
public class Sence {
    public static void main(String[] args) {
        Channel table = new Channel(3);
        new Producer("MakerThread-1", table, 31415).start();
        new Producer("MakerThread-2", table, 92653).start();
        new Producer("MakerThread-3", table, 58979).start();
        new Consumer("EaterThread-1", table, 32384).start();
        new Consumer("EaterThread-2", table, 62643).start();
        new Consumer("EaterThread-3", table, 38327).start();
    }
}
