package pattern.patterns.report.spi.task;

public interface IRedisTaskConsumer<T> {
	boolean supportTaskType(T type);
	void consume(IRedisTask t);
}
