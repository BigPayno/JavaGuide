package pattern.patterns.report.spi.service.template;

import pattern.patterns.report.spi.esb.exception.EsbException;

public interface IEsbV2Template<T,R> {
	R send(String tranCode,String serviceId, T t, Class<T> requestType) throws EsbException;
}
