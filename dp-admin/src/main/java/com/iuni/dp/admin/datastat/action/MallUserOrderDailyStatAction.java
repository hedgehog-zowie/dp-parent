package com.iuni.dp.admin.datastat.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
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

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.admin.datastat.utils.ExcelUtils;
import com.iuni.dp.persist.datastat.model.MallUserOrderDailyStat;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.ServiceException;
import com.iuni.dp.service.datastat.service.MallUserOrderDailyStatService;

/**
 * 金立商城用户注册及销售数据统计
 * @author ZuoChangjun 2013-9-6
 * @version dp-admin-1.0.0
 */
@Controller("mallUserOrderDailyStatAction")
@Scope("prototype")
public class MallUserOrderDailyStatAction extends BaseAction {
	private static final long serialVersionUID = -9059865076156626736L;

	private static final Logger logger = LoggerFactory.getLogger(MallUserOrderDailyStatAction.class);
	private String beginDate;// 注册开始日期
	private String endDate;// 注册结束日期
	/**
	 * 注册用户及销售数据列表
	 */
	private List<MallUserOrderDailyStat> mallUserOrderDailyStatList;
	/**
	 * 注册用户及销售数据总计
	 */
	private MallUserOrderDailyStat mallUserOrderSum;
	
	@Autowired
	private MallUserOrderDailyStatService mallUserOrderDailyStatService;
	/**
	 * 分页查询注册用户及订单数据
	 * 
	 * @return
	 */
	public String queryMallUserOrderDailyStats() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("beginDate", StringUtils.defaultIfEmpty(beginDate, null));
		paramsMap.put("endDate", StringUtils.defaultIfEmpty(endDate, null));
		try {
			// 查询总记录
			Integer totalRecord = mallUserOrderDailyStatService.queryMallUserOrderDailyStatsCount(paramsMap);
			if (totalRecord == null || totalRecord.intValue() == 0) {
				return SUCCESS;
			}
			// 根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord,
					page.getPageSize());
			// 设置分页起始值
			paramsMap.put("startRec", page.getStartRec());
			paramsMap.put("endRec", page.getEndRec());
			// 查询列表
			mallUserOrderDailyStatList = mallUserOrderDailyStatService.queryMallUserOrderDailyStats(paramsMap);
			// 查询总计
			mallUserOrderSum=mallUserOrderDailyStatService.queryMallUserOrderDailyStatsSum(paramsMap);
		} catch (ServiceException se) {
			logger.error("queryMallUserOrderDailyStats error:" + se.getMessage());
			return ERROR;
		} catch (Exception e) {
			logger.error("queryMallUserOrderDailyStats error:" + e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 导出excel金立商城注册用户及销售数据
	 * 
	 * @return
	 */
	public void mallUserOrderDailyStats2Excel() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		// 设置分页起始值
		paramsMap.put("startRec", 1);
		paramsMap.put("endRec", Integer.MAX_VALUE);
		paramsMap.put("beginDate", StringUtils.defaultIfEmpty(beginDate, null));
		paramsMap.put("endDate", StringUtils.defaultIfEmpty(endDate, null));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = "金立商城会员注册_" + sdf.format(new Date());
		try {
			fileName = new String(fileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.error("mallUserOrderDailyStats2Excel UnsupportedEncodingException:" + e.getMessage());
		}
		int columnNum = 8;
		ExcelUtils exportExcelUtils = new ExcelUtils(response,fileName, columnNum);
		try {
			// 查询列表
			mallUserOrderDailyStatList = mallUserOrderDailyStatService.queryMallUserOrderDailyStats(paramsMap);
			// 查询总计
			mallUserOrderSum = mallUserOrderDailyStatService.queryMallUserOrderDailyStatsSum(paramsMap);
			mallUserOrderSum.setBizDate("总计");
			mallUserOrderDailyStatList.add(mallUserOrderSum);
			exportExcelUtils.mallUserOrderDailyStats2Excel(mallUserOrderDailyStatList);
		} catch (Exception e) {
			logger.error("mallUserOrderDailyStats2Excel error:" + e.getMessage());
		}
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<MallUserOrderDailyStat> getMallUserOrderDailyStatList() {
		return mallUserOrderDailyStatList;
	}

	public void setMallUserOrderDailyStatList(
			List<MallUserOrderDailyStat> mallUserOrderDailyStatList) {
		this.mallUserOrderDailyStatList = mallUserOrderDailyStatList;
	}

	public MallUserOrderDailyStat getMallUserOrderSum() {
		return mallUserOrderSum;
	}

	public void setMallUserOrderSum(MallUserOrderDailyStat mallUserOrderSum) {
		this.mallUserOrderSum = mallUserOrderSum;
	}

}
