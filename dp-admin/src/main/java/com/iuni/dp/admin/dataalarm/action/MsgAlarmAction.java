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
import com.iuni.dp.admin.dataalarm.vo.MsgAlarmVO;
import com.iuni.dp.persist.dataalarm.model.MsgAlarm;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.dataalarm.service.MsgAlarmService;

/**
 * 告警消息相关信息处理Action
 * @author CaiKe
 * @version V1.0.0
 */
@Controller("msgAlarmAction")
@Scope("prototype")
public class MsgAlarmAction extends BaseAction {

	private static final long serialVersionUID = 2919498656053636670L;
	private static final Logger logger = LoggerFactory.getLogger(MsgAlarmAction.class);
	
	@Autowired
	private MsgAlarmService msgAlarmService;
	
	private MsgAlarmVO msgAlarmVO;
	
	public MsgAlarmVO getMsgAlarmVO() {
		return msgAlarmVO;
	}

	public void setMsgAlarmVO(MsgAlarmVO msgAlarmVO) {
		this.msgAlarmVO = msgAlarmVO;
	}

	/**
	 * 全部告警对象信息列表视图
	 * @return String
	 */
	public String msgAlarmListView() {

		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap();
			//告警对象信息列表总数目查询
			Integer totalRecord = msgAlarmService.fetchTotalCount(params);
			//根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			//新增Page至参数Map
			setPageInfo2Map(params);
			
//			List<MsgAlarm> msgAlarms = msgAlarmService.getAllMsgAlarm();
			List<MsgAlarm> msgAlarms = msgAlarmService.fetchMsgAlarmByPage(params);
			
			request.setAttribute("msgAlarms", msgAlarms);
			this.setPage(page);
			
		} catch(DBException dbex) {
			logger.error("MsgTypeAction found DBException", dbex);
			return ERROR;
			
		} catch(Exception ex) {
			logger.error("MsgTypeAction found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	
	}
	
	/**
	 * 生成查询参数Map
	 * @return Map<String, Object>
	 */
	private Map<String, Object> genParamMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(null != msgAlarmVO) {
			params.put("msgTypeCode", msgAlarmVO.getMsgSubject());
			params.put("msgTypeName", msgAlarmVO.getExecuteSucessNum());
			params.put("flag", msgAlarmVO.getState());
			params.put("sendEmailFlag", msgAlarmVO.getBeginDate());
			params.put("sendSmsFlag", msgAlarmVO.getEndDate());
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

}
