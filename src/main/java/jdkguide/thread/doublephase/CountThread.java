package jdkguide.thread.doublephase;

import lombok.Getter;

/**
 * @author payno
 * @date 2019/11/8 16:07
 * @description
 */
@Getter
public class CountThread extends Thread{
    private long counter=0;
    private volatile boolean shutDown=false;
    public void shutDown(){
        shutDown=true;
        interrupt();
    }
    @Override
    public void run() {
        try{
            while(!shutDown){
                doWork();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            doShutdown();
        }
    }

    private void doWork() throws InterruptedException{
        counter++;
        System.out.println("doWork: counter = " + counter);
        Thread.sleep(500);
    }

    private void doShutdown() {
        System.out.println("doShutdown: counter = " + counter);
    }
}
