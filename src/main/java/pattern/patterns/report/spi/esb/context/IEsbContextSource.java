package pattern.patterns.report.spi.esb.context;

public interface IEsbContextSource {
	Class<?>[] getSupportClasses();
	boolean supports(Class<?> clazz);
}
