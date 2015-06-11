package com.iuni.dp.admin.datastat.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.persist.datastat.model.IuniBuriedPointSite;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.IuniBuriedPointSiteService;

/**
 * IUNI商城前端埋点位置 Controller
 * @author Kenneth.Cai@iuni.com
 * @version dp-admin-V1.1.0
 */
@Controller("iuniBuriedPointSiteAction")
@Scope("prototype")
public class IuniBuriedPointSiteAction extends BaseAction {

	private static final long serialVersionUID = 2881095697239691951L;

	private final Logger logger = LoggerFactory.getLogger(IuniBuriedPointSiteAction.class);
	
	@Autowired
	private IuniBuriedPointSiteService iuniBuriedPointSiteService; 
	
	private String flag;
	private IuniBuriedPointSite iuniBuriedPointSite;
	private Map<String, String> queryParams = new HashMap<String, String>();
	
	/**
	 * IUNI商城前端埋点位置列表视图
	 * @return String
	 */
	public String iuniBuriedPointListView() {

		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("list");
			//消息类型列表总数目查询
			Integer totalRecord = iuniBuriedPointSiteService.queryIbpsCount(params);
			//根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			//新增Page至参数Map
			setPageInfo2Map(params);
			
			List<IuniBuriedPointSite> iuniBuriedPointSites = iuniBuriedPointSiteService.queryIbpsByPage(params);
			
			request.setAttribute("iuniBuriedPointSites", iuniBuriedPointSites);
			this.setPage(page);
			
		} catch(DBException dbex) {
			logger.error("IuniBuriedPointSiteAction.iuniBuriedPointListView found DBException", dbex);
			return ERROR;
			
		} catch(Exception ex) {
			logger.error("IuniBuriedPointSiteAction.iuniBuriedPointListView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	
	}
	
	/**
	 * 生成查询参数Map
	 * @return Map<String, Object>
	 */
	private Map<String, Object> genParamMap(String condition) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		if("update".equals(condition)) {
			params.put("id", iuniBuriedPointSite.getId());
		}
		
		if(StringUtils.isNotBlank(queryParams.get("websiteCode"))) {
			params.put("websiteCode", queryParams.get("websiteCode"));
		}
		
		if(StringUtils.isNotBlank(queryParams.get("website"))) {
			params.put("website", queryParams.get("website"));
		}
		
		if(StringUtils.isNotBlank(queryParams.get("pageName"))) {
			params.put("pageName", queryParams.get("pageName"));
		}
		
		if(StringUtils.isNotBlank(queryParams.get("pagePosition"))) {
			params.put("pagePosition", queryParams.get("pagePosition"));
		}

		if(StringUtils.isNotBlank(queryParams.get("pointFlag"))) {
			params.put("pointFlag", queryParams.get("pointFlag"));
		}
		
		if(StringUtils.isNotBlank(queryParams.get("beginDate"))) {
			params.put("beginDate", queryParams.get("beginDate"));
		}
		
		if(StringUtils.isNotBlank(queryParams.get("endDate"))) {
			params.put("endDate", queryParams.get("endDate"));
		}
		
		return params;
	}
	
	/**
	 * 新增Page至参数Map
	 * @param Map<String, Object> params
	 */
	private void setPageInfo2Map(Map<String, Object> params) {
		params.put("startRec", page.getStartRec());
		params.put("endRec", page.getEndRec());
	} 
	
	/**
	 * IUNI商城前端埋点位置编辑视图
	 * @return String
	 */
	public String iuniBuriedPointEditView() {
		
		if(!StringUtils.isBlank(flag) && "update".equals(flag)) {
			try {
				//生成查询参数Map
				Map<String, Object> params = genParamMap("update");
				
				List<IuniBuriedPointSite> iuniBuriedPointSites = iuniBuriedPointSiteService.queryIbpsByExample(params);
				if(CollectionUtils.isEmpty(iuniBuriedPointSites)) {
					return ERROR;
				}
				
				request.setAttribute("flag", flag);
				request.setAttribute("iuniBuriedPointSite", iuniBuriedPointSites.get(0));
				
			} catch(DBException dbex) {
				logger.error("IuniBuriedPointSiteAction.iuniBuriedPointEditView found DBException", dbex);
				return ERROR;
				
			} catch(Exception ex) {
				logger.error("IuniBuriedPointSiteAction.iuniBuriedPointEditView found Exception", ex);
				return ERROR;
			}
		}
		
		return SUCCESS;
	
	}
	
	/**
	 * 新增IUNI商城前端埋点位置
	 * @return String
	 */
	public String addIuniBuriedPoint() {

		try {
			iuniBuriedPointSite.setCreateTime(new Timestamp(new Date().getTime()));
			
			iuniBuriedPointSiteService.saveIbps(iuniBuriedPointSite);
			
		} catch(DBException dbex) {
			logger.error("IuniBuriedPointSiteAction.addIuniBuriedPoint found DBException", dbex);
			return ERROR;
			
		} catch(Exception ex) {
			logger.error("IuniBuriedPointSiteAction.addIuniBuriedPoint found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	
	}
	
	/**
	 * 更新IUNI商城前端埋点位置
	 * @return String
	 */
	public String updateIuniBuriedPoint() {

		try {
			iuniBuriedPointSiteService.updateIbps(iuniBuriedPointSite);	
			
		} catch(DBException dbex) {
			logger.error("IuniBuriedPointSiteAction.updateIuniBuriedPoint found DBException", dbex);
			return ERROR;
			
		} catch(Exception ex) {
			logger.error("IuniBuriedPointSiteAction.updateIuniBuriedPoint found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	
	}
	
	/**
	 * 删除IUNI商城前端埋点位置
	 * @return String
	 */
	public String removeIuniBuriedPoint() {

		try {
			iuniBuriedPointSiteService.removeIbpsById(iuniBuriedPointSite.getId());
			
		} catch(DBException dbex) {
			logger.error("IuniBuriedPointSiteAction.removeIuniBuriedPoint found DBException", dbex);
			return ERROR;
			
		} catch(Exception ex) {
			logger.error("IuniBuriedPointSiteAction.removeIuniBuriedPoint found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public IuniBuriedPointSite getIuniBuriedPointSite() {
		return iuniBuriedPointSite;
	}

	public void setIuniBuriedPointSite(IuniBuriedPointSite iuniBuriedPointSite) {
		this.iuniBuriedPointSite = iuniBuriedPointSite;
	}

	public Map<String, String> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Map<String, String> queryParams) {
		this.queryParams = queryParams;
	}

}
