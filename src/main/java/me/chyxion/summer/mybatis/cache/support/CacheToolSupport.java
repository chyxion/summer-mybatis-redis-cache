package me.chyxion.summer.mybatis.cache.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.ibatis.cache.Cache;
import javax.annotation.PostConstruct;
import org.apache.ibatis.session.Configuration;
import me.chyxion.summer.mybatis.cache.CacheTool;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @version 0.0.1
 * @since 0.0.1
 * @author Shaun Chyxion <br>
 * chyxion@163.com <br>
 * Aug 26, 2015 10:58:58 AM
 */
public class CacheToolSupport implements CacheTool {
	private static final Logger log = 
		LoggerFactory.getLogger(CacheToolSupport.class);
	@Autowired
	private SqlSessionFactory ssf;
	private Configuration configuration;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearCache(Class<?> mapper) {
		log.info("Clear Cache [{}].", mapper);
		Cache cache = getCache(mapper);
		if (cache != null) {
			cache.clear(); 
		}
		else {
			log.warn("No Cache [{}] Found.", mapper);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cache getCache(Class<?> mapper) {
		return configuration.getCache(mapper.getName());
	}

	// --
	// private methods
	@PostConstruct
	private void init() {
		 configuration = ssf.getConfiguration();
	}
}