package com.iuni.dp.service.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.sys.dao.QuerySqlDAO;
import com.iuni.dp.service.sys.service.QuerySqlService;

@Service("querySqlService")
public class QuerySqlServiceImpl implements QuerySqlService {
	
	@Autowired
	private  QuerySqlDAO querySqlDao;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List exeQuery(String queryStr) throws Exception{
		return querySqlDao.exeQuerySql(queryStr);
	}
	
	public QuerySqlDAO getQuerySqlDao() {
		return querySqlDao;
	}

	public void setQuerySqlDao(QuerySqlDAO querySqlDao) {
		this.querySqlDao = querySqlDao;
	}

}
