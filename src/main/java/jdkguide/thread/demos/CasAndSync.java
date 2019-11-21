package jdkguide.thread.demos;

import org.junit.Test;

/**
 * @author payno
 * @date 2019/11/21 08:30
 * @description
 *      Cas和Sync是保证原子性的方式
 *      volatile是保证可见性的方式
 */
public class CasAndSync {
    public interface Access{
        void readAndAdd();
    }
    public class Sync implements Access{
        private int count=0;
        @Override
        public synchronized void readAndAdd(){
            count++;
            System.out.printf("[%s] read result [%d]%n",Thread.currentThread().getName(),count);
        }
    }


    /**
     * Error cas的使用请看SpinLock里的运用
     */
    public class Cas implements Access{
        /**
         * 保证可见性
         */
        private volatile int count=0;
        /**
         * 保证原子性
         */
        private volatile int semaphore=1;
        @Override
        public void readAndAdd(){
            /**
             * 这里哎,update是需要更新的值，expect是期待的结果
             * 好坑,貌似semaphore的值不会改变
             * 但是cas和volatile配合是对的
             */
          if(!compareAndSet(1,0)){
              /**
               * 为了保证count的操作是原子性
               */
              addCount();
          }
        }

        public synchronized void addCount(){
            System.out.println(Thread.currentThread().getName()+"进入原子操作");
            count++;
            System.out.printf("[%s] read result [%d]%n",Thread.currentThread().getName(),count);
            compareAndSet(1,0);
            System.out.println(Thread.currentThread().getName()+"退出原子操作");
        }

        public final boolean compareAndSet(int expect, int update) {
            return UnSafes.get().compareAndSwapInt(this, semaphore, expect, update);
        }
    }

    public class Accessor implements Runnable{
        public Accessor(Access access){
            this.access=access;
        }
        private Access access;
        @Override
        public void run() {
            while(true){
                access.readAndAdd();
            }
        }
    }

    /**
     * 利用同步的方式实现原子性，既只有一个线程在同一时间能够访问
     */
    @Test
    public void sync() throws Exception{
        Access access=new Sync();
        Accessor accessor1=new Accessor(access);
        Accessor accessor2=new Accessor(access);
        new Thread(accessor1).start();
        new Thread(accessor2).start();
        /**
         * 保证测试时间够长
         */
        Thread.sleep(20);
    }

    /**
     * 通过cas保证
     */
    @Test
    public void cas() throws Exception{
        Access access=new Cas();
        Accessor accessor1=new Accessor(access);
        new Thread(accessor1).start();
        new Thread(accessor1).start();
        new Thread(accessor1).start();
        /**
         * 保证测试时间够长
         */
        Thread.sleep(20);
    }
}
