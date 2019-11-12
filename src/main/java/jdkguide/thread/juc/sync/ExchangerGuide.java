package jdkguide.thread.juc.sync;

import java.util.concurrent.Exchanger;

/**
 * @author payno
 * @date 2019/11/9 11:43
 * @description
 */
public class ExchangerGuide {
    public static class Message<V>{
        V v;
        public void setV(V v){
            this.v=v;
        }
        public V getV(){
            return v;
        }
    }
    public static class Producer implements Runnable {
        private final Exchanger<Message> exchanger;

        public Producer(Exchanger<Message> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            Message message = new Message();
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(1000);

                    message.setV(String.valueOf(i));
                    System.out.println(Thread.currentThread().getName() + ": 生产了数据[" + i + "]");

                    message = exchanger.exchange(message);

                    System.out.println(Thread.currentThread().getName() + ": 交换得到数据[" + String.valueOf(message.getV()) + "]");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public static class Consumer implements Runnable {
        private final Exchanger<Message> exchanger;

        public Consumer(Exchanger<Message> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            Message msg = new Message();
            while (true) {
                try {
                    Thread.sleep(1000);
                    msg = exchanger.exchange(msg);
                    System.out.println(Thread.currentThread().getName() + ": 消费了数据[" + msg.getV() + "]");
                    msg.setV(null);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Exchanger<Message> exchanger = new Exchanger<>();
        Thread t1 = new Thread(new Consumer(exchanger), "消费者-t1");
        Thread t2 = new Thread(new Producer(exchanger), "生产者-t2");

        t1.start();
        t2.start();
    }
}
