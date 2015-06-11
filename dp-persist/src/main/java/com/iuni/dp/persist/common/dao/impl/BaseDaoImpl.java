package com.iuni.dp.persist.common.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.iuni.dp.persist.common.dao.BaseDao;
import com.iuni.dp.persist.sys.model.Menu;

/**
 * BaseDaoImpl
 * @author CaiKe
 * @param <T>
 * @param <PK>
 * @version dp-persist-1.0.0
 */
@SuppressWarnings("deprecation")
public class BaseDaoImpl <T,PK> extends SqlMapClientDaoSupport implements BaseDao <T,PK>{
	
	static Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Menu> getAllObjects(String sqlId,Object obj) {
		List<Menu> list = new ArrayList<Menu>();
		list = getSqlMapClientTemplate().queryForList(sqlId,obj);	
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll(String sqlId) {
		List<T> list=new ArrayList<T>();
		list=getSqlMapClientTemplate().queryForList(sqlId);	
		return list;
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public List<Map<String, Object>> getAll2(String sqlId) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = getSqlMapClientTemplate().queryForList(sqlId);	
		return list;
	}

	@Override
	public Object getById(String sqlId, PK id) {
		return getSqlMapClientTemplate().queryForObject(sqlId,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllObjectsByPage(String sqlId, Object params) {
		List<T> list = new ArrayList<T>();
		list = getSqlMapClientTemplate().queryForList(sqlId, params);
		return list;
	}

    @Override
    public Object getObjectByProperty(String sqlId, Object obj) {
        return getSqlMapClientTemplate().queryForObject(sqlId,obj);
    }

	@Override
	public Integer findAllObjectsCount(String sqlId, Object params) {
		Object  count= getSqlMapClientTemplate().queryForObject(sqlId, params);
		if(count==null){
			return 0;
		}
		if(count instanceof Long){
			return new Integer(count.toString());
		}else{
			return (Integer) getSqlMapClientTemplate().queryForObject(sqlId, params);
		}
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public List<Map<String, Object>> findAllObjectsByPage2(String sqlId,
			Object params) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = getSqlMapClientTemplate().queryForList(sqlId, params);
		return list;
	}

	@SuppressWarnings({"rawtypes"})
	@Override
	public List<?> findAllObjectsByPage3(String sqlId, Object params) {
		List<?> list = new ArrayList();
		list = getSqlMapClientTemplate().queryForList(sqlId, params);
		return list;
	}

	@Override
	public Object insert(Object obj, String sqlId) {
		return getSqlMapClientTemplate().insert(sqlId, obj);
	}

	@Override
	public int update(Object obj, String sqlId) {
		return getSqlMapClientTemplate().update(sqlId, obj);
	}

	@Override
	public int delete(Object obj, String sqlId) {
		return getSqlMapClientTemplate().delete(sqlId, obj);
	}

}
