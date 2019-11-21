package jdkguide.thread.demos;

import org.junit.Test;
import sun.misc.Unsafe;

/**
 * @author payno
 * @date 2019/11/21 10:09
 * @description
 */
public class SpinLock {
    public interface Access{
        void readAndAdd();
    }
    public class Sync implements Access {
        public Sync(Thread notFirstThread){
            last=notFirstThread;
        }
        private int count=0;
        volatile Thread last;
        @Override
        public void readAndAdd(){
            /**
             * 这里类似自旋，如果last是当前线程就直接通过
             * Thread 1 上次获取锁，last量应该就是Thread 1，通过volatile保证可见性
             * 这时Thread 1 再次执行该方法不会被阻塞，而是跳过同步方法
             * 这样做特点是执行一次方法并不保证执行同步方法
             * 保证了原子性同时减少了线程从wait，await状态转变称ready的线程切换
             * 算是Aqs的萌芽，利用可见同步队列保证可见性和控制线程运行与否
             * 利用cas或者sync保证原子性
             * 必须保证第一次进入循环时，不会竞争，必须指定开始执行的线程，或者第一次还是要竞争的
             */
            if(last!=Thread.currentThread()){
                //System.out.println(Thread.currentThread().getName()+"进入操作");
                atomic();
            }else{
                //System.out.println(Thread.currentThread().getName()+"自旋中");
            }
        }

        public synchronized void atomic(){
            try{
                if(last==Thread.currentThread()){
                    wait();
                }
                count++;
                System.out.printf("[%s] read result [%d]%n",Thread.currentThread().getName(),count);
                last=Thread.currentThread();
                notifyAll();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static class Cas implements Access {
        static Unsafe unsafe=UnSafes.get();
        private static long lastOffSet;
        private static long countOffSet;
        private static long semaphoreOffSet;
        static {
            try{
                lastOffSet=unsafe.objectFieldOffset(Cas.class.getDeclaredField("last"));
                countOffSet=unsafe.objectFieldOffset(Cas.class.getDeclaredField("count"));
                semaphoreOffSet=unsafe.objectFieldOffset(Cas.class.getDeclaredField("semaphore"));
            }catch (Exception er){
                er.printStackTrace();
            }
        }
        int count=0;
        /**
         * 控制线程运行方式，Aqs的萌芽
         */
        volatile Thread last;
        /**
         * 控制线程的进出，只允许2个进程访问，通过cas保证原子性
         */
        volatile long semaphore=1;
        @Override
        public void readAndAdd(){
            if(compareAndSetSemaphore(1,0)){
                System.out.println(semaphore);
            }else{
                compareAndSetSemaphore(0,1);
            }
        }

        public boolean compareAndSetSemaphore(long except,long update){
            return unsafe.compareAndSwapLong(this,semaphoreOffSet,except,update);
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
     * 利用自旋锁的方法，使sync不再等待，而是自旋
     */
    @Test
    public void test() throws Exception{
        Thread thread=null;
        Access access=new Sync(thread);
        Accessor accessor=new Accessor(access);
        thread=new Thread(accessor);
        thread.start();
        new Thread(accessor).start();
        Thread.sleep(20);
    }

    @Test
    public void test2() throws Exception{
        Access access=new Cas();
        Accessor accessor=new Accessor(access);
        new Thread(accessor);
        new Thread(accessor).start();
        Thread.sleep(20);
    }
}
