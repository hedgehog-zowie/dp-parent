package com.iuni.dp.mq.common.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.iuni.dp.mq.common.utils.HttpUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * 数据上报基类Action
 * @author CaiKe
 * dp-mq-1.0.0
 */
@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements Preparable, ServletRequestAware, ServletResponseAware {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseAction.class);
	
	@SuppressWarnings("unused")
	private static final String UNCHECKED = "unchecked";
	
	//请求环境变量
	protected HttpServletRequest request;
	
	//用于页面消息提示
	protected Map<String, String> infoMessages = new HashMap<String, String>();
	
	//响应环境变量
	protected HttpServletResponse response;
	
	protected String ip;
	
	public static final String LOGIN = "login";
	
	//总行数；
	public long totalCounts;
	//总页数;
	public int totalPages;
	//当前页;默认为1;
	public int currentPage=1;
	//页参数;默认为1;
	public int pageIndex=1;
	//每页行数，默认10; 
	public int pageSize=10;
	//分页查询的起止行  缺省为0
    private int startRec = 0;
    //缺省为10
	private int endRec  = 10 ;
	
	public String getIp() {
//		return HttpUtil.getIp(null);
		return HttpUtil.getRealIP(null);
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public long getTotalCounts() {
		return totalCounts;
	}

	public void setTotalCounts(long totalCounts) {
		this.totalCounts = totalCounts;
	}

	public int getTotalPages() {
		totalPages = (int) ((totalCounts % pageSize == 0) ? (totalCounts / pageSize): ((totalCounts / pageSize) + 1));
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
 
	public void prepare() throws Exception {
		 	
	}
 
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	//oracle 
	public int getFirstRow() {
		if (totalPages == 0) {
			startRec = 0;
		} else {
			startRec = (pageIndexCurrent() - 1) * pageSize + 1;
		}
		return startRec;
	}
	
	//oracle
	public int getLastRow() {
		if (totalPages <= 1) {
			endRec = (int) totalCounts;
		} else if (pageIndexCurrent() == totalPages) {
			endRec = (int) totalCounts;
		} else {
			endRec = (startRec + pageSize) - 1;
		}
		return endRec;
	}
	
	//Mysql 
	public int getFirstRowMysql() {
		if (totalPages == 0) {
			startRec = 0;
		} else {
			startRec = (pageIndexCurrent() - 1) * pageSize;
		}
		return startRec;
	}
	
	//Mysql
	public int getLastRowMysql() {
		endRec = pageSize;
		return endRec;
	}
	
	public int pageIndexCurrent() {
		if (getTotalPages() == 0) {
			pageIndex = 1;
		} else if (pageIndex > getTotalPages()) {
			pageIndex = getTotalPages();
		}
		return pageIndex;
	}
	
	/**
	 * Action返回JSON数据
	 * @param Object data
	 */
	public void printJson(Object data) {
		JSONArray jsonArray = JSONArray.fromObject(data);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			out.println(jsonArray.toString());
			
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if(null != out) {
				out.flush();
				out.close();
			}
		}
	}
	
	/**
	 * Action返回JSON数据
	 * @param Object data
	 */
	public void printJson2(Object data, Boolean isDataFormat, Boolean isWriteNullValue) {
		String result = null;
		if(isDataFormat && isWriteNullValue) {
			result = JSON.toJSONString(data, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue);
		} else if(!isDataFormat && isWriteNullValue) {
			result = JSON.toJSONString(data, SerializerFeature.WriteMapNullValue);
		} else if(isDataFormat && !isWriteNullValue) {
			result = JSON.toJSONString(data, SerializerFeature.WriteDateUseDateFormat);
		} else {
			result = JSON.toJSONString(data);
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			out.println(result);
			
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if(null != out) {
				out.flush();
				out.close();
			}
		}
	}
	
}
