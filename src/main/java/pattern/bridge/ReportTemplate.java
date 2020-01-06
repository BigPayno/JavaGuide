package pattern.bridge;

import pattern.bridge.dto.ReportSyncDTO;
import pattern.bridge.dto.esb.ReportApplyReqEO;
import pattern.bridge.dto.esb.ReportResultReqEO;

/**
 * @author payno
 * @date 2020/1/6 08:43
 * @description
 */
public interface ReportTemplate {
    boolean findCacheReportFromResolveApp(String IdCard);
    String sendReportQueryApplyToEsb(ReportApplyReqEO esbReq) throws RuntimeException;
    String findReportResultFromEsb(ReportResultReqEO esbReq) throws RuntimeException;
    void syncReportToResolveApp(ReportSyncDTO syncDTO);
}
