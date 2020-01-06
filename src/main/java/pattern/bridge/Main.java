package pattern.bridge;

import org.junit.Test;
import pattern.bridge.context.MockReportContext;
import pattern.bridge.context.ReportContext;
import pattern.bridge.exception.ReportException;

import java.util.concurrent.CompletableFuture;

/**
 * @author payno
 * @date 2020/1/6 09:15
 * @description
 */
public class Main {
    ReportSyncer reportSyncer=new MockReportSyncer();
    ReportContext context=new MockReportContext();
    @Test
    public void test() {
        context.setIdCard("320723199611193816");
        boolean isCacheExisting
                = reportSyncer.findCacheReportFromResolveApp(context.getIdCard());
        if(!isCacheExisting){
            try{
                context= reportSyncer.findReportResultFromEsbTimes(context,3);
            }catch (ReportException e){
                //Todo
            }
        }
    }
}
