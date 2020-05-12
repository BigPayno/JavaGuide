package pattern.patterns.report.spi.task;

import java.util.Map;
import java.util.Set;


public interface IRedisTaskTemplate {
	void addTask(String zsetKey,String taskId,Long timestamp);
	Set<String> getTask(String zsetKey,Long before);
	Map<String,Long> getTasksOfOneDay(String zsetKey);
}
