package jdkguide.thread.juc.locks;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author payno
 * @date 2019/11/9 10:16
 * @description
 */
@Slf4j
public class LockGuide {
    public static void main(String[] args) {
        Lock lock=new ReentrantLock();
        if(lock.tryLock()){
            try{
                log.info("获得锁，任务开始执行");
            }finally {
                lock.unlock();
            }
        }else{
            log.info("锁正被占用，任务已经执行");
        }
    }
}
