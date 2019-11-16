package jdkguide.thread.juc.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author payno
 * @date 2019/11/16 14:27
 * @description
 */
public class AbstractQueuedSynchronizerGuide {
    public static class FastStart{
        public static void main(String[] args) {
            /**
             *  AQS<>--|-->Node head,tail
             *        |-->int state ::setState|getState|compareAndSetState
             *        enq(Node node) 自旋直到cas成功，返回上一个尾结点
             *
             *
             */
        }
    }
}
