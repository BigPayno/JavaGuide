package jdkguide.thread.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author payno
 * @date 2019/11/15 10:47
 * @description
 */
public class AbaStack implements Stack{
    AtomicReference<Node> top=new AtomicReference<>();
    @Override
    public void push(Node node) {
        Node oldTop;
        do {
            oldTop = top.get();
            node.next = oldTop;
        }
        while (!top.compareAndSet(oldTop, node));
    }
    @Override
    public Node pop(long seconds) throws InterruptedException {
        Node newTop;
        Node oldTop;
        do {
            oldTop = top.get();
            if (oldTop == null) {
                return null;
            }
            newTop = oldTop.next;
            if (seconds != 0) {
                System.out.println(Thread.currentThread() + " 睡一下，预期拿到的数据" + oldTop.item);
                // 休眠指定的时间
                TimeUnit.SECONDS.sleep(seconds);
            }
        }
        while (!top.compareAndSet(oldTop, newTop));
        return oldTop;
    }
}
