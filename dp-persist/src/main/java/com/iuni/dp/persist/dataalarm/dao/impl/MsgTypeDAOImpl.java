package com.iuni.dp.persist.dataalarm.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.dataalarm.dao.MsgTypeDAO;
import com.iuni.dp.persist.dataalarm.model.MsgType;
import com.iuni.dp.persist.dataalarm.model.PersonRelMsgType;

@Repository("msgTypeDao")
public class MsgTypeDAOImpl extends BaseDaoImpl<MsgType, Serializable> implements MsgTypeDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MsgTypeDAOImpl.class);
	
	@Override
	public MsgType getMsgTypeByMsgTypeId(String msgTypeId) {
		logger.debug("MsgTypeDAOImpl.getMsgTypeByMsgTypeId(String) entry: msgTypeId={}",
				new Object[] { msgTypeId });
		long stime = System.currentTimeMillis();
		
		MsgType msgType = (MsgType) getById(MsgType.class.getSimpleName()+ ".getMsgTypeByMsgTypeId"
				, msgTypeId);
		
		logger.debug("MsgTypeDAOImpl.getMsgTypeByMsgTypeId(String) success: msgType={},costTime={}ms",
				new Object[] { msgType.toString(),System.currentTimeMillis() - stime });
		return msgType;
	}
	
	@Override
	public List<MsgType> getMsgTypeList(MsgType msgType){
		logger.debug("MsgTypeDAOImpl.getMsgTypeList(MsgType) entry: msgType={}",
				new Object[] { msgType.toString() });
		long stime = System.currentTimeMillis();
		
		List<MsgType> msgTypeList = findAllObjectsByPage(MsgType.class.getSimpleName()+ ".getMsgTypeList"
				, msgType);
		
		int msgTypeListCount = (msgTypeList!=null )? msgTypeList.size():0;
		logger.debug("MsgTypeDAOImpl.getMsgTypeList(MsgType) success: msgTypeListCount={},costTime={}ms",
				new Object[] { msgTypeListCount,System.currentTimeMillis() - stime });
		return msgTypeList;
	}
	
	@Override
	public List<MsgType> getAllMsgTypeList() {
		logger.debug("MsgTypeDAOImpl.getMsgTypeList invoke");
		Long stime = System.currentTimeMillis();
		
		List<MsgType> msgTypes = getAll(MsgType.class.getSimpleName() + ".getAllMsgTypeList");
		
		logger.debug("MsgTypeDAOImpl.getMsgTypeList(MsgType) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return msgTypes;
	}
	
	@Override
	public List<MsgType> getMsgTypeByPage(Map<String, Object> params) {
		logger.debug("MsgTypeDAOImpl.getMsgTypeByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<MsgType> msgTypes = findAllObjectsByPage(MsgType.class.getSimpleName() + ".getMagTypeByPage", params);
		
		logger.debug("MsgTypeDAOImpl.getMsgTypeByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return msgTypes;
	}

	@Override
	public Integer getTotalCount(Map<String, Object> params) {
		logger.debug("MsgTypeDAOImpl.getTotalCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(MsgType.class.getSimpleName() + ".getTotalCount", params);
		
		logger.debug("MsgTypeDAOImpl.getTotalCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public Integer insertMsgType(MsgType msgType) {
		logger.debug("MsgTypeDAOImpl.insertMsgType(MsgType) entry: msgType={}",
				new Object[] { msgType.toString() });
		long stime = System.currentTimeMillis();
		
		Integer msgTypeId = (Integer) insert(msgType, MsgType.class.getSimpleName() + ".insertMsgType");
		
		logger.debug("MsgTypeDAOImpl.getMsgTypeList(MsgType) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return msgTypeId;
	}

	@Override
	public Integer insertPersonRelMsgType(PersonRelMsgType personRelMsgType) {
		logger.debug("MsgTypeDAOImpl.insertPersonRelMsgType(PersonRelMsgType) entry: personRelMsgType={}",
				new Object[] { personRelMsgType.toString() });
		long stime = System.currentTimeMillis();
		
		Integer personRelMsgTypeId = (Integer) insert(personRelMsgType, MsgType.class.getSimpleName() + ".insertPersonRelMsgType");
		
		logger.debug("MsgTypeDAOImpl.getMsgTypeList(MsgType) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		
		return personRelMsgTypeId;
	}

	@Override
	public List<Integer> batchInsertPersonRelMsgType(
			final List<PersonRelMsgType> personRelMsgTypes) {
		
		final List<Integer> result = new ArrayList<Integer>();
		Integer typesParam = (personRelMsgTypes != null) ? personRelMsgTypes.size() : 0;
		logger.debug("MsgTypeDAOImpl.batchInsertPersonRelMsgType(List<PersonRelMsgType>) entry: personRelMsgTypes={}"
				,new Object[] { typesParam });
		
		getSqlMapClientTemplate().execute( new SqlMapClientCallback<Integer>() {
			@Override
			public Integer doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				Long stime = System.currentTimeMillis();
				
				for(PersonRelMsgType personRelMsgType : personRelMsgTypes) {
					if(null != personRelMsgType) {
						Integer id = (Integer) executor.insert(MsgType.class.getSimpleName() 
								+ ".insertPersonRelMsgType", personRelMsgType);
						result.add(id);
					}
				}
				
				int execCount = executor.executeBatch();
				logger.debug("MsgTypeDAOImpl.batchInsertPersonRelMsgType(List<PersonRelMsgType>) success: execCount={},costTime={}ms",
						new Object[] { execCount,System.currentTimeMillis() - stime });
				return execCount;
			}
		});
		
		return result;
	}

	@Override
	public Integer updateMsgType(MsgType msgType) {
		logger.debug("MsgTypeDAOImpl.updateMsgType(MsgType) entry: msgType={}",
				new Object[] { msgType.toString() });
		long stime = System.currentTimeMillis();
		
		int id = update(msgType, MsgType.class.getSimpleName() + ".updateMsgType");
		
		logger.debug("MsgTypeDAOImpl.updateMsgType(MsgType) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return id;
	}

	@Override
	public Integer deletePersonRelMsgType(PersonRelMsgType personRelMsgType) {
		logger.debug("MsgTypeDAOImpl.deletePersonRelMsgType(PersonRelMsgType) entry: personRelMsgType={}",
				new Object[] { personRelMsgType.toString() });
		long stime = System.currentTimeMillis();
		
		int id = delete(personRelMsgType, MsgType.class.getSimpleName() + ".deletePersonRelMsgType");
		
		logger.debug("MsgTypeDAOImpl.deletePersonRelMsgType(PersonRelMsgType) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return id;
	}

	@Override
	public List<Integer> batchDeletePersonRelMsgType(
			final List<PersonRelMsgType> personRelMsgTypes) {
		final List<Integer> result = new ArrayList<Integer>();
		Integer typesParam = (personRelMsgTypes != null) ? personRelMsgTypes.size() : 0;
		logger.debug("MsgTypeDAOImpl.batchDeletePersonRelMsgType(List<PersonRelMsgType>) entry: personRelMsgTypes={}"
				,new Object[] { typesParam });
		
		getSqlMapClientTemplate().execute(new SqlMapClientCallback<Integer>(){
			@Override
			public Integer doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				Long stime = System.currentTimeMillis();
				
				for(PersonRelMsgType personRelMsgType : personRelMsgTypes) {
					if(null != personRelMsgType) {
						Integer id = executor.delete(MsgType.class.getSimpleName() + ".deletePersonRelMsgType"
								, personRelMsgType);
						result.add(id);
					}
				}
				
				int execCount = executor.executeBatch();
				logger.debug("MsgTypeDAOImpl.batchDeletePersonRelMsgType(List<PersonRelMsgType>) success: execCount={},costTime={}ms",
						new Object[] { execCount,System.currentTimeMillis() - stime });
				return execCount;
			}});
		
		return result;
	}

	@Override
	public Integer deleteMsgTypeById(Integer id) {
		logger.debug("MsgTypeDAOImpl.deleteMsgType(Integer) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		
		int msgTypeId = delete(id, MsgType.class.getSimpleName() + ".deleteMsgTypeById");
		
		logger.debug("MsgTypeDAOImpl.deleteMsgType(MsgType) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return msgTypeId;
	}
	
}
