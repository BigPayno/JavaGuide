package pattern.patterns.report.task;

import pattern.patterns.report.spi.task.IRedisTaskConsumer;
//否则Spring拿不到Beans会出现启动无异常终止

public interface RedisTaskConsumer extends IRedisTaskConsumer<RedisTaskType>{
	
}
