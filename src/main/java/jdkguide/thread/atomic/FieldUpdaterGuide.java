package jdkguide.thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author payno
 * @date 2019/11/5 09:44
 * @description
 */
public class FieldUpdaterGuide {
    public static void main(String[] args) {
        Provider provider=new Provider();
        AtomicIntegerFieldUpdater.newUpdater(Provider.class,"count").incrementAndGet(provider);
    }
}
