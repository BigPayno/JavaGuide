package pattern.patterns.report.spi.report.template;

import pattern.patterns.report.spi.esb.V2EsbRes;
import pattern.patterns.report.spi.esb.report.ReportApplyEsbReq;
import pattern.patterns.report.spi.esb.report.ReportApplyEsbRes;
import pattern.patterns.report.spi.esb.report.ReportResultEsbReq;
import pattern.patterns.report.spi.esb.report.ReportResultEsbRes;

/*
 * 征信报告查询的模板方法接口
 */
public interface IReportTemplate {
	/*
	 * 查询数据平台缓存征信报告
	 */
	boolean findCacheReportFromResolveApp(String idCard,String mobile);
	/*
	 * 征信报告查询申请请求
	 */
	V2EsbRes<ReportApplyEsbRes> sendReportQueryApplyToEsb(ReportApplyEsbReq reportApplyEsbReq)
		throws Exception;
	/*
	 * 征信报告结果查询请求
	 */
	V2EsbRes<ReportResultEsbRes> findReportResultFromEsb(ReportResultEsbReq reportResultEsbReq)
		throws Exception;
	/*
	 * 同步征信报告至数据平台,返回请求结果
	 */
	String syncReportToResolveApp
		(String userName,String mobile,String idCard,String reportJson)
		throws Exception;
}