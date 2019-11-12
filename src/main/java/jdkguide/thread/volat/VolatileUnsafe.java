package jdkguide.thread.volat;

/**
 * @author payno
 * @date 2019/11/5 10:18
 * @description
 */
public class VolatileUnsafe {
    public static class VolatileVar implements Runnable{
        private Boolean flag=true;
        @Override
        public void run() {
            for (;;) {
                if(flag){
                    System.out.printf("[%s]:[%s]",Thread.currentThread().getName(),flag);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        VolatileVar volatileVar=new VolatileVar();
        Thread thread1=new Thread(volatileVar);
        Thread thread2=new Thread(volatileVar);
        Thread thread3=new Thread(volatileVar);
        thread1.start();
        thread2.start();
        thread3.start();
        //线程1，2，3进行休眠状态，无法看见停止标签
        Thread.sleep(1000);
        volatileVar.flag=false;
        System.out.println("I sleep 3s");
        Thread.sleep(3000);
        System.out.println("I will start now");
        volatileVar.flag=true;
    }
}
