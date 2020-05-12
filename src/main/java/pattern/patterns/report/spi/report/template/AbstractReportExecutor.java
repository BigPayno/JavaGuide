package pattern.patterns.report.spi.report.template;

import pattern.patterns.report.spi.esb.V2EsbRes;
import pattern.patterns.report.spi.esb.exception.EsbException;
import pattern.patterns.report.spi.esb.report.ReportApplyEsbReq;
import pattern.patterns.report.spi.esb.report.ReportApplyEsbRes;
import pattern.patterns.report.spi.esb.report.ReportResultEsbReq;
import pattern.patterns.report.spi.esb.report.ReportResultEsbRes;
import pattern.patterns.report.spi.report.context.IReportContext;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractReportExecutor<T extends IReportContext<?>> implements IReportTemplate,IReportExecutor<T> {

	IReportTemplate reportTemplate;
	
	@Override
	public boolean findCacheReportFromResolveApp(String idCard,String mobile) {
		return reportTemplate.findCacheReportFromResolveApp(idCard,mobile);
	}

	@Override
	public V2EsbRes<ReportApplyEsbRes> sendReportQueryApplyToEsb(ReportApplyEsbReq reportApplyEsbReq)
			throws Exception {
		return reportTemplate.sendReportQueryApplyToEsb(reportApplyEsbReq);
	}

	@Override
	public V2EsbRes<ReportResultEsbRes> findReportResultFromEsb(ReportResultEsbReq reportResultEsbReq)
			throws Exception {
		return reportTemplate.findReportResultFromEsb(reportResultEsbReq);
	}

	@Override
	public String syncReportToResolveApp(String userName, String mobile, String idCard, String reportJson)
			throws Exception {
		return reportTemplate.syncReportToResolveApp(userName, mobile, idCard, reportJson);
	}
	
	@Override
	public boolean syncReportFromResolveApp(T t) {
		return findCacheReportFromResolveApp(t.getIdCard(),t.getMobile());
	}

	@Override
	public T syncReportFromEsbToResolveApp(T t) throws EsbException {
		if(!t.isReportApplyEsbSuccess()) {
			sendReportQueryApplyToEsb(t);
		}
		if(!t.isReportResultEsbSuccess()) {
			findReportResultFromEsb(t);
		}
		if(!t.isReportSyncToResolveSuccess()) {
			syncReportToResolveApp(t);
		}
		return t;
	}

	protected abstract void sendReportQueryApplyToEsb(T t) throws EsbException;
	protected abstract void findReportResultFromEsb(T t) throws EsbException;
	protected abstract void syncReportToResolveApp(T t) throws EsbException;
}
