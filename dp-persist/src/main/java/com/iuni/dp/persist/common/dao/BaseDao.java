package com.iuni.dp.persist.common.dao;

import java.util.List;
import java.util.Map;

/**
 * BaseDao
 * @author CaiKe
 * @param <T>
 * @param <PK>
 * @version dp-persist-1.0.0
 */
public interface BaseDao<T, PK> {
	
	/**
	 * 查询所有
	 * @param sqlId
	 * @return List < T >
	 */
	public List<T> getAll(String sqlId);
	
	/**
	 * 查询所有
	 * @param sqlId
	 * @return List< Map< String, Object > >
	 */
	public List<Map<String, Object>> getAll2(String sqlId);
	
	/**
	 * 根据主键ID查询一条数据
	 * @param sqlId
	 * @param id
	 * @return Object
	 */
	public Object getById(String sqlId, PK id);

	/**
	 * 根据参数查询一条数据
	 * @param sqlId
	 * @param obj
	 * @return Object
	 */
	public Object getObjectByProperty(String sqlId, Object obj);
	
	/**
	 * 查询并分页显示;
	 * @param sqlId
	 * @param params
	 * @return List< T >
	 */
	public List<T> findAllObjectsByPage(String sqlId, Object params);
	
	/**
	 * 查询并分页显示的总行数;
	 * @param sqlId
	 * @param params
	 * @return Integer
	 */
	public Integer findAllObjectsCount(String sqlId, Object params);
	
	/**
	 * 查询并分页显示2
	 * @param sqlId
	 * @param params
	 * @return List< Map< String, Object > >
	 */
	public List<Map<String, Object>> findAllObjectsByPage2(String sqlId, Object params);
	
	/**
	 * 查询并分页显示3
	 * @param sqlId
	 * @param params
	 * @return List< ? >
	 */
	public List<?> findAllObjectsByPage3(String sqlId, Object params);
	
	/**
	 * 保存
	 * @param obj
	 * @param sqlId
	 * @return Object
	 */
	public Object insert(Object obj, String sqlId);

	/**
	 * 更新
	 * @param obj
	 * @param sqlId
	 * @return int
	 */
	public int update(Object obj, String sqlId);
	
	/**
	 * 删除
	 * @param obj
	 * @param sqlId
	 * @return int
	 */
	public int delete(Object obj, String sqlId);

}
