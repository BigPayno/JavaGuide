package guava.time;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

/**
 * @author payno
 * @date 2019/9/16 16:30
 * @description
 */
@Slf4j
public class StopWatch {
    Stopwatch proxy;
    private StopWatch(Stopwatch proxy){
        this.proxy=proxy;
    }

    public static StopWatch createStarted(){
        StopWatch stopWatch=new StopWatch(Stopwatch.createStarted());
        return stopWatch;
    }
    public StopWatch invokeAction(Consumer<Stopwatch> action){
        action.accept(proxy);
        return this;
    }

    public StopWatch stop(Consumer<Stopwatch> action){
        action.accept(proxy);
        return this;
    }
}
