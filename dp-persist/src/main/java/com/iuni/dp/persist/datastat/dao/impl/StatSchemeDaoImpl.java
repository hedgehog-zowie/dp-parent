package com.iuni.dp.persist.datastat.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.common.dao.impl.BaseDaoImpl;
import com.iuni.dp.persist.datastat.dao.StatSchemeDao;
import com.iuni.dp.persist.datastat.model.StatScheme;

/**
 * 统计分析计划DAO实现类
 * @author CaiKe
 * @version dp-persist-1.0.0
 */
@Repository("statSchemeDao")
public class StatSchemeDaoImpl extends BaseDaoImpl<StatScheme, Serializable> implements StatSchemeDao {

	private static final Logger logger = LoggerFactory.getLogger(StatSchemeDaoImpl.class);
	
	@Override
	public StatScheme selectStatSchemeById(Integer id) {
		logger.debug("StatSchemeDaoImpl.selectStatSchemeById(Integer) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		
		StatScheme statScheme = (StatScheme) getById(StatScheme.class.getSimpleName()+ ".selectStatSchemeById"
				, id);
		
		logger.debug("StatSchemeDaoImpl.selectStatSchemeById(Integer) success: statScheme={}, costTime={}ms",
				new Object[] { statScheme.toString(), System.currentTimeMillis() - stime });
		return statScheme;
	}

	@Override
	public List<StatScheme> selectAllStatScheme(Map<String, Object> params) {
		logger.debug("StatSchemeDaoImpl.selectAllStatScheme(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<StatScheme> statSchemes = findAllObjectsByPage(StatScheme.class.getSimpleName() + ".selectAllStatScheme", params);
		
		logger.debug("StatSchemeDaoImpl.selectAllStatScheme(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return statSchemes;
	}

	@Override
	public List<StatScheme> selectStatSchemeByPage(Map<String, Object> params) {
		logger.debug("StatSchemeDaoImpl.selectStatSchemeByPage(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		List<StatScheme> statSchemes = findAllObjectsByPage(StatScheme.class.getSimpleName() + ".selectStatSchemeByPage", params);
		
		logger.debug("StatSchemeDaoImpl.selectStatSchemeByPage(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return statSchemes;
	}

	@Override
	public Integer selectTotalCount(Map<String, Object> params) {
		logger.debug("StatSchemeDaoImpl.selectTotalCount(Map<String, Object>) invoke");
		Long stime = System.currentTimeMillis();
		
		Integer count = (Integer) getObjectByProperty(StatScheme.class.getSimpleName() + ".selectTotalCount", params);
		
		logger.debug("StatSchemeDaoImpl.selectTotalCount(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return count;
	}

	@Override
	public Integer insertStatScheme(StatScheme statScheme) {
		logger.debug("StatSchemeDaoImpl.insertStatScheme(StatScheme) entry: statScheme={}",
				new Object[] { statScheme.toString() });
		long stime = System.currentTimeMillis();
		
		Integer insertIndex = (Integer) insert(statScheme, StatScheme.class.getSimpleName() + ".insertStatScheme");
		
		logger.debug("StatSchemeDaoImpl.insertStatScheme(StatScheme) success: insertIndex={}, costTime={}ms",
				new Object[] { insertIndex, System.currentTimeMillis() - stime });
		return insertIndex;
	}

	@Override
	public Integer updateStatScheme(StatScheme statScheme) {
		logger.debug("StatSchemeDaoImpl.updateStatScheme(StatScheme) entry: statScheme={}",
				new Object[] { statScheme.toString() });
		long stime = System.currentTimeMillis();
		Integer execCount = 0; 
		
		execCount = update(statScheme, StatScheme.class.getSimpleName() + ".updateStatScheme");
		
		logger.debug("StatSchemeDaoImpl.updateStatScheme(StatScheme) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		return execCount;
	}

	@Override
	public Integer deleteStatSchemeById(Integer id) {
		logger.debug("StatSchemeDaoImpl.deleteStatSchemeById(Integer) entry: id={}",
				new Object[] { id });
		long stime = System.currentTimeMillis();
		Integer execCount = 0;
		
		execCount = delete(id, StatScheme.class.getSimpleName() + ".deleteStatSchemeById");
		
		logger.debug("StatSchemeDaoImpl.deleteStatSchemeById(Integer) success: execCount={}, costTime={}ms",
				new Object[] { execCount, System.currentTimeMillis() - stime });
		return execCount;
	}

	@Override
	public Integer callProcStatPfResponse(Map<String, Object> params) {
		logger.debug("StatSchemeDaoImpl.callStatPfResponse(Map<String, Object>) invoke.");
		long stime = System.currentTimeMillis();
		Integer execStatus = 0;
		
		execStatus = (Integer) getObjectByProperty(StatScheme.class.getSimpleName() + ".callProcStatPfResponse", params);
		
		logger.debug("StatSchemeDaoImpl.callStatPfResponse(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return execStatus;
	}

	@Override
	public Integer callProcStatPfDomCtxLoaded(Map<String, Object> params) {
		logger.debug("StatSchemeDaoImpl.callStatPfDomCtxLoaded(Map<String, Object>) invoke.");
		long stime = System.currentTimeMillis();
		Integer execStatus = 0;
		
		execStatus = (Integer) getObjectByProperty(StatScheme.class.getSimpleName() + ".callProcStatPfDomCtxLoaded", params);
		
		logger.debug("StatSchemeDaoImpl.callStatPfDomCtxLoaded(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return execStatus;
	}

	@Override
	public Integer callProcStatDailyMallBaseData(Map<String, Object> params) {
		logger.debug("StatSchemeDaoImpl.callProcStatDailyMallBaseData(Map<String, Object>) invoke.");
		long stime = System.currentTimeMillis();
		Integer execStatus = 0;
		
		execStatus = (Integer) getObjectByProperty(StatScheme.class.getSimpleName() + ".callProcStatDailyMallBaseData", params);
		
		logger.debug("StatSchemeDaoImpl.callProcStatDailyMallBaseData(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return execStatus;
	}
	
	@Override
	public Integer callProcStatGnAppBaseDataDaily(Map<String, Object> params) {
		logger.debug("StatSchemeDaoImpl.callProcStatGnAppBaseDataDaily(Map<String, Object>) invoke.");
		long stime = System.currentTimeMillis();
		Integer execStatus = 0;
		
		execStatus = (Integer) getObjectByProperty(StatScheme.class.getSimpleName() + ".callProcStatGnAppBaseDataDaily", params);
		
		logger.debug("StatSchemeDaoImpl.callProcStatGnAppBaseDataDaily(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return execStatus;
	}

	@Override
	public Integer callProcStatIuniNetflowDataDaily(Map<String, Object> params) {
		logger.debug("StatSchemeDaoImpl.callProcStatIuniNetflowDataDaily(Map<String, Object>) invoke.");
		long stime = System.currentTimeMillis();
		Integer execStatus = 0;
		
		execStatus = (Integer) getObjectByProperty(StatScheme.class.getSimpleName() + ".callProcStatIuniNetflowDataDaily", params);
		
		logger.debug("StatSchemeDaoImpl.callProcStatIuniNetflowDataDaily(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return execStatus;
	}

	@Override
	public Integer callProcStatIpAreaDataDaily(Map<String, Object> params) {
		logger.debug("StatSchemeDaoImpl.callProcStatIpAreaDataDaily(Map<String, Object>) invoke.");
		long stime = System.currentTimeMillis();
		Integer execStatus = 0;
		
		execStatus = (Integer) getObjectByProperty(StatScheme.class.getSimpleName() + ".callProcStatIpAreaDataDaily", params);
		
		logger.debug("StatSchemeDaoImpl.callProcStatIpAreaDataDaily(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return execStatus;
	}

	@Override
	public Integer callProcStatIuniBpDataDaily(Map<String, Object> params) {
		logger.debug("StatSchemeDaoImpl.callProcStatIuniBpDataDaily(Map<String, Object>) invoke.");
		long stime = System.currentTimeMillis();
		Integer execStatus = 0;
		
		execStatus = (Integer) getObjectByProperty(StatScheme.class.getSimpleName() + ".callProcStatIuniBpDataDaily", params);
		
		logger.debug("StatSchemeDaoImpl.callProcStatIuniBpDataDaily(Map<String, Object>) success: costTime={}ms",
				new Object[] { System.currentTimeMillis() - stime });
		return execStatus;
	}

	/**
	 * 统计当天的前一天各个广告的各个推广渠道(入口点)的PV和UV(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertMallAdPvuvDailyStatByTask(Map<String,Object> paramMap){
		//return (Integer) sqlMapClientTemplate.insert("MallAdPvuvDailyStat.insertMallAdPvuvDailyStatByTask",paramMap);
		return (Integer) insert(paramMap,StatScheme.class.getSimpleName() +".insertMallAdPvuvDailyStatByTask");
	}
	
	/**
	 * 统计当天的前一天各个广告的各个推广渠道(入口点)的有效订单数、总订单数和退单数(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertMallAdOrderDailyStatByTask(Map<String,Object> paramMap){
		//return (Integer) sqlMapClientTemplate.insert("MallAdPvuvDailyStat.insertMallAdOrderDailyStatByTask",paramMap);
		return (Integer) insert(paramMap,StatScheme.class.getSimpleName() +".insertMallAdOrderDailyStatByTask");
	}
	
	/**
	 * 统计前22天的那一天的各个广告的各个推广渠道(入口点)的有效订单数、总订单数和退单数(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertMallAdOrderDailyStatFinalByTask(Map<String,Object> paramMap){
		//return (Integer) sqlMapClientTemplate.insert("MallAdPvuvDailyStat.insertMallAdPvuvDailyStatByTask",paramMap);
		return (Integer) insert(paramMap,StatScheme.class.getSimpleName() +".insertMallAdOrderDailyStatFinalByTask");
	}

	/**
	 * 清空金立商城当天之前的所有销售情况：包括订单总数、订单总金额、订单商品总件数、在线支付订单数、货到付款订单数、退货订单数、拒收订单数、有效订单数、有效订单总金额、有效订单商品总件数、已支付订单数、预约且已支付订单数(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer clearMallSaleDailyStatByTask(){
		return (Integer)getSqlMapClientTemplate().delete(StatScheme.class.getSimpleName() +".clearMallSaleDailyStatByTask");
	}
	/**
	 * 统计金立商城当天之前的所有销售情况：包括订单总数、订单总金额、订单商品总件数、在线支付订单数、货到付款订单数、退货订单数、拒收订单数、有效订单数、有效订单总金额、有效订单商品总件数、已支付订单数、预约且已支付订单数(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertMallSaleDailyStatByTask(Map<String,Object> paramMap){
		return (Integer) insert(paramMap,StatScheme.class.getSimpleName() +".insertMallSaleDailyStatByTask");
	}
	
	/**
	 * 清空金立商城当天之前的所有会员注册及订单信息：包括注册会员数、订单总数、订单总金额、有效订单数、有效订单总金额、会员平均订单数、会员平均购物金额
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer clearMallUserOrderDailyStatByTask(){
		return (Integer)getSqlMapClientTemplate().delete(StatScheme.class.getSimpleName() +".clearMallUserOrderDailyStatByTask");
	}
	
	/**
	 * 统计金立商城当天之前的所有会员注册及订单信息：包括注册会员数、订单总数、订单总金额、有效订单数、有效订单总金额、会员平均订单数、会员平均购物金额
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertMallUserOrderDailyStatByTask(Map<String,Object> paramMap){
		return (Integer) insert(paramMap,StatScheme.class.getSimpleName() +".insertMallUserOrderDailyStatByTask");
	}
	
	/**
	 * 清空金立商城CPS推广销售情况：包括订单总数、退货订单数、拒收订单数、有效订单数
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer clearMallYqfOrderDailyStatByTask(){
		return (Integer)getSqlMapClientTemplate().delete(StatScheme.class.getSimpleName() +".clearMallYqfOrderDailyStatByTask");
	}
	/**
	 * 统计金立商城CPS推广销售情况：包括订单总数、退货订单数、拒收订单数、有效订单数
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertMallYqfOrderDailyStatByTask(Map<String,Object> paramMap){
		return (Integer) insert(paramMap,StatScheme.class.getSimpleName() +".insertMallYqfOrderDailyStatByTask");
	}
	
	/**
	 * 清空IUNI商城当天之前的所有销售情况：包括订单总数、订单总金额、订单商品总件数、在线支付订单数、货到付款订单数、退货订单数、拒收订单数、有效订单数、有效订单总金额、有效订单商品总件数、已支付订单数、预约且已支付订单数(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer clearIuniSaleDailyStatByTask(){
		return (Integer)getSqlMapClientTemplate().delete(StatScheme.class.getSimpleName() +".clearIuniSaleDailyStatByTask");
	}
	/**
	 * 统计金立商城当天之前的所有销售情况：包括订单总数、订单总金额、订单商品总件数、在线支付订单数、货到付款订单数、退货订单数、拒收订单数、有效订单数、有效订单总金额、有效订单商品总件数、已支付订单数、预约且已支付订单数(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertIuniSaleDailyStatByTask(Map<String,Object> paramMap){
		return (Integer) insert(paramMap,StatScheme.class.getSimpleName() +".insertIuniSaleDailyStatByTask");
	}
	/**
	 * 统计Iuni商城前一天用户注册数
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertIuniUserRegDailyStatByTask(Map<String,Object> paramMap){
		return (Integer) insert(paramMap,StatScheme.class.getSimpleName() +".insertIuniUserRegDailyStatByTask");
	}
	
	/**
	 * 统计Iuni商城前一天PV、UV
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertIuniPagePvuvDailyStatByTask(Map<String,Object> paramMap){
		return (Integer) insert(paramMap,StatScheme.class.getSimpleName() +".insertIuniPagePvuvDailyStatByTask");
	}
}
