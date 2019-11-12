package jdkguide.thread.juc.locks;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author payno
 * @date 2019/11/9 10:25
 * @description
 */
public class FIFOMutexLock implements Lock {
    private final AtomicBoolean locked = new AtomicBoolean(false);
    private final Queue<Thread> waiters = new ConcurrentLinkedQueue<Thread>();
    @Override
    public void lock() {
        Thread current = Thread.currentThread();
        waiters.add(current);
        // 如果当前线程不在队首，或锁已被占用，则当前线程阻塞
        // NOTE：这个判断的意图其实就是：锁必须由队首元素拿到
        while (waiters.peek() != current || !locked.compareAndSet(false, true)) {
            LockSupport.park(this);
        }
        waiters.remove(); // 删除队首元素
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
    }

    @Override
    public boolean tryLock() {
        Thread current=Thread.currentThread();
        waiters.add(current);
        while(waiters.peek() != current){
            LockSupport.park(this);
        }
        boolean acquire=locked.compareAndSet(false, true);
        if(acquire){
            waiters.remove();
        }
        return acquire;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        locked.set(false);
        LockSupport.unpark(waiters.peek());
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
