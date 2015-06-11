package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.StatScheme;

/**
 * 统计分析计划Service
 * @author CaiKe
 * @version dp-service-1.0.0
 */
public interface StatSchemeService {

	/**
	 * 根据ID查询统计分析计划
	 * @param Integer id
	 * @return StatScheme
	 */
	public StatScheme getStatSchemeById(Integer id);
	
	/**
	 * 统计分析计划条件查询
	 * @param Map<String, Object> params
	 * @return List<StatScheme>
	 */
	public List<StatScheme> getAllStatScheme(Map<String, Object> params);
	
	/**
	 * 统计分析计划分页条件查询
	 * @param Map<String, Object> params
	 * @return List<StatScheme>
	 */
	public List<StatScheme> getStatSchemeByPage(Map<String, Object> params);
	
	/**
	 * 统计分析计划总数目查询
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer getTotalCount(Map<String, Object> params);
	
	/**
	 * 新增统计分析计划
	 * @param StatScheme StatScheme
	 * @return Integer
	 */
	public Integer saveStatScheme(StatScheme statScheme);
	
	/**
	 * 修改统计分析计划
	 * @param StatScheme StatScheme
	 */
	public Integer updateStatScheme(StatScheme statScheme);
	
	/**
	 * 删除统计分析计划
	 * @param Integer id
	 */
	public Integer removeStatSchemeById(Integer id);
	
	/**
	 * 调用存储过程stat_pf_responseEnd
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer invokeProcStatPfResponse(Map<String, Object> params);
	
	/**
	 * 调用存储过程stat_pf_domCtxLoadedEnd
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer invokeProcStatPfDomCtxLoaded(Map<String, Object> params);
	
	/**
	 * 调用存储过程stat_daily_mallbasedata
	 * @param Map<String, Object> params
	 * @return Integer
	 */
	public Integer invokeProcStatDailyMallBasedata(Map<String, Object> params);
	
	/**
	 * 调用存储过程stat_gnapp_basedata_daily
	 * @param params
	 * @return Integer
	 */
	public Integer invokeProcStatGnAppBaseDataDaily(Map<String, Object> params);
	
	/**
	 * 调用存储过程stat_iuni_netflowdata_daily
	 * @param params
	 * @return Integer
	 */
	public Integer invokeProcStatIuniNetflowDataDaily(Map<String, Object> params);
	
	/**
	 * 调用存储过程stat_iuni_iparea_daily
	 * @param params
	 * @return Integer
	 */
	public Integer invokeProcStatIpAreaDataDaily(Map<String, Object> params);
	
	/**
	 * 调用存储过程stat_iuni_bpdata_daily
	 * @param params
	 * @return Integer
	 */
	public Integer invokeProcStatIuniBpDataDaily(Map<String, Object> params);
	
	/**
	 * 统计当天的前一天各个广告的各个推广渠道(入口点)的PV和UV(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertMallAdPvuvDailyStatByTask(Map<String,Object> paramMap);
	
	/**
	 * 统计当天的前一天各个广告的各个推广渠道(入口点)的有效订单数、总订单数和退单数(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertMallAdOrderDailyStatByTask(Map<String,Object> paramMap);
	
	/**
	 * 统计前22天的那一天的各个广告的各个推广渠道(入口点)的有效订单数、总订单数和退单数(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertMallAdOrderDailyStatFinalByTask(Map<String,Object> paramMap);
	
	/**
	 * 清空金立商城当天之前的所有销售情况：包括订单总数、订单总金额、订单商品总件数、在线支付订单数、货到付款订单数、退货订单数、拒收订单数、有效订单数、有效订单总金额、有效订单商品总件数、已支付订单数、预约且已支付订单数(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer clearMallSaleDailyStatByTask();
	
	/**
	 * 统计金立商城当天之前的所有销售情况：包括订单总数、订单总金额、订单商品总件数、在线支付订单数、货到付款订单数、退货订单数、拒收订单数、有效订单数、有效订单总金额、有效订单商品总件数、已支付订单数、预约且已支付订单数(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertMallSaleDailyStatByTask(Map<String,Object> paramMap);
	
	/**
	 * 清空金立商城当天之前的所有会员注册及订单信息：包括注册会员数、订单总数、订单总金额、有效订单数、有效订单总金额、会员平均订单数、会员平均购物金额
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer clearMallUserOrderDailyStatByTask();
	
	/**
	 * 统计金立商城当天之前的所有会员注册及订单信息：包括注册会员数、订单总数、订单总金额、有效订单数、有效订单总金额、会员平均订单数、会员平均购物金额
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertMallUserOrderDailyStatByTask(Map<String,Object> paramMap);
	
	/**
	 * 清空金立商城CPS推广销售情况：包括订单总数、退货订单数、拒收订单数、有效订单数
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer clearMallYqfOrderDailyStatByTask();
	
	/**
	 * 统计金立商城CPS推广销售情况：包括订单总数、退货订单数、拒收订单数、有效订单数
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertMallYqfOrderDailyStatByTask(Map<String,Object> paramMap);
	
	/**
	 * 清空Iuni商城当天之前的所有销售情况：包括订单总数、订单总金额、订单商品总件数、在线支付订单数、货到付款订单数、退货订单数、拒收订单数、有效订单数、有效订单总金额、有效订单商品总件数、已支付订单数、预约且已支付订单数(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer clearIuniSaleDailyStatByTask();
	
	/**
	 * 统计Iuni商城当天之前的所有销售情况：包括订单总数、订单总金额、订单商品总件数、在线支付订单数、货到付款订单数、退货订单数、拒收订单数、有效订单数、有效订单总金额、有效订单商品总件数、已支付订单数、预约且已支付订单数(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertIuniSaleDailyStatByTask(Map<String,Object> paramMap);
	
	
	/**
	 * 统计Iuni商城前一天用户注册数
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertIuniUserRegDailyStatByTask(Map<String,Object> paramMap);
	
	/**
	 * 统计Iuni商城前一天PV、UV
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertIuniPagePvuvDailyStatByTask(Map<String,Object> paramMap);
	
}
