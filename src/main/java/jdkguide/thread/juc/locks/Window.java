package jdkguide.thread.juc.locks;

/**
 * @author payno
 * @date 2019/11/9 10:34
 * @description
 */
public class Window {
    private final FIFOMutexLock lock=new FIFOMutexLock();
    public void consume(){
        while(true) {
            if (lock.tryLock()) {
                try {
                    System.out.println(Thread.currentThread().getName() + "成功买到东西");
                    Thread.sleep(200);
                    break;
                }catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + "等待中");
            }
        }
    }
}
