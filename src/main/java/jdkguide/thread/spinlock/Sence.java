package jdkguide.thread.spinlock;

/**
 * @author payno
 * @date 2019/11/7 10:04
 * @description
 */
public class Sence {
    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread("Alice",3141592L,requestQueue).start();
        new ServerThread("Bobby",6535897L,requestQueue).start();
    }
}
