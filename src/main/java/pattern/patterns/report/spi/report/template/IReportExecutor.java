package pattern.patterns.report.spi.report.template;

import pattern.patterns.report.spi.esb.exception.EsbException;
import pattern.patterns.report.spi.report.context.IReportContext;

/*
 * 真正方便使用的征信报文接口，只会抛出业务异常
 */
public interface IReportExecutor<T extends IReportContext<?>> {
	boolean syncReportFromResolveApp(T t);
	T syncReportFromEsbToResolveApp(T t) throws EsbException;
}
