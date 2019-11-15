package jdkguide.thread.juc.cas;

/**
 * @author payno
 * @date 2019/11/15 11:06
 * @description
 */
public interface Stack {
    void push(Node node);
    Node pop(long seconds) throws InterruptedException;
}
