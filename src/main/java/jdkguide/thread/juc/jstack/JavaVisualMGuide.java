package jdkguide.thread.juc.jstack;

/**
 * @author payno
 * @date 2019/11/16 15:13
 * @description
 */
public class JavaVisualMGuide {
    public static class ThreadTest{
        public static class BlockResource{
            public synchronized void block(){
                try {
                    wait();
                }catch (Exception e){
                }
            }
        }
        public static void main(String[] args) throws Exception{
            BlockResource blockResource=new BlockResource();
            new Thread(()->{
                blockResource.block();
            },"TEST_THREAD").start();
        }
    }
}
