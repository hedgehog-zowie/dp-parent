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
import com.iuni.dp.persist.datastat.model.MallSaleDailyStat;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.ServiceException;
import com.iuni.dp.service.datastat.service.MallSaleDailyStatService;

/**
 * 金立商城销售数据统计
 * @author ZuoChangjun 2013-8-20
 * @version dp-admin-1.0.0
 */
@Controller("mallSaleDailyStatAction")
@Scope("prototype")
public class MallSaleDailyStatAction extends BaseAction {
	private static final long serialVersionUID = -9059865076156626736L;

	private static final Logger logger = LoggerFactory.getLogger(MallSaleDailyStatAction.class);
	private String beginDate;// 业务数据开始日期
	private String endDate;// 业务数据结束日期
	/**
	 * 销售数据列表
	 */
	private List<MallSaleDailyStat> mallSaleDailyStatList;
	/**
	 * 销售数据总计
	 */
	private MallSaleDailyStat mallSaleSum;
	
	@Autowired
	private MallSaleDailyStatService mallSaleDailyStatService;
	/**
	 * 分页查询销售数据
	 * 
	 * @return
	 */
	public String queryMallSaleDailyStats() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("beginDate", StringUtils.defaultIfEmpty(beginDate, null));
		paramsMap.put("endDate", StringUtils.defaultIfEmpty(endDate, null));
		try {
			// 查询总记录
			Integer totalRecord = mallSaleDailyStatService.queryMallSaleDailyStatsCount(paramsMap);
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
			mallSaleDailyStatList = mallSaleDailyStatService.queryMallSaleDailyStats(paramsMap);
			// 查询总计
			mallSaleSum=mallSaleDailyStatService.queryMallSaleDailyStatsSum(paramsMap);
		} catch (ServiceException se) {
			logger.error("queryMallSaleDailyStats error:" + se.getMessage());
			return ERROR;
		} catch (Exception e) {
			logger.error("queryMallSaleDailyStats error:" + e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 导出excel金立商城销售数据
	 * 
	 * @return
	 */
	public void mallSaleDailyStats2Excel() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		// 设置分页起始值
		paramsMap.put("startRec", 1);
		paramsMap.put("endRec", Integer.MAX_VALUE);
		paramsMap.put("beginDate", StringUtils.defaultIfEmpty(beginDate, null));
		paramsMap.put("endDate", StringUtils.defaultIfEmpty(endDate, null));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = "金立商城销售数据_" + sdf.format(new Date());
		try {
			fileName = new String(fileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.error("mallSaleDailyStats2Excel UnsupportedEncodingException:" + e.getMessage());
		}
		int columnNum = 8;
		ExcelUtils exportExcelUtils = new ExcelUtils(response,fileName, columnNum);
		try {
			// 查询列表
			mallSaleDailyStatList = mallSaleDailyStatService.queryMallSaleDailyStats(paramsMap);
			// 查询总计
			mallSaleSum = mallSaleDailyStatService.queryMallSaleDailyStatsSum(paramsMap);
			mallSaleSum.setBizDate("总计");
			mallSaleDailyStatList.add(mallSaleSum);
			exportExcelUtils.mallSaleDailyStats2Excel(mallSaleDailyStatList);
		} catch (Exception e) {
			logger.error("mallSaleDailyStats2Excel error:" + e.getMessage());
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
	public List<MallSaleDailyStat> getMallSaleDailyStatList() {
		return mallSaleDailyStatList;
	}
	public void setMallSaleDailyStatList(
			List<MallSaleDailyStat> mallSaleDailyStatList) {
		this.mallSaleDailyStatList = mallSaleDailyStatList;
	}
	public MallSaleDailyStat getMallSaleSum() {
		return mallSaleSum;
	}
	public void setMallSaleSum(MallSaleDailyStat mallSaleSum) {
		this.mallSaleSum = mallSaleSum;
	}
}
