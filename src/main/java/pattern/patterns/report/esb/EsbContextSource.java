package pattern.patterns.report.esb;

import java.io.InputStream;

import org.springframework.stereotype.Component;

import pattern.patterns.report.spi.esb.V2EsbReq;
import pattern.patterns.report.spi.esb.V2EsbRes;
import pattern.patterns.report.spi.esb.context.IEsbContextSource;

@Component
public class EsbContextSource implements IEsbContextSource {
	
	static Class<?>[] SUPPORT_CLASSES = new Class<?>[]{
		V2EsbReq.class, V2EsbRes.class,InputStream.class,String.class};
	
	@Override
	public Class<?>[] getSupportClasses() {
		return SUPPORT_CLASSES;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		for (Class<?> clazz2 : SUPPORT_CLASSES) {
			if(clazz2==clazz) {
				return true;
			}
		}
		return false;
	}
}
