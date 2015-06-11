package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.iuni.dp.persist.datastat.dao.StatSchemeDao;
import com.iuni.dp.persist.datastat.model.StatScheme;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datastat.service.StatSchemeService;

/**
 * 统计分析计划Service业务类
 * @author CaiKe
 * @version dp-service-1.0.0
 */
@Service("statSchemeService")
public class StatSchemeServiceImpl implements StatSchemeService {

	private final static Logger logger = LoggerFactory.getLogger(StatSchemeServiceImpl.class);
	
	@Autowired
	private StatSchemeDao statSchemeDao;
	
	@Override
	public StatScheme getStatSchemeById(Integer id) {
		StatScheme statScheme = null;
		
		try {
			statScheme = statSchemeDao.selectStatSchemeById(id);
			logger.debug("StatSchemeServiceImpl.getStatSchemeById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatSchemeServiceImpl.getStatSchemeById found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return statScheme;
	}

	@Override
	public List<StatScheme> getAllStatScheme(Map<String, Object> params) {
		List<StatScheme> statSchemes = null;
		
		try {
			statSchemes = statSchemeDao.selectAllStatScheme(params);
			logger.debug("StatSchemeServiceImpl.getAllStatScheme invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatSchemeServiceImpl.getAllStatScheme found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return statSchemes;
	}

	@Override
	public List<StatScheme> getStatSchemeByPage(Map<String, Object> params) {
		List<StatScheme> statSchemes = null;
		
		try {
			statSchemes = statSchemeDao.selectStatSchemeByPage(params);
			logger.debug("StatSchemeServiceImpl.getStatSchemeByPage invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatSchemeServiceImpl.getStatSchemeByPage found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return statSchemes;
	}

	@Override
	public Integer getTotalCount(Map<String, Object> params) {
		Integer totalCount = 0;
		
		try {
			totalCount = statSchemeDao.selectTotalCount(params);
			logger.debug("StatSchemeServiceImpl.getTotalCount invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatSchemeServiceImpl.getTotalCount found DataAccessException", daex);
			throw new DBException(daex);
		}
		
		return totalCount;
	}

	@Override
	public Integer saveStatScheme(StatScheme statScheme) {
		Integer execCount = 0;
		
		try {
			execCount = statSchemeDao.insertStatScheme(statScheme);
			logger.debug("StatSchemeServiceImpl.saveStatScheme invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatSchemeServiceImpl.saveStatScheme found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Override
	public Integer updateStatScheme(StatScheme statScheme) {
		Integer execCount = 0;
		
		try {
			execCount = statSchemeDao.updateStatScheme(statScheme);
			logger.debug("StatSchemeServiceImpl.updateStatScheme invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatSchemeServiceImpl.updateStatScheme found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Override
	public Integer removeStatSchemeById(Integer id) {
		Integer execCount = 0;
		
		try {
			execCount = statSchemeDao.deleteStatSchemeById(id);
			logger.debug("StatSchemeServiceImpl.removeStatSchemeById invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatSchemeServiceImpl.removeStatSchemeById found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execCount;
	}

	@Transactional(isolation=Isolation.READ_COMMITTED,timeout=30)
	@Override
	public Integer invokeProcStatPfResponse(Map<String, Object> params) {
		Integer execStatus = 0;
		
		try {
			execStatus = statSchemeDao.callProcStatPfResponse(params);
			logger.debug("StatSchemeServiceImpl.invokeProcStatPfResponse invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatSchemeServiceImpl.invokeProcStatPfResponse found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execStatus;
	}

	@Transactional(isolation=Isolation.READ_COMMITTED,timeout=30)
	@Override
	public Integer invokeProcStatPfDomCtxLoaded(Map<String, Object> params) {
		Integer execStatus = 0;
		
		try {
			execStatus = statSchemeDao.callProcStatPfDomCtxLoaded(params);
			logger.debug("StatSchemeServiceImpl.invokeProcStatPfDomCtxLoaded invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatSchemeServiceImpl.invokeProcStatPfDomCtxLoaded found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execStatus;
	}

	@Transactional(isolation=Isolation.READ_COMMITTED,timeout=30)
	@Override
	public Integer invokeProcStatDailyMallBasedata(Map<String, Object> params) {
		Integer execStatus = 0;
		
		try {
			execStatus = statSchemeDao.callProcStatDailyMallBaseData(params);
			logger.debug("StatSchemeServiceImpl.invokeProcStatDailyMallBasedata invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatSchemeServiceImpl.invokeProcStatDailyMallBasedata found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execStatus;
	}

	@Transactional(isolation=Isolation.READ_COMMITTED,timeout=30)
	@Override
	public Integer invokeProcStatGnAppBaseDataDaily(Map<String, Object> params) {
		Integer execStatus = 0;
		
		try {
			execStatus = statSchemeDao.callProcStatGnAppBaseDataDaily(params);
			logger.debug("StatSchemeServiceImpl.invokeProcStatGnAppBaseDataDaily invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatSchemeServiceImpl.invokeProcStatGnAppBaseDataDaily found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execStatus;
	}

	@Transactional(isolation=Isolation.READ_COMMITTED,timeout=30)
	@Override
	public Integer invokeProcStatIuniNetflowDataDaily(Map<String, Object> params) {
		Integer execStatus = 0;
		
		try {
			execStatus = statSchemeDao.callProcStatIuniNetflowDataDaily(params);
			logger.debug("StatSchemeServiceImpl.invokeProcStatIuniNetflowDataDaily invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatSchemeServiceImpl.invokeProcStatIuniNetflowDataDaily found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execStatus;
	}

	@Transactional(isolation=Isolation.READ_COMMITTED,timeout=30)
	@Override
	public Integer invokeProcStatIpAreaDataDaily(Map<String, Object> params) {
		Integer execStatus = 0;
		
		try {
			execStatus = statSchemeDao.callProcStatIpAreaDataDaily(params);
			logger.debug("StatSchemeServiceImpl.invokeProcStatIpAreaDataDaily invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatSchemeServiceImpl.invokeProcStatIpAreaDataDaily found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execStatus;
	}

	@Transactional(isolation=Isolation.READ_COMMITTED,timeout=30)
	@Override
	public Integer invokeProcStatIuniBpDataDaily(Map<String, Object> params) {
		Integer execStatus = 0;
		
		try {
			execStatus = statSchemeDao.callProcStatIuniBpDataDaily(params);
			logger.debug("StatSchemeServiceImpl.invokeProcStatIuniBpDataDaily invoke success");
			
		} catch(DataAccessException daex) {
			logger.error("StatSchemeServiceImpl.invokeProcStatIuniBpDataDaily found DataAccessException" ,daex);
			throw new DBException(daex);
		}
		
		return execStatus;
	}

	/**
	 * 统计当天的前一天各个广告的各个推广渠道(入口点)的PV和UV(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	@Transactional
	public Integer insertMallAdPvuvDailyStatByTask(Map<String,Object> paramMap){
		Integer count = 0;
		
		try {
			count = statSchemeDao.insertMallAdPvuvDailyStatByTask(paramMap);
			
		} catch(Exception e) {
			logger.error("StatSchemeServiceImpl.insertMallAdPvuvDailyStatByTask error：" ,e);
			throw new DBException(e);
		}
		
		return count;
	}
	
	/**
	 * 统计当天的前一天各个广告的各个推广渠道(入口点)的有效订单数、总订单数和退单数(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	@Transactional
	public Integer insertMallAdOrderDailyStatByTask(Map<String,Object> paramMap){
		Integer count = 0;
		
		try {
			count = statSchemeDao.insertMallAdOrderDailyStatByTask(paramMap);
			
		} catch(Exception e) {
			logger.error("StatSchemeServiceImpl.insertMallAdOrderDailyStatByTask error：" ,e);
			throw new DBException(e);
		}
		
		return count;
	}
	
	/**
	 * 统计前22天的那一天的各个广告的各个推广渠道(入口点)的有效订单数、总订单数和退单数(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertMallAdOrderDailyStatFinalByTask(Map<String,Object> paramMap){

		Integer count = 0;
		
		try {
			count = statSchemeDao.insertMallAdOrderDailyStatFinalByTask(paramMap);
			
		} catch(Exception e) {
			logger.error("StatSchemeServiceImpl.insertMallAdOrderDailyStatFinalByTask error：" ,e);
			throw new DBException(e);
		}
		
		return count;
	
	}
	
	/**
	 * 清空金立商城当天之前的所有销售情况：包括订单总数、订单总金额、订单商品总件数、在线支付订单数、货到付款订单数、退货订单数、拒收订单数、有效订单数、有效订单总金额、有效订单商品总件数、已支付订单数、预约且已支付订单数(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer clearMallSaleDailyStatByTask(){
		Integer count = 0;
		
		try {
			count = statSchemeDao.clearMallSaleDailyStatByTask();
			
		} catch(Exception e) {
			logger.error("StatSchemeServiceImpl.clearMallSaleDailyStatByTask error：" ,e);
			throw new DBException(e);
		}
		
		return count;
	
	
	}
	/**
	 * 统计金立商城当天之前的所有销售情况：包括订单总数、订单总金额、订单商品总件数、在线支付订单数、货到付款订单数、退货订单数、拒收订单数、有效订单数、有效订单总金额、有效订单商品总件数、已支付订单数、预约且已支付订单数(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertMallSaleDailyStatByTask(Map<String,Object> paramMap){
		Integer count = 0;
		
		try {
			count = statSchemeDao.insertMallSaleDailyStatByTask(paramMap);
			
		} catch(Exception e) {
			logger.error("StatSchemeServiceImpl.insertMallSaleDailyStatByTask error：" ,e);
			throw new DBException(e);
		}
		
		return count;
	}
	/**
	 * 清空金立商城当天之前的所有会员注册及订单信息：包括注册会员数、订单总数、订单总金额、有效订单数、有效订单总金额、会员平均订单数、会员平均购物金额
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer clearMallUserOrderDailyStatByTask(){

		Integer count = 0;
		
		try {
			count = statSchemeDao.clearMallUserOrderDailyStatByTask();
			
		} catch(Exception e) {
			logger.error("StatSchemeServiceImpl.clearMallUserOrderDailyStatByTask error：" ,e);
			throw new DBException(e);
		}
		
		return count;
	
	
	
	}
	
	/**
	 * 统计金立商城当天之前的所有会员注册及订单信息：包括注册会员数、订单总数、订单总金额、有效订单数、有效订单总金额、会员平均订单数、会员平均购物金额
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertMallUserOrderDailyStatByTask(Map<String,Object> paramMap){

		Integer count = 0;
		
		try {
			count = statSchemeDao.insertMallUserOrderDailyStatByTask(paramMap);
			
		} catch(Exception e) {
			logger.error("StatSchemeServiceImpl.insertMallUserOrderDailyStatByTask error：" ,e);
			throw new DBException(e);
		}
		
		return count;
	
	}
	
	/**
	 * 清空金立商城CPS推广销售情况：包括订单总数、退货订单数、拒收订单数、有效订单数
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer clearMallYqfOrderDailyStatByTask(){


		Integer count = 0;
		
		try {
			count = statSchemeDao.clearMallYqfOrderDailyStatByTask();
			
		} catch(Exception e) {
			logger.error("StatSchemeServiceImpl.clearMallYqfOrderDailyStatByTask error：" ,e);
			throw new DBException(e);
		}
		
		return count;
	
	
	
	
	}
	/**
	 * 统计金立商城CPS推广销售情况：包括订单总数、退货订单数、拒收订单数、有效订单数
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertMallYqfOrderDailyStatByTask(Map<String,Object> paramMap){


		Integer count = 0;
		
		try {
			count = statSchemeDao.insertMallYqfOrderDailyStatByTask(paramMap);
			
		} catch(Exception e) {
			logger.error("StatSchemeServiceImpl.insertMallYqfOrderDailyStatByTask error：" ,e);
			throw new DBException(e);
		}
		
		return count;
	
	
	}
	
	/**
	 * 清空Iuni商城当天之前的所有销售情况：包括订单总数、订单总金额、订单商品总件数、在线支付订单数、货到付款订单数、退货订单数、拒收订单数、有效订单数、有效订单总金额、有效订单商品总件数、已支付订单数、预约且已支付订单数(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer clearIuniSaleDailyStatByTask(){
		Integer count = 0;
		
		try {
			count = statSchemeDao.clearIuniSaleDailyStatByTask();
			
		} catch(Exception e) {
			logger.error("StatSchemeServiceImpl.clearMallSaleDailyStatByTask error：" ,e);
			throw new DBException(e);
		}
		
		return count;
	
	
	}
	/**
	 * 统计IUNI商城当天之前的所有销售情况：包括订单总数、订单总金额、订单商品总件数、在线支付订单数、货到付款订单数、退货订单数、拒收订单数、有效订单数、有效订单总金额、有效订单商品总件数、已支付订单数、预约且已支付订单数(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertIuniSaleDailyStatByTask(Map<String,Object> paramMap){
		Integer count = 0;
		
		try {
			count = statSchemeDao.insertIuniSaleDailyStatByTask(paramMap);
			
		} catch(Exception e) {
			logger.error("StatSchemeServiceImpl.insertMallSaleDailyStatByTask error：" ,e);
			throw new DBException(e);
		}
		
		return count;
	}
	/**
	 * 统计Iuni商城前一天用户注册数
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertIuniUserRegDailyStatByTask(Map<String,Object> paramMap){
		Integer count = 0;
		
		try {
			count = statSchemeDao.insertIuniUserRegDailyStatByTask(paramMap);
			
		} catch(Exception e) {
			logger.error("StatSchemeServiceImpl.insertIuniUserRegDailyStatByTask error：" ,e);
			throw new DBException(e);
		}
		
		return count;
	}
	
	/**
	 * 统计Iuni商城前一天PV、UV
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertIuniPagePvuvDailyStatByTask(Map<String,Object> paramMap){
		Integer count = 0;
		
		try {
			count = statSchemeDao.insertIuniPagePvuvDailyStatByTask(paramMap);
			
		} catch(Exception e) {
			logger.error("StatSchemeServiceImpl.insertIuniPagePvuvDailyStatByTask error：" ,e);
			throw new DBException(e);
		}
		
		return count;
	}
}
