package me.chyxion.summer.mybatis.cache;

import javax.annotation.PostConstruct;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @version 0.0.1
 * @since 3.1.1
 * @author Shaun Chyxion <br>
 * chyxion@163.com <br>
 * Aug 4, 2015 5:17:05 PM
 */
public class RedisCacheConfig {
	@Value("${mybatis.cache.expire:480}")
	private int expire;
	@Autowired
	private RedisTemplate<String, Object> redisTpl;
	private static RedisCacheConfig instance;

	@PostConstruct
	public void init() {
		instance = this;
	}

	/**
	 * @return cache instance
	 */
	public static RedisCacheConfig getInstance() {
		return instance;
	}

	/**
	 * @return the expire
	 */
	public int getExpire() {
		return expire;
	}

	/**
	 * @param expire the expire to set
	 */
	public void setExpire(int expire) {
		this.expire = expire;
	}

	/**
	 * @return the redisTpl
	 */
	public RedisTemplate<String, Object> getRedisTpl() {
		return redisTpl;
	}

	/**
	 * @param redisTpl the redisTpl to set
	 */
	public void setRedisTpl(RedisTemplate<String, Object> redisTpl) {
		this.redisTpl = redisTpl;
	}
}
