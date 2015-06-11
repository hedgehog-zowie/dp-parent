package com.iuni.dp.admin.dataalarm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.admin.dataalarm.vo.BusLogVO;
import com.iuni.dp.persist.dataalarm.model.BusLog;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.dataalarm.service.BusLogService;

@Controller("busLogAction")
@Scope("prototype")
public class BusLogAction extends BaseAction {

	private static final long serialVersionUID = 1781203911284490252L;
	private static final Logger logger = LoggerFactory.getLogger(BusLogAction.class);

	private BusLogVO busLogVO;
	
	public BusLogVO getBusLogVO() {
		return busLogVO;
	}

	public void setBusLogVO(BusLogVO busLogVO) {
		this.busLogVO = busLogVO;
	}

	@Autowired
	private BusLogService busLogService;
	
	public String busLogListView() {

		try {
			//生成查询参数Map
			Map<String,Object> params = genParamMap();
			
			//业务日志信息列表总数目条件查询
			Integer totalRecord = busLogService.fetchTotalCount(params);
			
			//根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			
			//新增Page至参数Map
			setPageInfo2Map(params, page);
			
			List<BusLog> busLogs = busLogService.fetchBusLogByPage(params);
			
			request.setAttribute("busLogs", busLogs);
			request.setAttribute("page", page);
			
		} catch(DBException dbex) {
			logger.error("MsgTypeAction.busLogListView found DBException", dbex);
			return ERROR;
			
		} catch(Exception ex) {
			logger.error("MsgTypeAction.busLogListView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	
	}
	
	/**
	 * 生成查询参数Map
	 * @return Map<String,Object>
	 */
	private Map<String,Object> genParamMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		if(null != busLogVO) {
			map.put("busType", busLogVO.getBusType());
			map.put("executResult", busLogVO.getExecutResult());
			map.put("beginDate", busLogVO.getBeginDate());
			map.put("endDate", busLogVO.getEndDate());
		}
		return map;
	}
	
	/**
	 * 新增Page至参数Map
	 * @param params
	 * @param page
	 */
	private void setPageInfo2Map(Map<String,Object> params, Page page) {
		params.put("startRec", page.getStartRec());
		params.put("endRec", page.getEndRec());
	}
	
}
