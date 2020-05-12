 package pattern.patterns.report.task;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import pattern.patterns.report.spi.task.IRedisTask;
import pattern.patterns.report.spi.task.IRedisTaskProducer;
import pattern.patterns.report.spi.task.IRedisTaskTemplate;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class RedisTasks implements IRedisTaskProducer{

	@Autowired
	IRedisTaskTemplate taskTempale;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Override
	public void addRedisTask(IRedisTask t) {
		taskTempale.addTask(t.getTaskType(), t.getTaskId(), t.getTaskExecuteTimestamp());
		log.info("添加任务成功[task=({}),time=({})]",t,
			LocalDateTime.ofInstant(
				Instant.ofEpochMilli(t.getTaskExecuteTimestamp()),
				ZoneId.systemDefault()
			)
		);
	}
	
	public Map<String, Long> getTasksWithExecuteTime(RedisTaskType type){
		return taskTempale.getTasksOfOneDay(type.getRedisZSetKey());
	}
}
