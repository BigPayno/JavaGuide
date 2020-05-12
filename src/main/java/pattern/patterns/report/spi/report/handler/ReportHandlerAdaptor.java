package pattern.patterns.report.spi.report.handler;

import pattern.patterns.report.spi.report.context.IReportContext;
import pattern.patterns.report.spi.esb.exception.EsbException;

public class ReportHandlerAdaptor<T extends IReportContext<?>> implements IReportHandler<T>{

	@Override
	public void onReportQueryApplySuccess(T context) throws EsbException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReportResultQuerySuccess(T context) throws EsbException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReportSyncResolveAppSuccess(T context) throws EsbException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReportQueryApplyFail(T context, Exception e) throws EsbException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReportResultQueryFail(T context, Exception e) throws EsbException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReportSyncResolveAppFail(T context, Exception e) throws EsbException {
		// TODO Auto-generated method stub
		
	}
}
