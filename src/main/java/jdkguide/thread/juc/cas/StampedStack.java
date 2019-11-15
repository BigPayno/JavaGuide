package jdkguide.thread.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author payno
 * @date 2019/11/15 11:08
 * @description
 */
public class StampedStack implements Stack{
    AtomicStampedReference<Node> top = new AtomicStampedReference<Node>(null,0);
    @Override
    public void push(Node node){
        Node oldTop;
        int v;
        do{
            v=top.getStamp();
            oldTop = top.getReference();
            node.next = oldTop;
        }
        while(!top.compareAndSet(oldTop, node,v,v+1));
    }
    @Override
    public Node pop(long time){
        Node newTop;
        Node oldTop;
        int v;
        do{
            v=top.getStamp();
            oldTop = top.getReference();
            if(oldTop == null){
                return null;
            }
            newTop = oldTop.next;
            try {
                if (time != 0) {
                    System.out.println(Thread.currentThread() + " 睡一下，预期拿到的数据" + oldTop.item);
                    TimeUnit.SECONDS.sleep(time);
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while(!top.compareAndSet(oldTop, newTop,v,v+1));
        return oldTop;
    }
    public void get(){
        Node node = top.getReference();
        while(node!=null){
            System.out.println(node.item);
            node = node.next;
        }
    }
}
