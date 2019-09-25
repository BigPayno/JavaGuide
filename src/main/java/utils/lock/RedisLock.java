package utils.lock;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author payno
 * @date 2019/8/30 16:10
 * @description //待修改，有问题@Todo
 */
@Slf4j
@Getter
public class RedisLock {
	private RedisLock() {
	}
	private String lockName;
	private String lockIdentifier;
	private long acquireTimeout;
	private long timeoutNum;
	private TimeUnit timeoutUnit;
	private boolean occupyLock;

	public static RedisLock of(String lockName) {
		RedisLock lock = new RedisLock();
		lock.lockName = lockName;
		lock.lockIdentifier = UUID.randomUUID().toString();
		lock.occupyLock = false;
		return lock;
	}

	public RedisLock tryBefore(long acquireTimeout) {
		this.acquireTimeout = acquireTimeout;
		return this;
	}

	public RedisLock releaseAfter(long num, TimeUnit timeUnit) {
		this.timeoutNum = num;
		this.timeoutUnit = timeUnit;
		return this;
	}

	public boolean tryLock(StringRedisTemplate stringRedisTemplate) {
		log.info("尝试获得锁,锁名[{}]", lockName);
		long end = System.currentTimeMillis() + acquireTimeout;
		try {
			while (System.currentTimeMillis() < end && !occupyLock) {
				occupyLock =Optional.ofNullable(stringRedisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
					return redisConnection.set(lockName.getBytes(), lockIdentifier.getBytes(),
							Expiration.from(timeoutNum, timeoutUnit), RedisStringCommands.SetOption.ifAbsent());
				})).orElse(Boolean.FALSE);	
				if (occupyLock) {
					log.info("成功获得锁，锁名[{}],占有锁时间为[{}{}]", lockName, timeoutNum, timeoutUnit.toString());
					return occupyLock;
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException ie) {
					Thread.currentThread().interrupt();
				}
			}
			return occupyLock;
		} catch (Exception e) {
			log.error("获取锁出现异常！！！,锁名[{}]", lockName, e.getMessage());
			release(stringRedisTemplate);
		}
		return this.occupyLock;
	}

	public void release(StringRedisTemplate stringRedisTemplate) {
		int time = 0;
		while (occupyLock) {
			log.info("尝试释放锁，锁名[{}],次数[{}]", lockName, time);
			try {
				BoundValueOperations<String, String> lockOnRedis = stringRedisTemplate.boundValueOps(lockName);
				if (lockIdentifier.equals(lockOnRedis.get())) {
					stringRedisTemplate.delete(lockName);
					occupyLock = false;
				}
				log.info("释放锁成功，锁名[{}]", lockName);
			} catch (Exception e) {
				time++;
				occupyLock = false;
				if (time == 3) {
					break;
				}
			}
		}
	}
}
