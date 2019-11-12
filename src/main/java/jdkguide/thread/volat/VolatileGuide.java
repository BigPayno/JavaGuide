package jdkguide.thread.volat;

/**
 * @author payno
 * @date 2019/11/5 10:45
 * @description
 */
public class VolatileGuide {
    public static class Provider{
        private boolean stop=true;
    }
    public static class Consumer implements Runnable{
        public Consumer(Provider provider){
            this.provider=provider;
        }
        private Provider provider;
        @Override
        public void run() {
            while(true){
                if(!provider.stop){
                    System.out.println("do something");
                }
            }
        }
    }
    public static class Consumer2 implements Runnable{
        public Consumer2(Provider provider){
            this.provider=provider;
        }
        private Provider provider;
        @Override
        public void run() {
            provider.stop=!provider.stop;
        }
    }
    public static void main(String[] args) throws Exception{
        Provider provider=new Provider();
        Consumer consumer=new Consumer(provider);
        Consumer2 consumer2=new Consumer2(provider);
        for (int i = 0; i <1<<2 ; i++) {
            new Thread(consumer).start();
        }
        Thread.sleep(500);
        //consumer2对变量的改变不会对线程1可见
        new Thread(consumer2).start();

}
}
