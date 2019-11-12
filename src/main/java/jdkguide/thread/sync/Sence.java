package jdkguide.thread.sync;

/**
 * @author payno
 * @date 2019/11/4 15:49
 * @description
 *  1.对整个门加锁，对门的部分进行加锁
 *  2.资源访问的互斥，对资源加锁
 */
public class Sence {
    public static void main(String[] args) {
        Provider provider=new Provider();
        Consumer bei=new Consumer("Bei bei","BeiJing",provider);
        Consumer niu=new Consumer("Niu niu","NanJing",provider);
        new Thread(bei).start();
        new Thread(niu).start();
    }
}
