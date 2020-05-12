package pattern.patterns.report.spi.task;

public interface IRedisTask {
	String getTaskType();
	String getTaskId();
	Long getTaskExecuteTimestamp();
}
