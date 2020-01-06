package pattern.bridge;

import jdkguide.thread.Threads;
import lombok.extern.slf4j.Slf4j;
import pattern.bridge.dto.ReportSyncDTO;
import pattern.bridge.dto.esb.ReportApplyReqEO;
import pattern.bridge.dto.esb.ReportResultReqEO;
import pattern.bridge.exception.ReportException;

/**
 * @author payno
 * @date 2020/1/6 10:12
 * @description
 */
@Slf4j
public class MockReportSyncer extends ReportSyncer {
    @Override
    public boolean findCacheReportFromResolveApp(String IdCard) {
        Threads.sleep(500);
        log.info("解析平台不存在缓存征信报文[IdCard=({})]",IdCard);
        return false;
    }

    @Override
    public String sendReportQueryApplyToEsb(ReportApplyReqEO esbReq) throws RuntimeException {
        String seqNo="3207";
        Threads.sleep(1000);
        log.info("发送个人征信报告查询申请请求,返回流水号[SeqNo=({})]",seqNo);
        return seqNo;
    }

    @Override
    public String findReportResultFromEsb(ReportResultReqEO esbReq) throws RuntimeException {
        String reportData="xml";
        Threads.sleep(5000);
        log.info("发送个人征信报告结果查询请求,返回报文[Data=({})]",reportData);
        return reportData;
    }

    @Override
    public void syncReportToResolveApp(ReportSyncDTO syncDTO) {
        Threads.sleep(2000);
        log.info("同步征信报文至解析平台[{}]",syncDTO);
    }
}
