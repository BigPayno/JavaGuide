package pattern.patterns.report.task;

import pattern.patterns.report.spi.task.IRedisTask;

import lombok.ToString;

/*
 * 必须给Id添加一个标识，否则回调任务只加了一个
 * 同理短时间内多次调同一个也会有问题
 * 因为redis会覆盖同样key的
 * 1.ID->UUID->ID
 * 2.ID->ID+FixedUUID->ID
 * 我采取了方案2，@RedisTaskTemplate
 * 一般情况是没有问题的，极端情况也只能失败重试
 */

@ToString(exclude = "taskExecuteTimestamp")
public class RedisTask implements IRedisTask{

	protected String taskId;
	protected long taskExecuteTimestamp;
	protected RedisTaskType taskType;
	
	@Override
	public String getTaskId() {
		return taskId;
	}

	@Override
	public Long getTaskExecuteTimestamp() {
		return (taskExecuteTimestamp==0)?
				Long.valueOf(System.currentTimeMillis()):
				Long.valueOf(taskExecuteTimestamp);
	}

	public void setTaskExecuteTimestamp(long timestamp) {
		this.taskExecuteTimestamp=timestamp;
	}

	public void setTaskExecuteDelayed(long delayed) {
		this.taskExecuteTimestamp=System.currentTimeMillis()+delayed;
	}
	
	public static RedisTask of(String taskId,RedisTaskType taskType) {
		RedisTask task=new RedisTask();
		task.taskId=taskId;
		task.taskType=taskType;
		return task;
	};
	
	public RedisTask when(long timestamp) {
		setTaskExecuteTimestamp(timestamp);
		return this;
	}
	
	public RedisTask after(long delayed) {
		setTaskExecuteDelayed(delayed);
		return this;
	}

	@Override
	public String getTaskType() {
		return taskType.getRedisZSetKey();
	}
}
