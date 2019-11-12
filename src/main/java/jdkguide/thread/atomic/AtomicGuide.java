package jdkguide.thread.atomic;

import com.google.common.collect.ImmutableList;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author payno
 * @date 2019/11/5 09:42
 * @description
 */
public class AtomicGuide {
    public static void main(String[] args) {
        ImmutableList.copyOf(new Class[]{
                AtomicBoolean.class,
                AtomicInteger.class,
                AtomicReference.class
        });
    }
}
