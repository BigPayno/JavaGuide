package pattern.bridge.context;

import pattern.bridge.dto.ReportSyncDTO;
import pattern.bridge.dto.esb.ReportApplyReqEO;
import pattern.bridge.dto.esb.ReportResultReqEO;

/**
 * @author payno
 * @date 2020/1/6 10:21
 * @description
 */
public class MockReportContext extends AbstractReportContext{

    @Override
    public ReportApplyReqEO buildReportApplyReqEO() {
        return null;
    }

    @Override
    public ReportResultReqEO buildReportResultReqEO() {
        return null;
    }

    @Override
    public ReportSyncDTO buildReportSyncDTO() {
        ReportSyncDTO dto= new ReportSyncDTO();
        dto.setIdCard(getIdCard());
        dto.setReportData(getReportData());
        return dto;
    }
}
