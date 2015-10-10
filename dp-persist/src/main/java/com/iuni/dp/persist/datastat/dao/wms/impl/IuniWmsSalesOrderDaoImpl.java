package com.iuni.dp.persist.datastat.dao.wms.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.wms.IuniWmsSalesOrderDao;
import com.iuni.dp.persist.datastat.model.IuniWmsSalesOrder;

/**
 * IuniWmsSalesOrder DAO
 * @author Kenneth.Cai@iuni.com
 * @version dp-persist-1.1.5
 */
@Repository("iuniWmsSalesOrderDao")
public class IuniWmsSalesOrderDaoImpl extends
		BaseDaoImpl<IuniWmsSalesOrder, Serializable> implements
		IuniWmsSalesOrderDao {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public List<Map<String, Object>> selectIuniWmsSalesOrderStatByExample(Map<String, Object> params) {
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniWmsSalesOrderStatByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniWmsSalesOrder.class.getSimpleName() 
				+ ".selectIuniWmsSalesOrderStatByExample", params);
		
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniWmsSalesOrderStatByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniWmsSalesOrderStatByPage(Map<String, Object> params) {
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniWmsSalesOrderStatByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniWmsSalesOrder.class.getSimpleName() 
				+ ".selectIuniWmsSalesOrderStatByPage", params);
		
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniWmsSalesOrderStatByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIuniWmsSalesOrderStatCount(Map<String, Object> params) {
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniWmsSalesOrderStatCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(IuniWmsSalesOrder.class.getSimpleName() 
				+ ".selectIuniWmsSalesOrderStatCount", params);
		
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniWmsSalesOrderStatCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectIuniStockMovDetailsByExample(
			Map<String, Object> params) {
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniStockMovDetailsByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniWmsSalesOrder.class.getSimpleName() 
				+ ".selectIuniStockMovDetailsByExample", params);
		
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniStockMovDetailsByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniStockMovDetailsByPage(
			Map<String, Object> params) {
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniStockMovDetailsByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniWmsSalesOrder.class.getSimpleName() 
				+ ".selectIuniStockMovDetailsByPage", params);
		
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniStockMovDetailsByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIuniStockMovDetailsCount(Map<String, Object> params) {
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniStockMovDetailsCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(IuniWmsSalesOrder.class.getSimpleName() 
				+ ".selectIuniStockMovDetailsCount", params);
		
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniStockMovDetailsCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectIuniNoInvoiceSalesDetailsByExample(
			Map<String, Object> params) {
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniNoInvoiceSalesDetailsByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniWmsSalesOrder.class.getSimpleName() 
				+ ".selectIuniNoInvoiceSalesDetailsByExample", params);
		
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniNoInvoiceSalesDetailsByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniNoInvoiceSalesDetailsByPage(
			Map<String, Object> params) {
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniNoInvoiceSalesDetailsByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniWmsSalesOrder.class.getSimpleName() 
				+ ".selectIuniNoInvoiceSalesDetailsByPage", params);
		
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniNoInvoiceSalesDetailsByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIuniNoInvoiceSalesDetailsCount(
			Map<String, Object> params) {
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniNoInvoiceSalesDetailsCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(IuniWmsSalesOrder.class.getSimpleName() 
				+ ".selectIuniNoInvoiceSalesDetailsCount", params);
		
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniNoInvoiceSalesDetailsCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectIuniWmsPayAmountCheckByExample(
			Map<String, Object> params) {
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniWmsPayAmountCheckByExample(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniWmsSalesOrder.class.getSimpleName() 
				+ ".selectIuniWmsPayAmountCheckByExample", params);
		
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniWmsPayAmountCheckByExample(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public List<Map<String, Object>> selectIuniWmsPayAmountCheckByPage(
			Map<String, Object> params) {
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniWmsPayAmountCheckByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<Map<String, Object>> list = findAllObjectsByPage2(IuniWmsSalesOrder.class.getSimpleName() 
				+ ".selectIuniWmsPayAmountCheckByPage", params);
		
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniWmsPayAmountCheckByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIuniWmsPayAmountCheckCount(Map<String, Object> params) {
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniWmsPayAmountCheckCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();

		Integer count = (Integer) getObjectByProperty(IuniWmsSalesOrder.class.getSimpleName()
				+ ".selectIuniWmsPayAmountCheckCount", params);

		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniWmsPayAmountCheckCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public List<Map<String, Object>> selectIuniRebatesDetailByPage(Map<String, Object> params) {
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniRebatesDetailByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();

		List<Map<String, Object>> list = findAllObjectsByPage2(IuniWmsSalesOrder.class.getSimpleName()
				+ ".selectIuniRebatesDetailByPage", params);

		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniRebatesDetailByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return list;
	}

	@Override
	public Integer selectIuniRebatesDetailCount(Map<String, Object> params) {
		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniRebatesDetailCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();

		Integer count = (Integer) getObjectByProperty(IuniWmsSalesOrder.class.getSimpleName()
				+ ".selectIuniRebatesDetailCount", params);

		logger.debug("IuniWmsSalesOrderDaoImpl.selectIuniRebatesDetailCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

}
