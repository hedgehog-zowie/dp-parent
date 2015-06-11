package com.iuni.dp.service.dataalarm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.iuni.dp.persist.dataalarm.dao.MsgTypeDAO;
import com.iuni.dp.persist.dataalarm.dao.ReceivePersonDAO;
import com.iuni.dp.persist.dataalarm.model.MsgType;
import com.iuni.dp.persist.dataalarm.model.PersonRelMsgType;
import com.iuni.dp.persist.dataalarm.model.ReceivePerson;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.common.utils.JsonUtil;
import com.iuni.dp.service.dataalarm.service.MsgTypeService;

/**
 * 消息类型相关处理业务类
 * @author CaiKe
 * @version V1.0.0
 */
@Service("msgTypeService")
public class MsgTypeServiceImpl implements MsgTypeService {
	
	private final static Logger logger = LoggerFactory.getLogger(MsgTypeServiceImpl.class);
	private static final JsonUtil jsonUtils = new JsonUtil();
	private static Map<String, String> msgTypeMap = new ConcurrentHashMap<String, String>();
	
	@Autowired
	private ReceivePersonDAO receivePersonDao;
	
	private MsgTypeDAO msgTypeDao;
	
	@Autowired
	public void setMsgTypeDao(MsgTypeDAO msgTypeDao) {
		this.msgTypeDao = msgTypeDao;
		if (msgTypeDao != null) {
			refreshMsgType();
		}
	}
	
	public static Map<String, String> getMsgTypeMap() {
		return msgTypeMap;
	}
	
	public static MsgType getMsgTypeOjb(String msgTypeId){
		String msgTypeStr = getMsgTypeMap().get(msgTypeId);
		return jsonUtils.fromJson(msgTypeStr, MsgType.class);
	}
	
	@Override
	public MsgType getMsgTypeByMsgTypeId(String msgTypeId) {
		long stime = System.currentTimeMillis();
		logger.debug("MsgTypeServiceImpl.getMsgTypeByMsgTypeId(String) entry: msgTypeId={}",new Object[] { msgTypeId });
		
		MsgType findMsgType = getMsgTypeOjb(msgTypeId);
		if(null == findMsgType) {
			findMsgType = msgTypeDao.getMsgTypeByMsgTypeId(msgTypeId);
			updateMsgTypeCache(findMsgType);
		} 
		
//		MsgType findMsgType = (getMsgTypeOjb(msgTypeId) != null) 
//				? getMsgTypeOjb(msgTypeId) : msgTypeDao.getMsgTypeByMsgTypeId(msgTypeId);
				
		logger.debug("MsgTypeServiceImpl.getMsgTypeByMsgTypeId(String) success: costTime={}ms",
					new Object[] {System.currentTimeMillis() - stime });
		return findMsgType;	
	}
	
	@Override
	public List<MsgType> getMsgTypeList(MsgType msgType){
		long stime = System.currentTimeMillis();
		logger.debug("MsgTypeServiceImpl.getMsgTypeList(MsgType) entry: msgType={}",new Object[] { msgType.toString() });
		
		List<MsgType> msgTypeList =  msgTypeDao.getMsgTypeList(msgType);
		
		int msgTypeListCount = (msgTypeList != null) ? msgTypeList.size() : 0;
		logger.debug("MsgTypeServiceImpl.getMsgTypeList(MsgType) success: msgTypeListCount={},costTime={}ms",
				new Object[] {msgTypeListCount,System.currentTimeMillis() - stime });
		return msgTypeList;
	}
	
	@Override
	public List<MsgType> getAllMsgTypeList() {
		List<MsgType> msgTypes = null;
		
		try {
			msgTypes = msgTypeDao.getAllMsgTypeList();
			
		} catch(DataAccessException daex) {
			logger.error("MsgTypeServiceImpl.getAllMsgTypeList found DataAccessException", daex);
			throw new DBException(daex);
		}
		return msgTypes;
	}
	
	@Override
	public List<MsgType> fetchMsgTypeByPage(Map<String, Object> params) {
		List<MsgType> msgTypes = null;
		
		try {
			msgTypes = msgTypeDao.getMsgTypeByPage(params);
			
		} catch(DataAccessException daex) {
			logger.error("MsgTypeServiceImpl.fetchMsgTypeByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return msgTypes;
	}

	@Override
	public Integer fetchTotalCount(Map<String, Object> params) {
		Integer count = 0;
		
		try {
			count = msgTypeDao.getTotalCount(params);
			
		} catch(DataAccessException daex) {
			logger.error("MsgTypeServiceImpl.fetchTotalCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return count;
	}

	@Override
	public void refreshMsgType() {
		long stime = System.currentTimeMillis();
		logger.debug("MsgTypeServiceImpl.refreshMsgType()");
		
		List<MsgType> msgTypeList = getMsgTypeList(new MsgType());
		if (msgTypeList!=null && msgTypeList.size()>0) {
			for (MsgType msgType : msgTypeList) {
				msgTypeMap.put(String.valueOf(msgType.getMsgTypeId()), jsonUtils.toJson(msgType));
			}
			logger.debug("MsgTypeServiceImpl.refreshMsgType() success: costTime={}ms",
					new Object[] {System.currentTimeMillis() - stime });
		}else {
			logger.info("MsgTypeServiceImpl.refreshMsgType(),msgTypeList is null or msgTypeList'size is zero");
		}
	}
	
	@Override
	public void updateMsgTypeCache(MsgType msgType) {
		if(null != msgType) {
			msgTypeMap.put(String.valueOf(msgType.getMsgTypeId()), jsonUtils.toJson(msgType));
		}
	}

	/**
	 * 保存消息类型以及与之相关的接收人列表关系，作为一统一事务处理
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public void saveMsgType(MsgType msgType, List<Integer> receivePersonIds) {
		List<PersonRelMsgType> relTypes = new ArrayList<PersonRelMsgType>();
		try {
			Integer msgTypeId = msgTypeDao.insertMsgType(msgType);
			for(Integer receivePersonId : receivePersonIds) {
				PersonRelMsgType relType = new PersonRelMsgType();
				relType.setMsgTypeId(msgTypeId);
				relType.setReceivePersonId(receivePersonId);
				relTypes.add(relType);
			}
			msgTypeDao.batchInsertPersonRelMsgType(relTypes);
		} catch(DataAccessException daex) {
			logger.error("MsgTypeServiceImpl.saveMsgType found DataAccessException", daex);
			throw new DBException(daex);
		} 
	}
	
	/**
	 * 更新消息类型以及与之相关的接收人列表关系，作为一统一事务处理
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public void updateMsgType(MsgType msgType, List<Integer> receivePersonIds) {
		List<PersonRelMsgType> relTypes = new ArrayList<PersonRelMsgType>();
		
		try {
			Integer msgTypeId = msgType.getMsgTypeId();
			
			List<ReceivePerson> receivers = receivePersonDao.getReceivePersonListByMsgTypeId(msgTypeId.toString());
			
			msgTypeDao.updateMsgType(msgType);
			
			if(!CollectionUtils.isEmpty(receivers)) {
				for(ReceivePerson receiver : receivers) {
					Integer receivePersonId = receiver.getReceivePersonId();
					boolean isExist = receivePersonIds.contains(receivePersonId);
					if(!isExist) {
						PersonRelMsgType relType = new PersonRelMsgType();
						relType.setMsgTypeId(msgTypeId);
						relType.setReceivePersonId(receivePersonId);
						
						relTypes.add(relType);
					}
				}
			}
			
			msgTypeDao.batchDeletePersonRelMsgType(relTypes);
			
		} catch(DataAccessException daex) {
			logger.error("MsgTypeServiceImpl.updateMsgType found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		
	}

	/**
	 * 删除消息类型以及与之相关的接收人列表关系，作为一统一事务处理
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public void removeMsgType(MsgType msgType) {
		try {
			Integer msgTypeId = msgType.getMsgTypeId();
			PersonRelMsgType personRelMsgType = new PersonRelMsgType();
			personRelMsgType.setMsgTypeId(msgTypeId);
			
			msgTypeDao.deleteMsgTypeById(msgTypeId);
			msgTypeDao.deletePersonRelMsgType(personRelMsgType);
			
		} catch(DataAccessException daex) {
			logger.error("MsgTypeServiceImpl.removeMsgType found DataAccessException", daex);
			throw new DBException(daex);
		}
		
	}
	
}
