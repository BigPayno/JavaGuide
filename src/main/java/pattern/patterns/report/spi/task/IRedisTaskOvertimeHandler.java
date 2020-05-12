package pattern.patterns.report.spi.task;

public interface IRedisTaskOvertimeHandler<T> {
	boolean supportOvertimeTaskType(T type);
	void handleTaskOvertime(T type);
}
