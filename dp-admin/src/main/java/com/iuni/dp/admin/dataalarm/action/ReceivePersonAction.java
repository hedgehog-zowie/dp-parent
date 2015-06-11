package com.iuni.dp.admin.dataalarm.action;

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

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.admin.common.utils.ActionUtils;
import com.iuni.dp.admin.dataalarm.vo.ReceivePersonVO;
import com.iuni.dp.persist.dataalarm.model.ReceivePerson;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.dataalarm.service.ReceivePersonService;

/**
 * 接收人相关信息处理Action
 * @author CaiKe
 * @version V1.0.0
 */
@Controller("receivePersonAction")
@Scope("prototype")
public class ReceivePersonAction extends BaseAction{

	private static final long serialVersionUID = 1297956697512752333L;
	private static final Logger logger = LoggerFactory.getLogger(ReceivePersonAction.class);
	
	@Autowired
	private ReceivePersonService receivePersonService;
	
	private String flag;
	private ReceivePerson receivePerson;
	private ReceivePersonVO receivePersonVO;
	private String msgTypeId;
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public ReceivePerson getReceivePerson() {
		return receivePerson;
	}

	public void setReceivePerson(ReceivePerson receivePerson) {
		this.receivePerson = receivePerson;
	}

	public ReceivePersonVO getReceivePersonVO() {
		return receivePersonVO;
	}

	public void setReceivePersonVO(ReceivePersonVO receivePersonVO) {
		this.receivePersonVO = receivePersonVO;
	}

	public String getMsgTypeId() {
		return msgTypeId;
	}

	public void setMsgTypeId(String msgTypeId) {
		this.msgTypeId = msgTypeId;
	}
	
	/**
	 * 接收人信息列表视图
	 * @return String
	 */
	public String receiverListView() {

		List<ReceivePerson> receivers = null;
		try {
			if(StringUtils.isBlank(msgTypeId)) {
				//生成查询参数Map
				Map<String, Object> params = genParamMap();
				//接收人信息列表总数目分页查询
				Integer totalRecord = receivePersonService.fetchTotalCount(params);
				//根据当前页、总记录数、页大小获得Page
				page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
				//新增Page至参数Map
				setPageInfo2Map(params);
//				receivers = receivePersonService.fetchAllReceivePerson();
				receivers = receivePersonService.fetchReceivePersonByPage(params);
				
			} else {
				receivers = receivePersonService.fetchReceivePersonsByMsgTypeId(msgTypeId);
			}
			
			request.setAttribute("receivers", receivers);
			request.setAttribute("flag", flag);
			this.setPage(page);
			
		} catch(DBException dbex) {
			logger.error("ReceivePersonAction.receiverSaveView found DBException", dbex);
			return ERROR;
		} catch(Exception ex) {
			logger.error("ReceivePersonAction.receiverSaveView found Exception", ex);
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
		
		if(null != receivePersonVO) {
			params.put("code", receivePersonVO.getCode());
			params.put("name", receivePersonVO.getName());
			params.put("mobile", receivePersonVO.getMobile());
			params.put("email", receivePersonVO.getEmail());
			params.put("beginDate", receivePersonVO.getBeginDate());
			params.put("endDate", receivePersonVO.getEndDate());
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
	 * 接收人信息编辑视图
	 * @return String
	 */
	public String receiverEditView() {

		if(!StringUtils.isBlank(flag) && "update".equals(flag)) {
			try {
				String id = receivePerson.getReceivePersonId().toString();
				ReceivePerson receiver = receivePersonService.fetchReceivePersonById(id);
				
				request.setAttribute("flag", flag);
				request.setAttribute("receiver", receiver);
				
			} catch(DBException dbex) {
				logger.error("ReceivePersonAction.receiverSaveView found DBException", dbex);
				return ERROR;
			} catch(Exception ex) {
				logger.error("ReceivePersonAction.receiverSaveView found Exception", ex);
				return ERROR;
			}
		}
		
		return SUCCESS;
	
	}
	
	/**
	 * 新增接收人信息
	 * @return String
	 */
	public String addReceiver() {
	
		receivePerson.setCreator(ActionUtils.getLoginName());
		receivePerson.setCreateTime(new Date());
		
		try {
			receivePersonService.saveReceivePerson(receivePerson);
			
		} catch(DBException dbex) {
			logger.error("ReceivePersonAction.receiverSaveView found DBException", dbex);
			return ERROR;
		} catch(Exception ex) {
			logger.error("ReceivePersonAction.receiverSaveView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	
	}
	
	/**
	 * 更新对应接收人信息
	 * @return String
	 */
	public String updateReceiver() {

		try {
			receivePersonService.updateReceivePerson(receivePerson);
			request.setAttribute("receiver", receivePerson);
			
		} catch(DBException dbex) {
			logger.error("ReceivePersonAction.updateReceiver found DBException", dbex);
			return ERROR;
		} catch(Exception ex) {
			logger.error("ReceivePersonAction.updateReceiver found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	
	}
	
	/**
	 * 删除对应接收人信息
	 * @return String
	 */
	public String removeReceiver() {

		try {
			String receiverId = receivePerson.getReceivePersonId().toString();
			receivePersonService.removeReceivePersonById(receiverId);
			
		} catch(DBException dbex) {
			logger.error("ReceivePersonAction.removeReceiver found DBException", dbex);
			return ERROR;
		} catch(Exception ex) {
			logger.error("ReceivePersonAction.removeReceiver found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	
	}
	
}
