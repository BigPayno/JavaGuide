package jdkguide.thread.spinlock;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author payno
 * @date 2019/11/7 09:55
 * @description
 */
public class RequestQueue {
    private final Queue<Request> queue=new LinkedList<>();
    public synchronized Request getRequest(){
        while(queue.peek()==null){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return queue.remove();
    }
    public synchronized void putRequest(Request request){
        queue.offer(request);
        notifyAll();
    }
}
