package jdkguide.references;

import java.lang.ref.SoftReference;
import java.util.WeakHashMap;

/**
 * @author payno
 * @date 2019/8/20 20:28
 * @description JVM只会在内存不足的情况下回收该对象。
 */
public class SoftReferenceGuide {
    public static void main(String[] args) {
        SoftReference<String> str=new SoftReference<>("abc");
        System.out.println(str.get());
        System.gc();
        System.out.println(str.get());
    }
}
