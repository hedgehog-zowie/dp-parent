package com.iuni.dp.service.dataalarm.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.dataalarm.dao.ReceivePersonDAO;
import com.iuni.dp.persist.dataalarm.model.ReceivePerson;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.dataalarm.service.ReceivePersonService;

/**
 * 接收人信息相关业务类
 * @author CaiKe
 * @version V1.0.0
 */
@Service(value = "receivePersonService")
public class ReceivePersonServiceImpl implements ReceivePersonService {

	private static final Logger logger = LoggerFactory.getLogger(ReceivePersonServiceImpl.class);
	
	@Autowired
	private ReceivePersonDAO receivePersonDao;
	
	@Override
	public List<ReceivePerson> fetchAllReceivePerson() {
		List<ReceivePerson> receivers = null;
		Long sTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonServiceImpl.fetchAllReceivePerson invoke");
		}
		
		try {
			receivers = receivePersonDao.getAllReceivePerson();
		} catch(DataAccessException ex) {
			logger.error("ReceivePersonServiceImpl.fetchAllReceivePerson found DBException", ex);
			
			throw new DBException(ex);
		}
		
		Long eTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonServiceImpl.fetchAllReceivePerson(ReceivePerson) success: costTime={}ms"
					, new Object[]{eTime - sTime});
		}
		return receivers;
	}
	
	@Override
	public List<ReceivePerson> fetchReceivePersonByPage(
			Map<String, Object> params) {
		List<ReceivePerson> receivers = null;
		try {
			receivers = receivePersonDao.getReceivePersonByPage(params);
			
		} catch(DataAccessException daex) {
			logger.error("ReceivePersonServiceImpl.fetchReceivePersonByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return receivers;
	}

	@Override
	public Integer fetchTotalCount(Map<String, Object> params) {
		Integer count = 0;
		
		try {
			count = receivePersonDao.getTotalCount(params);;
			
		} catch(DataAccessException daex) {
			logger.error("ReceivePersonServiceImpl.fetchTotalCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return count;
	}

	@Override
	public void saveReceivePerson(ReceivePerson receivePerson) {
		Long sTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonServiceImpl.saveReceivePerson(ReceivePerson) entry: receivePerson={}"
					, new Object[]{receivePerson.toString()});
		}
		
		try {
			receivePersonDao.insertReceivePerson(receivePerson);
			
		} catch(DataAccessException ex) {
			logger.error("ReceivePersonServiceImpl.saveReceivePerson found DataAccessException", ex);
			
			throw new DBException(ex);
		}
		
		Long eTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonServiceImpl.saveReceivePerson(ReceivePerson) success: costTime={}ms"
					, new Object[]{eTime - sTime});
		}
		
	}

	@Override
	public ReceivePerson fetchReceivePersonById(String id) {
		Long sTime = System.currentTimeMillis();
		ReceivePerson receivePerson = null;
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonServiceImpl.fetchReceivePersonById(String) parameter: id={}"
					, id);
		}
		
		try {
			receivePerson = receivePersonDao.getReceivePersonById(id);
			
		} catch(DataAccessException ex) {
			logger.error("ReceivePersonServiceImpl.fetchReceivePersonById found DBException", ex);
			
			throw new DBException(ex);
		}
		
		Long eTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonServiceImpl.fetchReceivePersonById(String) success: costTime={}ms"
					, new Object[]{eTime - sTime});
		}
		
		return receivePerson;
	}

	@Override
	public void updateReceivePerson(ReceivePerson receivePerson) {
		Long sTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonServiceImpl.updateReceivePerson(ReceivePerson) entry: receivePerson={}"
					, new Object[]{receivePerson.toString()});
		}
		
		try {
			receivePersonDao.updateReceivePerson(receivePerson);
			
		} catch(DataAccessException ex) {
			logger.error("ReceivePersonServiceImpl.updateReceivePerson found DBException", ex);
			
			throw new DBException(ex);
		}
		
		Long eTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonServiceImpl.updateReceivePerson(ReceivePerson) success: costTime={}ms"
					, new Object[]{eTime - sTime});
		}
	}

	@Override
	public void removeReceivePersonById(String id) {
		Long sTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonServiceImpl.removeReceivePersonById(String) parameter: id={}"
					, id);
		}
		
		try {
			receivePersonDao.deleteReceivePersonById(id);
			
		} catch(DataAccessException ex) {
			logger.error("ReceivePersonServiceImpl.removeReceivePersonById found DBException", ex);
			
			throw new DBException(ex);
		}
		
		Long eTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonServiceImpl.removeReceivePersonById(String) success: costTime={}ms"
					, new Object[]{eTime - sTime});
		}
	}

	@Override
	public List<ReceivePerson> fetchReceivePersonsByMsgTypeId(String msgTypeId) {
		List<ReceivePerson> receivers = null;
		Long sTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonServiceImpl.fetchReceivePersonsByMsgTypeId(String) parameter: msgTypeId={}"
					, msgTypeId);
		}
		
		try {
			receivers = receivePersonDao.getReceivePersonListByMsgTypeId(msgTypeId);
			
		} catch(DataAccessException daex) {
			logger.error("ReceivePersonServiceImpl.fetchReceivePersonsByMsgTypeId found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		Long eTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonServiceImpl.fetchReceivePersonsByMsgTypeId(String) success: costTime={}ms"
					, new Object[]{eTime - sTime});
		}
		
		return receivers;
	}

}
