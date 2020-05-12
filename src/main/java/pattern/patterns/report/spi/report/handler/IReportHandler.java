package pattern.patterns.report.spi.report.handler;

import pattern.patterns.report.spi.report.context.IReportContext;
import pattern.patterns.report.spi.esb.exception.EsbException;

public interface IReportHandler<T extends IReportContext<?>> {
	/*
	 * 运行成功，还需要根据结果判断是否逻辑上正常
	 * 不符合逻辑的抛出业务异常
	 */
	public void onReportQueryApplySuccess(T context) throws EsbException;
	public void onReportResultQuerySuccess(T context) throws EsbException;
	public void onReportSyncResolveAppSuccess(T context) throws EsbException;
	
	/*
	 * 执行失败处理
	 */
	public void onReportQueryApplyFail(T context,Exception e) throws EsbException;
	public void onReportResultQueryFail(T context,Exception e) throws EsbException;
	public void onReportSyncResolveAppFail(T context,Exception e) throws EsbException;
}
