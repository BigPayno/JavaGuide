package pattern.patterns.report.spi.report.handler;

import pattern.patterns.report.spi.report.context.IReportContext;
import pattern.patterns.report.spi.esb.exception.EsbException;

import lombok.AllArgsConstructor;

/*
 * W为当前Handler处理类型,T为包装Handler处理类型
 */
@AllArgsConstructor
public abstract class ReportHandlerWrapper<W extends T,T extends IReportContext<?>> implements IReportHandler<W>{
	
	IReportHandler<T> reportHandler;

	@Override
	public void onReportQueryApplySuccess(W context) throws EsbException {
		reportHandler.onReportQueryApplySuccess(context);
		onReportQueryApplySuccessInternal(context);
	}

	protected void onReportQueryApplySuccessInternal(W context) {
		
	}

	
	
	@Override
	public void onReportQueryApplyFail(W context, Exception e) {
		reportHandler.onReportQueryApplyFail(context, e);
		onReportQueryApplyFailInternal(context, e);
	}

	protected void onReportQueryApplyFailInternal(W context, Exception e) {
		
	}

	@Override
	public void onReportResultQuerySuccess(W context) throws EsbException {
		reportHandler.onReportResultQuerySuccess(context);
		onReportResultQuerySuccessInternal(context);
	}

	protected void onReportResultQuerySuccessInternal(W context) {
		
	}

	@Override
	public void onReportResultQueryFail(W context, Exception e) {
		reportHandler.onReportResultQueryFail(context, e);
		onReportResultQueryFailInternal(context, e);
	}

	protected void onReportResultQueryFailInternal(W context, Exception e) {
		
	}

	@Override
	public void onReportSyncResolveAppFail(W context,Exception e) {
		reportHandler.onReportSyncResolveAppFail(context,e);
		onReportSyncResolveAppFailInternal(context,e);
	}
	
	protected void onReportSyncResolveAppFailInternal(W context, Exception e) {
		
	}

	@Override
	public void onReportSyncResolveAppSuccess(W context) throws EsbException {
		reportHandler.onReportSyncResolveAppSuccess(context);
		onReportSyncResolveAppSuccessInternal(context);
	}

	protected void onReportSyncResolveAppSuccessInternal(W context) {
		
	}
}
