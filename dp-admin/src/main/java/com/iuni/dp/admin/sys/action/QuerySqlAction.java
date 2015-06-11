package com.iuni.dp.admin.sys.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.service.sys.service.QuerySqlService;

@Controller("querySqlAction")
@Scope("prototype")
public class QuerySqlAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private QuerySqlService querySqlService;
	
	private String queryStr;
	
	@SuppressWarnings("rawtypes")
	private List queryResult ;

	public String exeQuery() {
		try {			
			//判断查询条件
			if(StringUtils.isEmpty(queryStr)){
				throw new Exception("SQl语句不能为空!");
			}
			if(queryStr.trim().toUpperCase().indexOf("SELECT")<0){
				throw new Exception("该页面仅供查询！");
			}
			queryResult = querySqlService.exeQuery(queryStr);
			request.setAttribute("size", queryResult.size());
			if(null == queryResult || queryResult.size() == 0){
				this.infoMessages.put("infoMessage","没找到任何结果");
			}
			return SUCCESS;
		} catch (Exception e) {
			addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public String goquerySql(){
		return SUCCESS;
	}
	
	@SuppressWarnings("rawtypes")
	public List getQueryResult() {
		return queryResult;
	}

	@SuppressWarnings("rawtypes")
	public void setQueryResult(List queryResult) {
		this.queryResult = queryResult;
	}

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public void setQuerySqlService(QuerySqlService querySqlService) {
		this.querySqlService = querySqlService;
	}

}
