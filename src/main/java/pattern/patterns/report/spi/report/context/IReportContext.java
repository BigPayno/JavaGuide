package pattern.patterns.report.spi.report.context;

import java.util.function.Consumer;

import pattern.patterns.report.spi.cache.ICachable;
import pattern.patterns.report.spi.esb.V2EsbRes;
import pattern.patterns.report.spi.esb.report.ReportApplyEsbReq;
import pattern.patterns.report.spi.esb.report.ReportApplyEsbRes;
import pattern.patterns.report.spi.esb.report.ReportResultEsbReq;
import pattern.patterns.report.spi.esb.report.ReportResultEsbRes;

/*
 * 提供统一的征信报文查询上下文接口,泛型使用自限定泛型
 * @LimitApplyReportContext
 * @WarningApplyReportContext
 */
public interface IReportContext<T extends IReportContext<T>> extends ICachable{
	/*
	 * 保存和获取SeqNo、Report的能力
	 */
	void setSeqNo(String seqNo);
	String getSeqNo();
	void setReport(String report);
	String getReport();
	/*
	 * 创建同步解析平台请求的能力
	 */
	String getIdCard();
	String getUserName();
	String getMobile();
	void setResponse(String response);
	String getResponse();
	
	/*
	 * 为了方便重试机制
	 */
	boolean hasRetryTimes();
	void reportApplyQueryFail();
	void reportResultQueryFail();
	void reportSyncApplyFail();
	
	void setRetryForApply(long times);
	void setRetryForResult(long times);
	void setRetryForSync(long times);
	long getRetryTimes();
	
	void setWaitTimeOnGettingReportSeq(long interval);
	long getWaitTimeOnGettingReportSeq();
	void setRetryForApplyInterval(long interval);
	void setRetryForResultInterval(long interval);
	void setRetryForSyncInterval(long interval);
	long getInterval();
	
	boolean isReportApplyEsbSuccess();
	boolean isReportResultEsbSuccess();
	void setReportSyncToResolveSuccess(boolean val);
	boolean isReportSyncToResolveSuccess();
	
	void handle(Consumer<IReportContext<?>> consumer);
	
	/*
	 * 根据已有上下文创建征信报告请求的能力
	 */
	ReportApplyEsbReq buildApplyEsbReq();
	ReportResultEsbReq buildReportResultEsbReq();
	
	/*
	 * 保存和获取请求响应的能力
	 */
	void setReportApplyEsbRes(V2EsbRes<ReportApplyEsbRes> reportApplyEsbRes);
	V2EsbRes<ReportApplyEsbRes> getReportApplyEsbRes();
	void setReportResultEsbRes(V2EsbRes<ReportResultEsbRes> reportResultEsbRes);
	V2EsbRes<ReportResultEsbRes> getReportResultEsbRes();
}
