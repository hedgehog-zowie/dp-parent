package com.iuni.dp.persist.dataalarm.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.dataalarm.dao.ReceivePersonDAO;
import com.iuni.dp.persist.dataalarm.model.ReceivePerson;

@Repository("receivePersonDao")
public class ReceivePersonDAOImpl extends BaseDaoImpl<ReceivePerson, Serializable> implements ReceivePersonDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ReceivePersonDAOImpl.class);
	
    @Override
    public List<ReceivePerson> getReceivePersonListByMsgTypeId(String msgTypeId) {
		logger.debug("ReceivePersonDAOImpl.getReceivePersonListByMsgTypeId(String) entry: msgAlarm={}",new Object[] { msgTypeId });
		long stime = System.currentTimeMillis();
		List<ReceivePerson> receivePersonList =  findAllObjectsByPage(ReceivePerson.class.getSimpleName()+ ".getReceivePersonListByMsgTypeId", msgTypeId);
		logger.debug("ReceivePersonDAOImpl.getReceivePersonListByMsgTypeId(String) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return receivePersonList;
	}
	
	@Override
	public void insertReceivePerson(ReceivePerson receivePerson) {
		Long sTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonDAOImpl.insertReceivePerson(ReceivePerson) entry: receivePerson={}"
					, new Object[]{receivePerson.toString()});
		}
			
		insert(receivePerson, ReceivePerson.class.getSimpleName() + ".insertReceivePerson");
		
		Long eTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonDAOImpl.insertReceivePerson(ReceivePerson) success: costTime={}ms"
					, new Object[]{eTime - sTime});
		}
		
	}

	@Override
	public List<ReceivePerson> getAllReceivePerson() {
		Long sTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonDAOImpl.getAllReceivePerson invoke ");
		}
			
		List<ReceivePerson> receivers = getAll(ReceivePerson.class.getSimpleName() + ".getAllReceivePerson");
		
		Long eTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonDAOImpl.getAllReceivePerson success: costTime={}ms"
					, new Object[]{eTime - sTime});
		}
		
		return receivers;
	}

	@Override
	public List<ReceivePerson> getReceivePersonByPage(Map<String, Object> params) {
		Long sTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonDAOImpl.getReceivePersonByPage(Map<String, Object>) invoke ");
		}
		List<ReceivePerson> receivers = findAllObjectsByPage(ReceivePerson.class.getSimpleName() 
				+ ".getReceivePersonByPage", params);
		
		logger.debug("ReceivePersonDAOImpl.getReceivePersonByPage(Map<String, Object>) success: costTime={}ms"
				, new Object[]{System.currentTimeMillis() - sTime});
		return receivers;
	}

	@Override
	public Integer getTotalCount(Map<String, Object> params) {
		Long sTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonDAOImpl.getReceivePersonByPage(Map<String, Object>) invoke ");
		}
		
		Integer count = (Integer) getObjectByProperty(ReceivePerson.class.getSimpleName() + ".getTotalCount", params);
		
		logger.debug("ReceivePersonDAOImpl.getReceivePersonByPage(Map<String, Object>) success: costTime={}ms"
				, new Object[]{System.currentTimeMillis() - sTime});
		return count;
	}

	@Override
	public ReceivePerson getReceivePersonById(String id) {
		Long sTime = System.currentTimeMillis();
		ReceivePerson receivePerson = null;
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonDAOImpl.getReceivePersonById(String) parameter: id={}"
					,id);
		}
		
		receivePerson = (ReceivePerson) getById(ReceivePerson.class.getSimpleName() + ".getReceivePersonById", Integer.parseInt(id));
		
		Long eTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonDAOImpl.getReceivePersonById(String) success: costTime={}ms"
					, new Object[]{eTime - sTime});
		}
		
		return receivePerson;
	}

	@Override
	public void updateReceivePerson(ReceivePerson receivePerson) {
		Long sTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonDAOImpl.updateReceivePerson(ReceivePerson) entry: receivePerson={}"
					,new Object[]{receivePerson.toString()});
		}
		
		update(receivePerson, ReceivePerson.class.getSimpleName() + ".updateReceivePerson");
		
		Long eTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonDAOImpl.updateReceivePerson(ReceivePerson) success: costTime={}ms"
					, new Object[]{eTime - sTime});
		}
	}

	@Override
	public void deleteReceivePersonById(String id) {
		Long sTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonDAOImpl.deleteReceivePersonById(String) parameter: id={}"
					,id);
		}
		
		delete(Integer.parseInt(id), ReceivePerson.class.getSimpleName() + ".deleteReceivePersonById");
		
		Long eTime = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {
			logger.debug("ReceivePersonDAOImpl.deleteReceivePersonById(String) success: costTime={}ms"
					, new Object[]{eTime - sTime});
		}
	}
	
}
