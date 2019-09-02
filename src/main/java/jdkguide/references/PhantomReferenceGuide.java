package jdkguide.references;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author payno
 * @date 2019/8/20 20:50
 * @description 【1】当垃圾回收器准备回收一个对象时，如果发现它与虚引用关联，就会在回收它之前，将这个虚引用加入到引用队列中。程序可以通过判断引用队列中是否已经加入了虚引用
 * ，来了解被引用的对象是否将要被回收，如果确实要被回收，就可以做一些回收之前的收尾工作。
 */
public class PhantomReferenceGuide {
    public static void main(String[] args) {
        ReferenceQueue<String> queue=new ReferenceQueue<>();
        PhantomReference<String> str=new PhantomReference<>(new String("abc"),queue);
        System.out.println(str.get());
    }
}
