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
                    str4Plus.length();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void methodReferenceLifeCycle(WeakReference<String> str7){
        String str7Plus=str7.get();
    }

    @Test
    public void test2() throws Exception{
        WeakReference<String> str1=new WeakReference<>(new String("a"));
        WeakReference<String> str2=new WeakReference<>(new String("b"));
        WeakReference<String> str3=new WeakReference<>(new String("c"));
        WeakReference<String> str4=new WeakReference<>(new String("d"));
        WeakReference<String> str5=new WeakReference<>(new String("e"));
        WeakReference<String> str6=new WeakReference<>("f");
        WeakReference<String> str7=new WeakReference<>(new String("g"));
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
        async2(str5.get(),service);
        async(str4,service);
        methodReferenceLifeCycle(str7);
        System.gc();
        /**
         * 没有强引用保护，挂了
         */
        System.out.println(str1.get());
        /**
         * 被前面的强引用保护主了
         */
        System.out.println(str2.get());
        /**
         * 有强引用就不会被gc，但是如果异步线程执行比主线程慢
         * 可能异步线程拿到的对象已经被清除了
         * 被异步线程的强引用保护住了
         */
        System.out.println(str3.get());
        /**
         * 异步线程执行太慢，已经被主线程的gc干掉了
         */
        System.out.println(str4.get());
        /**
         * 证明了方法的引用是强引用，是引用链接你传入的引用或对象
         * 而不是链接你传入的对象，证明了引用链的存在
         * 引用被传到了异步线程里，得到保护
         */
        System.out.println(str5.get());
        /**
         * 很明显池化对象没有被gc
         */
        System.out.println(str6.get());
        /**
         * 说明方法内强引用的生命周期是执行完就释放了
         * 同理，5如果在异步线程里不使用引用，也会挂
         */
        System.out.println(str7.get());
    }
}
