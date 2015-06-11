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
import com.iuni.dp.persist.datastat.model.MallBuriedPointSite;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.MallBuriedPointSiteService;

/**
 * 商城前端埋点位置 Controller
 * @author CaiKe
 * @version dp-admin-V1.0.1
 */
@Controller("mallBuriedPointSiteAction")
@Scope("prototype")
public class MallBuriedPointSiteAction extends BaseAction {

	private static final long serialVersionUID = -6415528149755512802L;

	private Logger logger = LoggerFactory.getLogger(MallBuriedPointSiteAction.class);
	
	@Autowired
	private MallBuriedPointSiteService mallBuriedPointSiteService;
	
	private String flag;
	private MallBuriedPointSite mallBuriedPointSite;
	private Map<String, String> queryParams = new HashMap<String, String>();
	
	/**
	 * 商城前端埋点位置列表视图
	 * @return String
	 */
	public String mallBuriedPointListView() {

		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap("list");
			//消息类型列表总数目查询
			Integer totalRecord = mallBuriedPointSiteService.queryMbpsCount(params);
			//根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			//新增Page至参数Map
			setPageInfo2Map(params);
			
			List<MallBuriedPointSite> mallBuriedPointSites = mallBuriedPointSiteService.queryMbpsByPage(params);
			
			request.setAttribute("mallBuriedPointSites", mallBuriedPointSites);
			this.setPage(page);
			
		} catch(DBException dbex) {
			logger.error("MallBuriedPointSiteAction found DBException", dbex);
			return ERROR;
			
		} catch(Exception ex) {
			logger.error("MallBuriedPointSiteAction found Exception", ex);
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
			params.put("id", mallBuriedPointSite.getId());
		}
		
		if(StringUtils.isNotBlank((String)queryParams.get("website"))) {
			params.put("website", queryParams.get("website"));
		}
		
		if(StringUtils.isNotBlank((String)queryParams.get("pageName"))) {
			params.put("pageName", queryParams.get("pageName"));
		}
		
		if(StringUtils.isNotBlank((String)queryParams.get("pagePosition"))) {
			params.put("pagePosition", queryParams.get("pagePosition"));
		}

		if(StringUtils.isNotBlank((String)queryParams.get("pointFlag"))) {
			params.put("pointFlag", queryParams.get("pointFlag"));
		}
		
		if(StringUtils.isNotBlank((String)queryParams.get("beginDate"))) {
			params.put("beginDate", queryParams.get("beginDate"));
		}
		
		if(StringUtils.isNotBlank((String)queryParams.get("endDate"))) {
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
	 * 商城前端埋点位置编辑视图
	 * @return String
	 */
	public String mallBuriedPointEditView() {
		
		if(!StringUtils.isBlank(flag) && "update".equals(flag)) {
			try {
				//生成查询参数Map
				Map<String, Object> params = genParamMap("update");
				
				List<MallBuriedPointSite> mallBuriedPointSites = mallBuriedPointSiteService.queryMbpsByExample(params);
				if(CollectionUtils.isEmpty(mallBuriedPointSites)) {
					return ERROR;
				}
				
				request.setAttribute("flag", flag);
				request.setAttribute("mallBuriedPointSite", mallBuriedPointSites.get(0));
				
			} catch(DBException dbex) {
				logger.error("MallBuriedPointSiteAction.mallBuriedPointEditView found DBException", dbex);
				return ERROR;
				
			} catch(Exception ex) {
				logger.error("MallBuriedPointSiteAction.mallBuriedPointEditView found Exception", ex);
				return ERROR;
			}
		}
		
		return SUCCESS;
	
	}
	
	/**
	 * 新增商城前端埋点位置
	 * @return String
	 */
	public String addMallBuriedPoint() {

		try {
			mallBuriedPointSite.setCreateTime(new Timestamp(new Date().getTime()));
			
			mallBuriedPointSiteService.saveMbps(mallBuriedPointSite);
			
		} catch(DBException dbex) {
			logger.error("MallBuriedPointSiteAction.addMsgType found DBException", dbex);
			return ERROR;
			
		} catch(Exception ex) {
			logger.error("MallBuriedPointSiteAction.addMsgType found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	
	}
	
	/**
	 * 更新商城前端埋点位置
	 * @return
	 */
	public String updateMallBuriedPoint() {

		try {
			mallBuriedPointSiteService.updateMbps(mallBuriedPointSite);	
			
		} catch(DBException dbex) {
			logger.error("MallBuriedPointSiteAction.updateMallBuriedPoint found DBException", dbex);
			return ERROR;
			
		} catch(Exception ex) {
			logger.error("MallBuriedPointSiteAction.updateMallBuriedPoint found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	
	}
	
	/**
	 * 删除商城前端埋点位置
	 * @return
	 */
	public String removeMallBuriedPoint() {

		try {
			mallBuriedPointSiteService.removeMbpsById(mallBuriedPointSite.getId());
			
		} catch(DBException dbex) {
			logger.error("MallBuriedPointSiteAction.removeMallBuriedPoint found DBException", dbex);
			return ERROR;
			
		} catch(Exception ex) {
			logger.error("MallBuriedPointSiteAction.removeMallBuriedPoint found Exception", ex);
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

	public MallBuriedPointSite getMallBuriedPointSite() {
		return mallBuriedPointSite;
	}

	public void setMallBuriedPointSite(MallBuriedPointSite mallBuriedPointSite) {
		this.mallBuriedPointSite = mallBuriedPointSite;
	}

	public Map<String, String> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Map<String, String> queryParams) {
		this.queryParams = queryParams;
	}
	
}
