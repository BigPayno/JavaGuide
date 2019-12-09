package jdkguide.lang;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author payno
 * @date 2019/12/7 16:36
 * @description
 */
public class ObjGuide {
    public void mon(T t){
        /**
         * 证明这里的t指向的是t0，
         * 如果指向对象，那么对象应该被修改
         * t->y0->1还是t->1?应该是第二种
         * t->2
         * 引用->地址->对象
         * 不是引用->引用->对象
         */
        t=new T(2);
    }

    @Test
    public void test1(){
        /**
         * 引用传递是否能改变原本的值
         */
        T t0=new T(1);
        System.out.println(t0);
        mon(t0);
        System.out.println(t0);
    }

    @Test
    public void test2(){
        T t=new T(1);
        t=new T(2);
        System.out.println(t);
    }

    public void sleep(long millis){
        try{
            Thread.sleep(millis);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void mon(AtomicReference<T> t){
        new Thread(()->{
            while (true){
                sleep(500);
                System.out.println(t.get());
            }
        }).start();
    }

    /**
     * 确定了，引用->地址->内存
     * 不是 引用->引用->内存
     */
    @Test
    public void test3(){
        AtomicReference<T> t=new AtomicReference<>(new T(1));
        mon(t);
        sleep(1200);
        t=new AtomicReference<>(new T(2));
        System.out.println(t.get());
        sleep(1000);

    }
}
