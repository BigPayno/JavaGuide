package jdkguide.comlpetablefature;

import jdkguide.thread.Threads;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author payno
 * @date 2020/1/7 14:46
 * @description
 */
@Slf4j
public class CompletableFutureMain {
    public String syncReportBeforeLimitApply(String base){
        Threads.sleep(1000);
        log.info("syncReportBeforeLimitApply");
        if(base.equals("null")){
            throw new RuntimeException();
        }
        return base;
    }
    public String executeRiskRuleEngine(String base){
        Threads.sleep(1000);
        log.info("executeRiskRuleEngine");
        return base;
    }

    public void callbackAfterLimitApplySuccess(String base){
        Threads.sleep(1000);
        log.info("callbackAfterLimitApplySuccess");
    }

    public void  handleLimitApplyWhenComplete(String base,Throwable e){
        log.info("handleLimitApplyWhenComplete");
    }

    @Test
    public void test(){
        String in="null";
        CompletableFuture.supplyAsync(()->syncReportBeforeLimitApply(in))
                .thenApplyAsync(base->executeRiskRuleEngine(base))
                .whenCompleteAsync((base,e)->{
                   if(e!=null){
                       handleLimitApplyWhenComplete(base,e);
                   }else{
                       callbackAfterLimitApplySuccess(base);
                   }
                });
        log.info("run async!");
        Threads.sleep(5000);
    }
}
