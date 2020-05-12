package pattern.patterns.report.task;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import pattern.patterns.report.spi.task.IRedisTaskTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RedisTaskTemplate implements IRedisTaskTemplate{

	@Autowired
	StringRedisTemplate redisTemplate;
	
	ZSetOperations<String, String> opsForZSet;
	
	@SuppressWarnings("rawtypes")
	DefaultRedisScript<List> script = new DefaultRedisScript<>();
	
	@PostConstruct
	public void initOpsForZSet() {
		opsForZSet=redisTemplate.opsForZSet();
		Resource resource = new ClassPathResource("/lua/pullTasks.lua");
		script = new DefaultRedisScript<>();
	    script.setResultType(List.class);
	    script.setScriptSource(new ResourceScriptSource(resource));
	}
	
	@Override
	public void addTask(String zsetKey, String taskId, Long timestamp) {
		try {
			opsForZSet.add
				(zsetKey, taskId+UUID.randomUUID().toString().substring(0, 4), timestamp/1000);
		}catch (Exception e) {
			log.error("redis任务添加异常！[zsetKey=({}),taskId=({})，timestamp=({})]",zsetKey,taskId,timestamp);
		}
	}
	
	/*
	 * 	稍微延迟可以接受的，以后优化
	 * 	需要保证原子性，否则会出现重复消费
	 * 	但是无论是加锁还是redis原子操作，都导致只是单机在执行
	 * 	可以考虑但没必要
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getTask(String zsetKey, Long before) {
		try {
			long end=before/1000;
			List<String> taskList= (List<String>) redisTemplate
					.execute(script, Arrays.asList(zsetKey), String.valueOf(end));
			if(taskList==null) {
				return Collections.emptySet();
			}else {
				return taskList.stream()
						.map(taskId->taskId.substring(0, taskId.length()-4))
						.collect(Collectors.toSet());
			}
		}catch (Exception e) {
			log.error("redis任务获取异常！[zsetKey=({}),before=({})]",zsetKey,before);
			e.printStackTrace();
			return Collections.emptySet();
		}
	}

	@Override
	public Map<String,Long> getTasksOfOneDay(String zsetKey) {
		Set<TypedTuple<String>> set=opsForZSet.rangeByScoreWithScores(zsetKey,0,(System.currentTimeMillis() + 24 * 60 * 60 * 1000)/(double)1000);
		if(set==null) {
			return  Collections.emptyMap();
		}else {
			Map<String, Long> map=new HashMap<String, Long>();
			set.forEach(tuple->{
				String taskId=tuple.getValue();
				Long exeTimestamp= tuple.getScore().longValue()*1000;
				map.put(taskId.substring(0, taskId.length()-4), exeTimestamp);
			});
			return map;
		}
	}
}
