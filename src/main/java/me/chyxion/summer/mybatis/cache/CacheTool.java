package me.chyxion.summer.mybatis.cache;

import org.apache.ibatis.cache.Cache;

/**
 * @version 0.0.1
 * @since 0.0.1
 * @author Shaun Chyxion <br>
 * chyxion@163.com <br>
 * Aug 26, 2015 10:58:58 AM
 */
public interface CacheTool {

	/**
	 * @param mapper mapper
	 */
	void clearCache(Class<?> mapper);
	
	/**
	 * @param mapper mapper
	 * @return cache of mapper
	 */
	Cache getCache(Class<?> mapper);
}
