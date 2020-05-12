package pattern.patterns.report.spi.cache;

public interface ICacheManager {
	void cache(String id,ICachable t);
	
	<T extends ICachable> T getCache(String id,Class<T> clazz);
	
	<T extends ICachable> void expireCache(String id,Class<T> clazz);
}
