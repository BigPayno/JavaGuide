package jdkguide.thread.spinlock;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author payno
 * @date 2019/11/7 10:19
 * @description
 *  相当于多线程版本的if语句，等待段拥有守护条件，不满足就进入等待，释放锁
 *  满足就执行相关方法。
 *
 *  RequestQueue有jdk实现的集合BlockQueue接口，如LinkedBlockQueue
 *
 *  当队列为空，阻塞调用该对象的线程，使之进入阻塞状态
 *  当队列插入元素，唤醒线程
 */
public class BlockQueueGuide {
    private BlockingQueue<Request> blockingQueue=new LinkedBlockingQueue<>();
}
