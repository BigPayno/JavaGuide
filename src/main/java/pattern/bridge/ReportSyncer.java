package pattern.bridge;

import pattern.bridge.context.ReportContext;
import pattern.bridge.exception.ReportException;

/**
 * @author payno
 * @date 2020/1/6 08:51
 * @description
 */
public abstract class ReportSyncer implements ReportTemplate{

    public ReportContext sendReportQueryApplyToEsbTimes(ReportContext context, int times) throws ReportException {
        String reportSeqNo;
        try{
            times--;
            reportSeqNo=sendReportQueryApplyToEsb(context.buildReportApplyReqEO());
            context.setSeqNo(reportSeqNo);
        }catch (ReportException e) {
            if (times > 0) {
                sendReportQueryApplyToEsbTimes(context, times);
            } else {
                throw new ReportException();
            }
        }
        return context;
    }

    public ReportContext findReportResultFromEsbTimes(ReportContext context, int times) throws RuntimeException {
        String reportData;
        try{
            times--;
            reportData=findReportResultFromEsb(context.buildReportResultReqEO());
            context.setReportData(reportData);
            //这里可以利用事件回调拓展
        }catch (ReportException e) {
            if (times > 0) {
                System.out.println(times);
                findReportResultFromEsbTimes(context,times);
            } else {
                throw new ReportException();
            }
        }
        return context;
    }

    public void syncReportToResolveApp(ReportContext context) {
        syncReportToResolveApp(context.buildReportSyncDTO());
    }
}
