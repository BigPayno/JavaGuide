package pattern.patterns.report.spi.esb.context;

import pattern.patterns.report.spi.esb.V2EsbReq;
import pattern.patterns.report.spi.esb.V2EsbRes;

public interface IV2EsbProxy {
	<T,R> V2EsbRes<R> send(V2EsbReq<T> request, Class<R> responseType)
		throws Exception;
}
