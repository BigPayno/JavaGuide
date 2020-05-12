package pattern.patterns.report.spi.cache;

public interface ICachable {
	
	default String getCacheHashKey() {
		return getClass().getSimpleName();
	}
	
	String getCacheId();
}
