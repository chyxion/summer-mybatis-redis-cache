package me.chyxion.summer.mybatis.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.ibatis.cache.Cache;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @version 0.0.1
 * @since 0.0.1
 * @author Shaun Chyxion <br>
 * chyxion@163.com <br>
 * Feb 23, 2016 6:03:39 PM
 */
public final class RedisCache implements Cache {
	private static final Logger log = 
		LoggerFactory.getLogger(RedisCache.class);

	private String id;
	private RedisTemplate<String, Object> redisTpl;
	private ValueOperations<String, Object> valueOp;
	private RedisCacheConfig config;

	/**
	 * construct cache 
	 * @param id cache id
	 */
	public RedisCache(String id) {
		if (id == null) {
			throw new IllegalArgumentException(
				"Cache Instance ID Could Not Be Null");
		}
		log.info("Create Redis Cache [{}].", id);
		this.id = id;
		config = RedisCacheConfig.getInstance();
		redisTpl = config.getRedisTpl();
		valueOp = redisTpl.opsForValue();
	}

	/**
	 * {@inheritDoc}
	 */
	public String getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getSize() {
		log.debug("Get Cache [{}] Size.", id);
		return redisTpl.keys(prefixedKey("*")).size();
	}

	/**
	 * {@inheritDoc}
	 */
	public void putObject(final Object key, final Object value) {
		log.debug("Put Object Key [{}], Value [{}].", key, value);
		valueOp.set(prefixedKey(key), 
			value, config.getExpire(), TimeUnit.MILLISECONDS);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Object getObject(final Object key) {
		Object value = valueOp.get(prefixedKey(key));
		log.debug("Get Object Key [{}], Value [{}].", key, value);
		return value;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Object removeObject(final Object key) {
		log.debug("Remove Object Key [{}].", key);
		redisTpl.delete(prefixedKey(key));
		return 1;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void clear() {
		log.debug("Clear Cache Key [{}].", id);
		redisTpl.delete(redisTpl.keys(prefixedKey("*")));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReadWriteLock getReadWriteLock() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Redis Cache [" + id + "]";
	}

	// --
	// private methods

	/**
	 * return prefixed cache key
	 * @param key cache key
	 * @return prefixed key
	 */
	String prefixedKey(Object key) {
		return prefix() + String.valueOf(key);
	}

	/**
	 * @return cache prefix
	 */
	String prefix() {
		return id + ":";
	}
}
