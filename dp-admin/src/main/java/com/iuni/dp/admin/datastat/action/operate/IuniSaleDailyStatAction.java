package com.iuni.dp.admin.datastat.action.operate;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.iuni.dp.admin.datastat.constants.OrderRefer;
import com.iuni.dp.persist.datastat.common.model.OrderSource;
import com.iuni.dp.service.datastat.service.common.OrderSourceService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iuni.dp.admin.common.action.BaseAction;
import com.iuni.dp.admin.datastat.utils.ExcelUtils;
import com.iuni.dp.persist.datastat.model.IuniDayOrderConvertRate;
import com.iuni.dp.persist.datastat.model.IuniDayPvuvUserReg;
import com.iuni.dp.persist.datastat.model.IuniSaleDailyStat;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.ServiceException;
import com.iuni.dp.service.datastat.service.IuniSaleDailyStatService;

/**
 * IUNI商城销售数据统计
 * @author ZuoChangjun 2013-8-20
 * @version dp-admin-1.0.0
 */
@Controller("iuniSaleDailyStatAction")
@Scope("prototype")
public class IuniSaleDailyStatAction extends BaseAction {
	private static final long serialVersionUID = -9059865076156626736L;

	private static final Logger logger = LoggerFactory.getLogger(IuniSaleDailyStatAction.class);
	private String beginDate;// 业务数据开始日期
	private String endDate;// 业务数据结束日期
	/**
	 * 销售数据列表
	 */
	private List<IuniSaleDailyStat> iuniSaleDailyStatList;
	/**
	 * 销售数据总计
	 */
	private IuniSaleDailyStat iuniSaleSum;
	
	/**
	 * 订单转化率列表
	 */
	private List<IuniDayOrderConvertRate> iuniDayOrderConvertRateList;
	/**
	 * 订单转化率总计
	 */
	private IuniDayOrderConvertRate iuniDayOrderConvertRateSum;
	
	/**
	 * 用户注册列表
	 */
	private List<IuniDayPvuvUserReg> iuniDayPvuvUserRegList;
	/**
	 * 用户注册总计
	 */
	private IuniDayPvuvUserReg iuniDayPvuvUserRegSum;

    /**
     * 商城订单转换率报表订单来源
     */
    private static final Map<String, String> orderSourceMapConv = OrderRefer.getOrderSourceMapForConv();

    private static String orderSourceIdConv = OrderRefer.IUNI.toString();

	@Autowired
	private IuniSaleDailyStatService iuniSaleDailyStatService;

	@Autowired
	private OrderSourceService orderSourceService;

	private List<OrderSource> orderSourceList = new ArrayList<OrderSource>();
	// 订单来源
	private String sourceCode = "IUNI";

    /**
	 * 分页查询销售数据
	 * 
	 * @return
	 */
	public String queryIuniSaleDailyStats() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("beginDate", StringUtils.defaultIfEmpty(beginDate, null));
		paramsMap.put("endDate", StringUtils.defaultIfEmpty(endDate, null));
		if(StringUtils.isNotBlank(sourceCode) && !"0".equals(sourceCode))
			paramsMap.put("orderSource", sourceCode);
		try {
			// 查询来源
			orderSourceList = orderSourceService.getAllOmOrderSource();
			// 查询总记录
			Integer totalRecord = iuniSaleDailyStatService.queryIuniSaleDailyStatsCount(paramsMap);
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
			iuniSaleDailyStatList = iuniSaleDailyStatService.queryIuniSaleDailyStats(paramsMap);
			// 查询总计
			iuniSaleSum=iuniSaleDailyStatService.queryIuniSaleDailyStatsSum(paramsMap);
		} catch (ServiceException se) {
			logger.error("queryIuniSaleDailyStats error:" + se.getMessage());
			return ERROR;
		} catch (Exception e) {
			logger.error("queryIuniSaleDailyStats error:" + e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 商城销售数据导出到excel
	 * 
	 * @return
	 */
	public void iuniSaleDailyStats2Excel() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		// 设置分页起始值
		paramsMap.put("startRec", 1);
		paramsMap.put("endRec", Integer.MAX_VALUE);
		paramsMap.put("beginDate", StringUtils.defaultIfEmpty(beginDate, null));
		paramsMap.put("endDate", StringUtils.defaultIfEmpty(endDate, null));
        paramsMap.put("orderSource", sourceCode);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// String fileName = "IUNI商城销售数据_" + sdf.format(new Date());
        String dateStr = "（" + paramsMap.get("beginDate") + "~" + paramsMap.get("endDate") + "）";
        String fileName = "IUNI商城销售数据_" + sourceCode + dateStr;
        try {
			fileName = new String(fileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.error("iuniSaleDailyStats2Excel UnsupportedEncodingException:" + e.getMessage());
		}
		int columnNum = 8;
		ExcelUtils exportExcelUtils = new ExcelUtils(response,fileName, columnNum);
		try {
			// 查询列表
			iuniSaleDailyStatList = iuniSaleDailyStatService.queryIuniSaleDailyStats(paramsMap);
			// 查询总计
			iuniSaleSum = iuniSaleDailyStatService.queryIuniSaleDailyStatsSum(paramsMap);
			iuniSaleSum.setBizDate("总计");
			iuniSaleDailyStatList.add(iuniSaleSum);
			exportExcelUtils.iuniSaleDailyStats2Excel(iuniSaleDailyStatList);
		} catch (Exception e) {
			logger.error("iuniSaleDailyStats2Excel error:" + e.getMessage());
		}
	}
	
	/**
	 * 分页查询订单转化率
	 * 
	 * @return
	 */
	public String queryIuniDayOrderConvertRates() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("beginDate", StringUtils.defaultIfEmpty(beginDate, null));
		paramsMap.put("endDate", StringUtils.defaultIfEmpty(endDate, null));
        paramsMap.put("orderSource", orderSourceIdConv);
		try {
			// 查询总记录
			Integer totalRecord = iuniSaleDailyStatService.queryIuniDayOrderConvertRatesCount(paramsMap);
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
			iuniDayOrderConvertRateList = iuniSaleDailyStatService.queryIuniDayOrderConvertRates(paramsMap);
			// 查询总计
			iuniDayOrderConvertRateSum=iuniSaleDailyStatService.queryIuniDayOrderConvertRatesSum(paramsMap);
		} catch (ServiceException se) {
			logger.error("queryIuniSaleDailyStats error:" + se.getMessage());
			return ERROR;
		} catch (Exception e) {
			logger.error("queryIuniSaleDailyStats error:" + e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 导出excel IUNI商城订单转化率
	 * 
	 * @return
	 */
	public void queryIuniDayOrderConvertRates2Excel() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		// 设置分页起始值
		paramsMap.put("startRec", 1);
		paramsMap.put("endRec", Integer.MAX_VALUE);
		paramsMap.put("beginDate", StringUtils.defaultIfEmpty(beginDate, null));
		paramsMap.put("endDate", StringUtils.defaultIfEmpty(endDate, null));
        paramsMap.put("orderSource", orderSourceIdConv);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = "IUNI商城订单转化率_" + sdf.format(new Date());
		try {
			fileName = new String(fileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.error("iuniSaleDailyStats2Excel UnsupportedEncodingException:" + e.getMessage());
		}
		int columnNum = 9;
		ExcelUtils exportExcelUtils = new ExcelUtils(response,fileName, columnNum);
		try {
			// 查询列表
			iuniDayOrderConvertRateList = iuniSaleDailyStatService.queryIuniDayOrderConvertRates(paramsMap);
			// 查询总计
			iuniDayOrderConvertRateSum=iuniSaleDailyStatService.queryIuniDayOrderConvertRatesSum(paramsMap);
			iuniDayOrderConvertRateSum.setBizDate("总计");
			iuniDayOrderConvertRateList.add(iuniDayOrderConvertRateSum);
			exportExcelUtils.iuniDayOrderConvertRates2Excel(iuniDayOrderConvertRateList);
		} catch (Exception e) {
			logger.error("queryIuniDayOrderConvertRates2Excel error:" + e.getMessage());
		}
	}
	
	/**
	 * 分页查询IUNI商城注册用户情况
	 * 
	 * @return
	 */
	public String queryIuniUserRegDailyStats() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("beginDate", StringUtils.defaultIfEmpty(beginDate, null));
		paramsMap.put("endDate", StringUtils.defaultIfEmpty(endDate, null));
		try {
			// 查询总记录
			Integer totalRecord = iuniSaleDailyStatService.queryIuniUserRegDailyStatsCount(paramsMap);
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
			iuniDayPvuvUserRegList = iuniSaleDailyStatService.queryIuniUserRegDailyStats(paramsMap);
			// 查询总计
			iuniDayPvuvUserRegSum=iuniSaleDailyStatService.queryIuniUserRegDailyStatsSum(paramsMap);
		} catch (ServiceException se) {
			logger.error("queryIuniSaleDailyStats error:" + se.getMessage());
			return ERROR;
		} catch (Exception e) {
			logger.error("queryIuniSaleDailyStats error:" + e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 导出excel用户注册数据
	 * 
	 * @return
	 */
	public void iuniUserRegDailyStats2Excel() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		// 设置分页起始值
		paramsMap.put("startRec", 1);
		paramsMap.put("endRec", Integer.MAX_VALUE);
		paramsMap.put("beginDate", StringUtils.defaultIfEmpty(beginDate, null));
		paramsMap.put("endDate", StringUtils.defaultIfEmpty(endDate, null));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = "IUNI商城用户注册_" + sdf.format(new Date());
		try {
			fileName = new String(fileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.error("iuniUserRegDailyStats2Excel UnsupportedEncodingException:" + e.getMessage());
		}
		int columnNum = 11;
		ExcelUtils exportExcelUtils = new ExcelUtils(response,fileName, columnNum);
		try {
			// 查询列表
			iuniDayPvuvUserRegList = iuniSaleDailyStatService.queryIuniUserRegDailyStats(paramsMap);
			// 查询总计
			iuniDayPvuvUserRegSum=iuniSaleDailyStatService.queryIuniUserRegDailyStatsSum(paramsMap);
			iuniDayPvuvUserRegSum.setBizDate("总计");
			iuniDayPvuvUserRegList.add(iuniDayPvuvUserRegSum);
			exportExcelUtils.iuniUserRegDailyStats2Excel(iuniDayPvuvUserRegList);
		} catch (Exception e) {
			logger.error("iuniUserRegDailyStats2Excel error:" + e.getMessage());
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

	public List<IuniSaleDailyStat> getIuniSaleDailyStatList() {
		return iuniSaleDailyStatList;
	}

	public void setIuniSaleDailyStatList(
			List<IuniSaleDailyStat> iuniSaleDailyStatList) {
		this.iuniSaleDailyStatList = iuniSaleDailyStatList;
	}

	public IuniSaleDailyStat getIuniSaleSum() {
		return iuniSaleSum;
	}

	public void setIuniSaleSum(IuniSaleDailyStat iuniSaleSum) {
		this.iuniSaleSum = iuniSaleSum;
	}

	public List<IuniDayOrderConvertRate> getIuniDayOrderConvertRateList() {
		return iuniDayOrderConvertRateList;
	}

	public void setIuniDayOrderConvertRateList(
			List<IuniDayOrderConvertRate> iuniDayOrderConvertRateList) {
		this.iuniDayOrderConvertRateList = iuniDayOrderConvertRateList;
	}

	public IuniDayOrderConvertRate getIuniDayOrderConvertRateSum() {
		return iuniDayOrderConvertRateSum;
	}

	public void setIuniDayOrderConvertRateSum(
			IuniDayOrderConvertRate iuniDayOrderConvertRateSum) {
		this.iuniDayOrderConvertRateSum = iuniDayOrderConvertRateSum;
	}

	public List<IuniDayPvuvUserReg> getIuniDayPvuvUserRegList() {
		return iuniDayPvuvUserRegList;
	}

	public void setIuniDayPvuvUserRegList(
			List<IuniDayPvuvUserReg> iuniDayPvuvUserRegList) {
		this.iuniDayPvuvUserRegList = iuniDayPvuvUserRegList;
	}

	public IuniDayPvuvUserReg getIuniDayPvuvUserRegSum() {
		return iuniDayPvuvUserRegSum;
	}

	public void setIuniDayPvuvUserRegSum(IuniDayPvuvUserReg iuniDayPvuvUserRegSum) {
		this.iuniDayPvuvUserRegSum = iuniDayPvuvUserRegSum;
	}

    public static String getOrderSourceIdConv() {
        return orderSourceIdConv;
    }

    public static void setOrderSourceIdConv(String orderSourceIdConv) {
        IuniSaleDailyStatAction.orderSourceIdConv = orderSourceIdConv;
    }

    public static Map<String, String> getOrderSourceMapConv() {
        return orderSourceMapConv;
    }

	public List<OrderSource> getOrderSourceList() {
		return orderSourceList;
	}

	public void setOrderSourceList(List<OrderSource> orderSourceList) {
		this.orderSourceList = orderSourceList;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
}
