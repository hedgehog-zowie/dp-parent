package com.iuni.dp.service.sys.service;

import java.util.LinkedHashMap;
import java.util.List;

public interface QuerySqlService {
	/**
	 * 执行数据查询
	 * @param queryStr
	 * @return List  返回类型
	 * @throws Exception
	 */
	public List<LinkedHashMap<String, String>> exeQuery(String queryStr) throws Exception;
	
}
