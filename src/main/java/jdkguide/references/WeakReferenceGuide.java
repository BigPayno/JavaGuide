package jdkguide.references;

import java.lang.ref.WeakReference;

/**
 * @author payno
 * @date 2019/8/20 20:42
 * @description  如果某个对象与弱引用关联，那么当JVM在进行垃圾回收时，无论内存是否充足，都会回收此类对象。
 * 当gc线程发现某个对象只有弱引用指向它，那么就会将其销毁并回收内存
 */
public class WeakReferenceGuide {
    private static void test(){
        WeakReference<String> str=new WeakReference<>(new String("ABC"));
        System.out.println(str.get());
        System.gc();
        System.out.println(str.get());
    }
    public static class WeakString extends WeakReference<String>{
        WeakString(String referent){
            super(referent);
        }
        @Override
        public String toString(){
            return this.get();
        }
    }
    public static void main(String[] args) throws Exception{
        String abc=new String("abc");
        WeakString string1=new WeakString(abc);
        WeakString string2=new WeakString(new String("def"));
        System.out.println(string1);
        System.out.println(string2);
        System.gc();
        System.out.println(string1);
        System.out.println(string2);
        abc=null;
        System.gc();
        System.out.println(string1);
        System.out.println(string2);
        String def=new String("DEF");
    }
}
