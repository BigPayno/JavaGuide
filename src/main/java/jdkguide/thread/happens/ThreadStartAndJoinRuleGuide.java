package jdkguide.thread.happens;

import jdkguide.thread.Threads;
import lombok.Data;
import org.junit.Test;

/**
 * @author payno
 * @date 2019/12/12 10:28
 * @description
 *      线程join停止线程，会刷新join线程至内存并同步主存
 */
public class ThreadStartAndJoinRuleGuide {
    @Data
    public class Word{
        private String word;
    }
    @Test
    public void test() throws InterruptedException{
        Word word=new Word();
        word.setWord("Init by Constructor");
        Runnable runnable=()->{
            System.out.println(word.getWord());
            Threads.sleep(1000);
            word.setWord("Update by async Thread");
        };
        Thread thread=new Thread(runnable);
        /**
         * 线程启动准则
         */
        thread.start();
        /**
         * 主线程阻塞直到线程执行结束，线程终止准则
         */
        thread.join();
        System.out.println(word.getWord());
    }
}
