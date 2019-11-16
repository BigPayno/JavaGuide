package jdkguide.thread.juc.locks;

/**
 * @author payno
 * @date 2019/11/9 10:29
 * @description
 */
public class FIFOMutexGuide {
    public static class Consumer extends Thread{
        private Window window;
        public Consumer(Window window){
            this.window=window;
        }
        @Override
        public void run() {
            window.consume();
        }
    }
    public static void main(String[] args) {
        Window window=new Window();
        Consumer consumer=new Consumer(window);
        Consumer consumer1=new Consumer(window);
        Consumer consumer2=new Consumer(window);
        consumer.start();
        consumer1.start();
        consumer2.start();
    }
}
