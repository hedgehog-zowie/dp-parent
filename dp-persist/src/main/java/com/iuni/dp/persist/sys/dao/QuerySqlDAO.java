package com.iuni.dp.persist.sys.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;

public interface QuerySqlDAO {
	/**
	 * 执行数据查询
	 * @param queryStr
	 * @return List  返回类型
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List exeQuerySql(String queryStr) throws DataAccessException;
}
