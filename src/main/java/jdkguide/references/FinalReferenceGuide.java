package jdkguide.references;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author payno
 * @date 2019/8/20 20:30
 * @description 系统宁愿抛出OutOfMemoryError也不会抛弃引用
 */
public class FinalReferenceGuide {
    public static void main(String[] args) {
        /**
         * 只要某个对象与强引用关联，那么JVM在内存不足的情况下，宁愿抛出o
         * utOfMemoryError错误，也不会回收此类对象。
         * 【1】如果我们想要JVM回收此类被强引用关联的对象，
         * 我们可以显示地将str置为null，那么JVM就会在合适的时间回收此对象。
         * 或者将引用指向其他对象
         */
        String str="abc";
        str=null;
    }
}
