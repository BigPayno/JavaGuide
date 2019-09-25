package guava.time;

import com.google.common.base.Stopwatch;
import com.google.common.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.function.Consumer;

/**
 * @author payno
 * @date 2019/9/16 16:08
 * @description
 */
@Slf4j
public class StopWatchGuide {
    public static void main(String[] args) {
        StopWatch.createStarted().invokeAction(w->{
            log.info("开始执行任务！！！");
            try {
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }).stop(stopwatch -> {
            log.info("共计耗时[{}]",stopwatch);
        });
        TypeToken<String> typeToken=TypeToken.of(String.class);
    }
}
