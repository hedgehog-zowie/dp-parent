package com.iuni.dp.persist.sys.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.sys.dao.QuerySqlDAO;

@Service("querySqlDao")
public class QuerySqlDAOImpl extends SqlMapClientDaoSupport implements QuerySqlDAO {

	@SuppressWarnings({ "rawtypes" })
	public List exeQuerySql(String queryStr) throws DataAccessException {		
		//限制页面显示的结果集
		Map<String,String> paramMap=new HashMap<String,String>();
		paramMap.put("queryStr", queryStr);
		List list  = this.getSqlMapClientTemplate().queryForList("common.admin_query_sql", paramMap,0,200);
		if(null == list){
			list  = new ArrayList();
		}
		return list;
	}
}
