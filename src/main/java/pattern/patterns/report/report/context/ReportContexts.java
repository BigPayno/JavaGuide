package pattern.patterns.report.report.context;


import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.codec.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import pattern.patterns.report.spi.cache.ICachable;
import pattern.patterns.report.spi.cache.ICacheManager;

import javax.annotation.PostConstruct;


/**
 * 		工厂模式 将创建的行为分离出来
 */
@Slf4j
@Configuration
public class ReportContexts implements ICacheManager {
	
	@Value("${gz.credit-report.account}")
	String account;
	
	@Value("${gz.credit-report.password}")
	String password;
	
	String signEncry;
	
	@PostConstruct
	void initSignEntry() {
		signEncry =
			Hashing.md5().hashString(password, Charsets.UTF_8).toString().toUpperCase()+
			Base64.encodeToString(account.getBytes());
	}

	StringRedisTemplate redisTemplate;
	
	HashOperations<String, String, String> opsForHash;
	
	@PostConstruct
	public void initHashOps() {
		opsForHash=redisTemplate.opsForHash();
	}


	@Override
	public void cache(String id, ICachable t) {
		String info =JSONObject.toJSONString(t);
		opsForHash.put(t.getCacheHashKey(), t.getCacheId(), info);
		log.debug("缓存[id=({}),info=({})]",id,info);
	}

	@Override
	public <T extends ICachable> T getCache(String id,Class<T> clazz) {
		String contextInfo=opsForHash.get(clazz.getSimpleName(), id);
		log.debug("获取缓存[id=({}),class=({}),info=({})]",id,clazz.getSimpleName(),contextInfo);
		return JSONObject.parseObject(contextInfo, clazz);
	}

	@Override
	public <T extends ICachable> void expireCache(String id,Class<T> clazz) {
		opsForHash.delete(clazz.getSimpleName(), id);
		log.debug("释放缓存[id=({}),class=({})]",id,clazz.getSimpleName());
	}

	public <T extends ICachable> void expireAllCache(Class<T> clazz) {
		redisTemplate.delete(clazz.getSimpleName());
		log.debug("释放缓存[class=({})]",clazz.getSimpleName());
	}
}
