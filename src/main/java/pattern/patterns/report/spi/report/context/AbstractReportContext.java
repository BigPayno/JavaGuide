package pattern.patterns.report.spi.report.context;

import java.util.function.Consumer;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.base.Strings;
import pattern.patterns.report.spi.esb.V2EsbRes;
import pattern.patterns.report.spi.esb.report.ReportApplyEsbReq;
import pattern.patterns.report.spi.esb.report.ReportApplyEsbRes;
import pattern.patterns.report.spi.esb.report.ReportResultEsbReq;
import pattern.patterns.report.spi.esb.report.ReportResultEsbRes;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"response","reportApplyEsbRes","reportResultEsbRes","reportApplyEsbReq","reportResultEsbReq"})
public abstract class AbstractReportContext<T extends AbstractReportContext<T>> 
implements IReportContext<T>{
	
	protected long retryForApply;
	protected long retryForApplyInterval;
	
	protected long waitTimeOnGettingReportSeq;
	
	protected long retryForResult;
	protected long retryForResultInterval;
	
	protected long retryForSync;
	protected long retryForSyncInterval;
	protected boolean reportSyncToResolveSuccess;
	
	protected String seqNo;
	protected String report;
	protected String idCard;
	protected String userName;
	protected String mobile;
	protected String signEncry;
	
	@JSONField(serialize = false)
	protected String response;
	@JSONField(serialize = false)
	protected V2EsbRes<ReportApplyEsbRes> reportApplyEsbRes;
	@JSONField(serialize = false)
	protected V2EsbRes<ReportResultEsbRes> reportResultEsbRes;
	@JSONField(serialize = false)
	protected ReportApplyEsbReq reportApplyEsbReq;
	@JSONField(serialize = false)
	protected ReportResultEsbReq reportResultEsbReq;
	
	@Override
	public ReportApplyEsbReq buildApplyEsbReq() {
		if(reportApplyEsbReq==null) {
			reportApplyEsbReq=new ReportApplyEsbReq();
			reportApplyEsbReq.setID_TP_CD(ReportEnums.ID_TP_CD.第二代居民身份证);
			reportApplyEsbReq.setQRY_POLC(ReportEnums.QRY_POLC.本地优先);
			reportApplyEsbReq.setID_NO(getIdCard());
			reportApplyEsbReq.setCN_NM(getUserName());
			reportApplyEsbReq.setAUT_IND(signEncry);
		}
		return reportApplyEsbReq;
	}
	
	@Override
	public ReportResultEsbReq buildReportResultEsbReq() {
		if(reportResultEsbReq==null) {
			reportResultEsbReq=new ReportResultEsbReq();
			reportResultEsbReq.setAPLCN_TXN_SEQ_NO(getSeqNo());
			reportResultEsbReq.setRSLT_TP(ReportEnums.RSLT_TP.JSON结果报文);
			reportResultEsbReq.setAUT_IND(signEncry);
		}
		return reportResultEsbReq;
	}

	@Override
	public boolean isReportApplyEsbSuccess() {
		return !Strings.isNullOrEmpty(seqNo);
	}

	@Override
	public boolean isReportResultEsbSuccess() {
		return !Strings.isNullOrEmpty(report);
	}

	@Override
	public boolean hasRetryTimes() {
		if(!isReportApplyEsbSuccess()) {
			return retryForApply>=0;
		}else {
			if(!isReportResultEsbSuccess()) {
				return retryForResult>=0;
			}else {
				return retryForSync>=0;
			}
		}
	}

	@Override
	public void reportApplyQueryFail() {
		retryForApply--;
	}

	@Override
	public void reportResultQueryFail() {
		retryForResult--;
	}

	@Override
	public void reportSyncApplyFail() {
		retryForSync--;
	}

	@Override
	public void handle(Consumer<IReportContext<?>> action) {
		action.accept(this);
	}

	@Override
	public long getInterval() {
		if(!isReportApplyEsbSuccess()) {
			return retryForApplyInterval;
		}
		if(!isReportResultEsbSuccess()) {
			return retryForResultInterval;
		}
		return retryForSyncInterval;	
	}

	@Override
	public long getRetryTimes() {
		if(!isReportApplyEsbSuccess()) {
			return retryForApply;
		}
		if(!isReportResultEsbSuccess()) {
			return retryForResult;
		}
		return retryForSync;
	}
	
}
