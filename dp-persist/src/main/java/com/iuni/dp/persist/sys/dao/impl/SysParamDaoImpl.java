package com.iuni.dp.persist.sys.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.sys.dao.SysParamDao;
import com.iuni.dp.persist.sys.model.SysParam;

@Repository("sysParamDao")
public class SysParamDaoImpl extends BaseDaoImpl<SysParam, Serializable> implements SysParamDao {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);

	public void insertSysParam(SysParam sysParam) {
		insert(sysParam, SysParamDao.class.getSimpleName()+ ".insertSysParam");
	}

	public void updateSysParam(SysParam sysParam) {
		getSqlMapClientTemplate().update(SysParamDao.class.getSimpleName() + ".updateSysParam",sysParam);

	}

	public void deleteSysParamByParamName(String paramName) {
		getSqlMapClientTemplate().delete(SysParamDao.class.getSimpleName() + ".deleteSysParam",paramName);
	}

	@SuppressWarnings("unchecked")
	public List<SysParam> getAllSysParam(Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList(SysParamDao.class.getSimpleName() + ".getSysParams", map);

	}

	@Override
	public SysParam getSysParamByName(String name) {
		return (SysParam) getById(SysParamDao.class.getSimpleName()+ ".getSysParamsByParamName", name);
	}

	public static void main(String[] args) {
		// ApplicationContext context= new
		// ClassPathXmlApplicationContext("classpath:config/spring/applicationContext*.xml");
		// //ApplicationContext context = new ClassPathXmlApplicationContext(new
		// String[]{"classpath:config/spring/applicationContext.xml","classpath:config/spring/applicationContext-jdbc.xml","classpath:config/spring/applicationContext-service.xml"});
		// SysParamDao sysParamDao=(SysParamDao) context.getBean("sysParamDao");
		//
		// SysParam sysParam = new SysParam();
		// sysParam.setParamName("test3");
		// sysParam.setParamVal("test3");
		// sysParam.setParamDesc("dd测试修改dd");
		// sysParam.setModifier(22L);
		// sysParam.setCreateor(11L);
		//
		// // sysParamDao.insertSysParam(sysParam);
		//
		// // sysParamDao.updateSysParam(sysParam);
		//
		// // sysParamDao.deleteSysParamByParamName("test1");
		//
		// List<SysParam> list = sysParamDao.getAllSysParam(new HashMap());
		// System.out.println(list);
		//
		//
		// // sysParam = sysParamDao.getSysParamByName("test2");
		// System.out.println(sysParam);
	}

}
