package pattern.patterns.report.report.template;

import pattern.patterns.report.spi.esb.V2EsbRes;
import pattern.patterns.report.spi.esb.exception.EsbException;
import pattern.patterns.report.spi.esb.report.ReportApplyEsbRes;
import pattern.patterns.report.spi.esb.report.ReportResultEsbRes;
import pattern.patterns.report.spi.report.context.IReportContext;
import pattern.patterns.report.spi.report.handler.IReportHandler;
import pattern.patterns.report.spi.report.template.AbstractReportExecutor;
import pattern.patterns.report.spi.report.template.IReportTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * 	模板方法
 */
@Slf4j
public class ReportExecutor<T extends IReportContext<?>> extends AbstractReportExecutor<T> {
	
	public ReportExecutor(IReportTemplate reportTemplate, IReportHandler<T> handler) {
		super(reportTemplate);
		this.handler=handler;
	}

	IReportHandler<T> handler;

	@Override
	protected void sendReportQueryApplyToEsb(T t) throws EsbException {
		try {
			V2EsbRes<ReportApplyEsbRes> response=sendReportQueryApplyToEsb(t.buildApplyEsbReq());
			t.setReportApplyEsbRes(response);
			handler.onReportQueryApplySuccess(t);
		}catch (Exception e) {
			log.debug("打印异常栈",e);
			handler.onReportQueryApplyFail(t, e);
		}
	}

	@Override
	protected void findReportResultFromEsb(T t) throws EsbException{
		try {
			V2EsbRes<ReportResultEsbRes> response=findReportResultFromEsb(t.buildReportResultEsbReq());
			t.setReportResultEsbRes(response);
			handler.onReportResultQuerySuccess(t);
		}catch (Exception e) {
			log.debug("打印异常栈",e);
			handler.onReportResultQueryFail(t, e);
		}
	}

	@Override
	protected void syncReportToResolveApp(T t) throws EsbException{
		try {
			String response=syncReportToResolveApp(
					t.getUserName(), t.getMobile(),
					t.getIdCard(), t.getReport());
			t.setResponse(response);
			handler.onReportSyncResolveAppSuccess(t);
			t.setReportSyncToResolveSuccess(true);
		}catch (Exception e) {
			log.debug("打印异常栈",e);
			handler.onReportSyncResolveAppFail(t, e);
		}
	}
}
