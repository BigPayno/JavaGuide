package jdkguide.references;

import com.google.common.base.FinalizableReference;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Test;

import java.lang.ref.WeakReference;
import java.util.concurrent.*;

/**
 * @author payno
 * @date 2019/12/6 22:19
 * @description
 */
public class MethodReferenceGuide {
    /**
     * 方法传递引用是这样的
     * str->pool["test"]
     * str2->str->["test"]
     * str2->un pool["testa"]
     */
    public void change(String str2){
        str2=str2+"a";
        System.out.println(str2);
    }

    public void change2(WeakReference<String> str3){
        System.out.println(str3.get());
    }

    @Test
    public void test() {
        String str="test";
        change(str);
        System.out.println(str);
        WeakReference<String> weakReference=new WeakReference<>(str);
        change2(weakReference);
        System.out.println(str);
    }

    public void async(WeakReference<String> str3,ExecutorService service){
        service.submit(()->{
            try{
                String str3Plus=str3.get();
                while (true){
                    Thread.sleep(1000);
                    System.out.println("runnable 3plus "+str3Plus);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void async2(String str4Plus,ExecutorService service){
        service.submit(()->{
            try{
                while (true){
                    Thread.sleep(1000);
                    System.out.println("runnable 4plus "+str4Plus);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    @Test
    public void test2() throws Exception{
        WeakReference<String> str1=new WeakReference<>(new String("a"));
        WeakReference<String> str2=new WeakReference<>(new String("b"));
        WeakReference<String> str3=new WeakReference<>(new String("c"));
        WeakReference<String> str4=new WeakReference<>(new String("d"));
        WeakReference<String> str5=new WeakReference<>(new String("e"));
        WeakReference<String> str6=new WeakReference<>("f");
        ExecutorService service=new ThreadPoolExecutor(2, 2,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
        MoreExecutors.addDelayedShutdownHook(service,10, TimeUnit.SECONDS);
        String str2Plus=str2.get();
        /**
         * 应该是主线程执行过快，异步线程还未执行到强引用
         * 等到异步线程执行到强引用时，对象已经被清除
         */
        async(str3,service);
        Thread.sleep(1000);
        async(str4,service);
        //async2(str5.get(),service);
        System.gc();
        System.out.println(str1.get());
        System.out.println(str2.get());
        System.out.println(str3.get());
        System.out.println(str4.get());
        System.out.println(str5.get());
        System.out.println(str6.get());
    }
}
