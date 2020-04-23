package jdkguide.collections.concurrent;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author payno
 * @date 2019/11/15 14:40
 * @description
 *  延迟队列  组合 优先级队列  继承  阻塞队列
 *  Condition.available PriorityQueue<E> p ReentrantLock
 *  1.offer lock
 *     p.offer(ele),if p.peek()==ele:leader==null&&available.signal
 *  2.poll lock
 *     if p.peek()==null || p.peek().getDelay(NANOSECONDS) > 0 :null
 *     else: return p.peek()
 *  3.take blocking
 */
public class DelayQueueGuide {
    @Data
    public static class DelayMessage implements Delayed{
        private int id;
        private String body;
        private long executeNanoTime;
        public DelayMessage(int id,String body,long duration,TimeUnit timeUnit){
            this.id=id;
            this.body=body;
            this.executeNanoTime=System.nanoTime()+timeUnit.toNanos(duration);
        }
        private long getDistance(){
            return executeNanoTime-System.nanoTime();
        }
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(getDistance(),TimeUnit.NANOSECONDS);
        }
        @Override
        public int compareTo(Delayed o) {
            if(o instanceof DelayMessage){
                DelayMessage delayMessage=(DelayMessage) o;
                return Ordering.from(Comparator.comparing(DelayMessage::getDistance))
                        .thenComparing(Comparator.comparing(DelayMessage::getId).reversed())
                        .compare(this,delayMessage);
            }else {
                return Integer.MAX_VALUE;
            }
        }
    }

    public static List<DelayMessage> delayMessages= ImmutableList.of(
            new DelayMessage(1,"hello",1,TimeUnit.SECONDS),
            new DelayMessage(2,"payne",3,TimeUnit.SECONDS),
            new DelayMessage(3, "payno",1,TimeUnit.SECONDS)
    );

    public static class PriorityTest{
        public static void main(String[] args) {
            PriorityQueue<DelayMessage> priorityQueue=new PriorityQueue<>(delayMessages);
            System.out.println(priorityQueue.poll());
            System.out.println(priorityQueue.poll());
            System.out.println(priorityQueue.poll());
        }
    }

    public static class DelayTest{
        public static class Consumer extends Thread{
            DelayQueue<DelayMessage> delayMessageDelayQueue;
            public Consumer(DelayQueue<DelayMessage> delayQueue){
                this.delayMessageDelayQueue=delayQueue;
            }
            @Override
            public void run() {
                while(true){
                    try{
                        System.out.println(Thread.currentThread()+":"+delayMessageDelayQueue.take());
                        if(delayMessageDelayQueue.size()==0){
                            Thread.currentThread().interrupt();
                        }
                    }catch (InterruptedException e){
                        System.out.println("NoMessage still exists!");
                    }
                }
            }
        }

        public static void main(String[] args) {
            DelayQueue<DelayMessage> delayQueue=new DelayQueue<>(delayMessages);
            Consumer consumer=new Consumer(delayQueue);
            consumer.start();
        }
    }
}
