package com.iuni.dp.admin.dataalarm.action;

import java.util.ArrayList;
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
import com.iuni.dp.admin.common.utils.ActionUtils;
import com.iuni.dp.admin.dataalarm.vo.MsgTypeVO;
import com.iuni.dp.persist.dataalarm.model.MsgType;
import com.iuni.dp.persist.dataalarm.model.ReceivePerson;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.dataalarm.service.MsgTypeService;
import com.iuni.dp.service.dataalarm.service.ReceivePersonService;

/**
 * 消息类型相关信息处理Action
 * @author CaiKe
 * @version V1.0.0
 */
@Controller("msgTypeAction")
@Scope("prototype")
public class MsgTypeAction extends BaseAction {

	private static final long serialVersionUID = -3458032818013528542L;
	private static final Logger logger = LoggerFactory.getLogger(MsgTypeAction.class);
	
	@Autowired
	private MsgTypeService msgTypeService;
	
	@Autowired
	private ReceivePersonService receivePersonService;
	
	private String flag;
	private String relReceiverIds;
	private MsgType msgType;
	private MsgTypeVO msgTypeVO;
	private List<Integer> ckIdList = new ArrayList<Integer>();
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getRelReceiverIds() {
		return relReceiverIds;
	}

	public void setRelReceiverIds(String relReceiverIds) {
		this.relReceiverIds = relReceiverIds;
	}

	public MsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}

	public MsgTypeVO getMsgTypeVO() {
		return msgTypeVO;
	}

	public void setMsgTypeVO(MsgTypeVO msgTypeVO) {
		this.msgTypeVO = msgTypeVO;
	}

	public List<Integer> getCkIdList() {
		return ckIdList;
	}

	public void setCkIdList(List<Integer> ckIdList) {
		this.ckIdList = ckIdList;
	}
	
	/**
	 * 消息类型列表视图
	 * @return String
	 */
	public String msgTypeListView() {

		try {
			//生成查询参数Map
			Map<String, Object> params = genParamMap();
			//消息类型列表总数目查询
			Integer totalRecord = msgTypeService.fetchTotalCount(params);
			//根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
			//新增Page至参数Map
			setPageInfo2Map(params);
			
//			List<MsgType> msgTypes = msgTypeService.getAllMsgTypeList();
			List<MsgType> msgTypes = msgTypeService.fetchMsgTypeByPage(params);
			
			request.setAttribute("msgTypes", msgTypes);
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
		
		if(null != msgTypeVO) {
			params.put("msgTypeCode", msgTypeVO.getMsgTypeCode());
			params.put("msgTypeName", msgTypeVO.getMsgTypeName());
			params.put("flag", msgTypeVO.getFlag());
			params.put("sendEmailFlag", msgTypeVO.getSendEmailFlag());
			params.put("sendSmsFlag", msgTypeVO.getSendSmsFlag());
			params.put("senderStrategyCode", msgTypeVO.getSenderStrategyCode());
			params.put("executeStrategyCode", msgTypeVO.getExecuteStrategyCode());
			params.put("failStrategyCode", msgTypeVO.getFailStrategyCode());
			params.put("beginDate", msgTypeVO.getBeginDate());
			params.put("endDate", msgTypeVO.getEndDate());
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
	 * 消息类型编辑视图
	 * @return String
	 */
	public String msgTypeEditView() {
		
		if(!StringUtils.isBlank(flag) && "update".equals(flag)) {
			try {
				List<MsgType> msgTypes = msgTypeService.getMsgTypeList(msgType);
				if(CollectionUtils.isEmpty(msgTypes)) {
					return ERROR;
				}
				
				request.setAttribute("flag", flag);
				request.setAttribute("msgType", msgTypes.get(0));
				
			} catch(DBException dbex) {
				logger.error("MsgTypeAction.addMsgType found DBException", dbex);
				return ERROR;
				
			} catch(Exception ex) {
				logger.error("MsgTypeAction.addMsgType found Exception", ex);
				return ERROR;
			}
		}
		
		return SUCCESS;
	
	}

	/**
	 * 消息类型关联接收人信息视图
	 * @return String
	 */
	public String relReceiverView() {

		try {
			//分页参数Map
//			Map<String, Object> params = new HashMap<String, Object>();
			//已勾选接收人
			List<ReceivePerson> ckeckedReceivers = new ArrayList<ReceivePerson>();
			
			if(!StringUtils.isBlank(flag) && "update".equals(flag)) {
				//所有接收人对象
				List<ReceivePerson> allReceivers = receivePersonService.fetchAllReceivePerson();
				
				if(!StringUtils.isBlank(relReceiverIds)) {
					String[] relIds = relReceiverIds.split(",");
					
					for(ReceivePerson receiver : allReceivers) {
						if(ckeckedReceivers.size() > relIds.length) {
							break;
						}
						String id = receiver.getReceivePersonId().toString();
						for(String relId : relIds) {
							if(relId.equals(id)) {
								receiver.setRelStatus("1");
								ckeckedReceivers.add(receiver);
							}
						}
					}
				}
				if(!CollectionUtils.isEmpty(ckIdList)) {
					for(ReceivePerson receiver : allReceivers) {
						if(ckeckedReceivers.size() > ckIdList.size()) {
							break;
						}
						String id = receiver.getReceivePersonId().toString();
						for(Integer relId : ckIdList) {
							if(relId == Integer.parseInt(id)) {
								receiver.setRelStatus("1");
								ckeckedReceivers.add(receiver);
							}
						}
					}
				}
				
				//已勾选接收人分页
//				page = Page.genPage(page.getCurrentPage(), ckeckedReceivers.size(), page.getPageSize());
//				
//				if(page.getTotalPage() > 1) {
//					if(page.getCurrentPage() < page.getTotalPage()) {
//						ckeckedReceivers = ckeckedReceivers.subList(page.getStartRec(), page.getStartRec() + page.getEndRec());
//					}
//					else {
//						ckeckedReceivers = ckeckedReceivers.subList(page.getStartRec(), page.getTotalRecord() - 1);
//					}
//				}
				
				request.setAttribute("flag", flag);
				request.setAttribute("receivers", ckeckedReceivers);
			} else {
				//新增消息类型接收人DB分页
//				Integer totalRecord = receivePersonService.fetchTotalCount(params);
//				page = Page.genPage(page.getCurrentPage(), totalRecord, page.getPageSize());
//				setPageInfo2Map(params);
//				List<ReceivePerson> receiversBypage = receivePersonService.fetchReceivePersonByPage(params);
//				
//				if(!CollectionUtils.isEmpty(ckIdList)) {
//					this.setCkIdList(ckIdList);
//				}
				
				List<ReceivePerson> receivers = receivePersonService.fetchAllReceivePerson();
				
				request.setAttribute("receivers", receivers);
			}
			
			request.setAttribute("msgType", msgType);
			
		} catch(DBException dbex) {
			logger.error("MsgTypeAction.relReceiverView found DBException", dbex); 
			return ERROR;
			
		} catch(Exception ex) {
			logger.error("MsgTypeAction.relReceiverView found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	
	}
	
	/**
	 * 新增消息类型
	 * @return String
	 */
	public String addMsgType() {

		try {
			msgType.setCreateTime(new Date());
			msgType.setCreator(ActionUtils.getLoginName());
			
			msgTypeService.saveMsgType(msgType, ckIdList);
			
		} catch(DBException dbex) {
			logger.error("MsgTypeAction.addMsgType found DBException", dbex);
			return ERROR;
			
		} catch(Exception ex) {
			logger.error("MsgTypeAction.addMsgType found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	
	}
	
	public String updateMsgType() {

		try {
			msgTypeService.updateMsgType(msgType, ckIdList);	
			
		} catch(DBException dbex) {
			logger.error("MsgTypeAction.addMsgType found DBException", dbex);
			return ERROR;
			
		} catch(Exception ex) {
			logger.error("MsgTypeAction.addMsgType found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	
	}
	
	public String removeMsgType() {

		try {
			msgTypeService.removeMsgType(msgType);
			
		} catch(DBException dbex) {
			logger.error("MsgTypeAction.addMsgType found DBException", dbex);
			return ERROR;
			
		} catch(Exception ex) {
			logger.error("MsgTypeAction.addMsgType found Exception", ex);
			return ERROR;
		}
		
		return SUCCESS;
	
	}
}
