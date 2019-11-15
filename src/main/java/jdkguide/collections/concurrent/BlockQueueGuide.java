package jdkguide.collections.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author payno
 * @date 2019/11/15 15:18
 * @description
 *  ArrayBlockingQueue extends AbstractQueue,implements BlockingQueue
 *      ReentrantLock Condition.forNotFull,Condition.forNotEmpty
 *      putIndex,takeIndex
 *  1.enqueue,dequeue private
 *      在当前Index插入或者拿出数据->下次操作是否到达边界，唤醒对应Condition的线程
 *  2.offer,poll public 还有offer,poll带时间Unit会加入Condition并可能抛出中断异常
 *      占有可重入锁情况下，通过enqueue和dequeue插入或者拿出数据，在边界条件下
 *      插入返回false，拿出返回null
 *  3.put,take public await InterruptedException
 *      插入或者取出，一直等待，会抛出中断异常
 *  4.peek
 *      取出takeIndex的元素
 */
public class BlockQueueGuide {
    public static class ArrayBlockingTest{
        public static void main(String[] args) throws Exception{
            BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(2);
            //方法1不会阻塞，返回null，方法2会阻塞，线程放弃锁
            //当执行blockingQueue的enqueue方法时，会唤醒所有notEmpty的线程去竞争锁
            //在关闭方法2时，方法3会去竞争3秒钟，没有就返回null
            System.out.println(blockingQueue.poll());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.poll(3, TimeUnit.SECONDS));
        }
    }

    public static class LinkedBlockingTest{
        public static void main(String[] args) {
            LinkedBlockingQueue<String> blockingQueue=new LinkedBlockingQueue<>(2);
        }
    }
}
