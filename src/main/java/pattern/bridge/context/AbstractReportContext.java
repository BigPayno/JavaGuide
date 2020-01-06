package pattern.bridge.context;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pattern.bridge.dto.esb.ReportApplyReqEO;
import pattern.bridge.dto.esb.ReportApplyResEO;
import pattern.bridge.dto.esb.ReportResultReqEO;
import pattern.bridge.dto.esb.ReportResultResEO;

/**
 * @author payno
 * @date 2020/1/6 10:18
 * @description
 */
@Setter
@Getter
public abstract class AbstractReportContext implements ReportContext{
    String idCard;
    String seqNo;
    String reportData;
    ReportApplyReqEO reportApplyReqEO;
    ReportApplyResEO reportApplyResEO;
    ReportResultReqEO reportResultReqEO;
    ReportResultResEO reportResultResEO;
}
