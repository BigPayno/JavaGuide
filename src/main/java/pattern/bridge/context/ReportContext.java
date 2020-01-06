package pattern.bridge.context;

import pattern.bridge.dto.ReportSyncDTO;
import pattern.bridge.dto.esb.ReportApplyReqEO;
import pattern.bridge.dto.esb.ReportApplyResEO;
import pattern.bridge.dto.esb.ReportResultReqEO;
import pattern.bridge.dto.esb.ReportResultResEO;

/**
 * @author payno
 * @date 2020/1/6 09:02
 * @description
 */
public interface ReportContext {
    void setIdCard(String idCard);
    String getIdCard();
    void setSeqNo(String seqNo);
    String getSeqNo();
    void setReportData(String reportData);
    String getReportData();
    ReportApplyReqEO getReportApplyReqEO();
    ReportApplyResEO getReportApplyResEO();
    ReportResultReqEO getReportResultReqEO();
    ReportResultResEO getReportResultResEO();
    ReportApplyReqEO buildReportApplyReqEO();
    ReportResultReqEO buildReportResultReqEO();
    ReportSyncDTO buildReportSyncDTO();
}
