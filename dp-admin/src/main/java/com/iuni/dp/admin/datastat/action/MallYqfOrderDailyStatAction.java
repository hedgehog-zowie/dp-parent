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
import com.iuni.dp.persist.datastat.model.MallYqfOrderDailyStat;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.ServiceException;
import com.iuni.dp.service.datastat.service.MallYqfOrderDailyStatService;

/**
 * 金立商城CPS销售数据统计
 * @author ZuoChangjun 2013-9-6
 * @version dp-admin-1.0.0
 */
@Controller("mallYqfOrderDailyStatAction")
@Scope("prototype")
public class MallYqfOrderDailyStatAction extends BaseAction {
	private static final long serialVersionUID = -9059865076156626736L;

	private static final Logger logger = LoggerFactory.getLogger(MallYqfOrderDailyStatAction.class);
	private String beginDate;// 注册开始日期
	private String endDate;// 注册结束日期
	private Long cid;// 广告活动ID
	private String source;// 数据来源:公司名
	private String channel;// 推广渠道：合作方式
	/**
	 * CPS推广销售数据列表
	 */
	private List<MallYqfOrderDailyStat> mallYqfOrderDailyStatList;
	/**
	 * CPS推广销售数据总计
	 */
	private MallYqfOrderDailyStat mallYqfOrderSum;
	
	@Autowired
	private MallYqfOrderDailyStatService mallYqfOrderDailyStatService;
	/**
	 * 分页查询CPS推广订单数据
	 * 
	 * @return
	 */
	public String queryMallYqfOrderDailyStats() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("beginDate", StringUtils.defaultIfEmpty(beginDate, null));
		paramsMap.put("endDate", StringUtils.defaultIfEmpty(endDate, null));
		paramsMap.put("cid", cid);
		paramsMap.put("source", StringUtils.defaultIfEmpty(source, null));
		paramsMap.put("channel", StringUtils.defaultIfEmpty(channel, null));
		try {
			// 查询总记录
			Integer totalRecord = mallYqfOrderDailyStatService.queryMallYqfOrderDailyStatsCount(paramsMap);
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
			mallYqfOrderDailyStatList = mallYqfOrderDailyStatService.queryMallYqfOrderDailyStats(paramsMap);
			// 查询总计
			mallYqfOrderSum=mallYqfOrderDailyStatService.queryMallYqfOrderDailyStatsSum(paramsMap);
		} catch (ServiceException se) {
			logger.error("queryMallYqfOrderDailyStats error:" + se.getMessage());
			return ERROR;
		} catch (Exception e) {
			logger.error("queryMallYqfOrderDailyStats error:" + e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 导出excel金立商城CPS推广销售数据
	 * 
	 * @return
	 */
	public void mallYqfOrderDailyStats2Excel() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		// 设置分页起始值
		paramsMap.put("startRec", 1);
		paramsMap.put("endRec", Integer.MAX_VALUE);
		paramsMap.put("beginDate", StringUtils.defaultIfEmpty(beginDate, null));
		paramsMap.put("endDate", StringUtils.defaultIfEmpty(endDate, null));
		paramsMap.put("cid", cid);
		paramsMap.put("source", StringUtils.defaultIfEmpty(source, null));
		paramsMap.put("channel", StringUtils.defaultIfEmpty(channel, null));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = "金立商城CPS推广_" + sdf.format(new Date());
		try {
			fileName = new String(fileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.error("mallYqfOrderDailyStats2Excel UnsupportedEncodingException:" + e.getMessage());
		}
		int columnNum = 8;
		ExcelUtils exportExcelUtils = new ExcelUtils(response,fileName, columnNum);
		try {
			// 查询列表
			mallYqfOrderDailyStatList = mallYqfOrderDailyStatService.queryMallYqfOrderDailyStats(paramsMap);
			// 查询总计
			mallYqfOrderSum = mallYqfOrderDailyStatService.queryMallYqfOrderDailyStatsSum(paramsMap);
			mallYqfOrderSum.setBizDate("总计");
			mallYqfOrderDailyStatList.add(mallYqfOrderSum);
			exportExcelUtils.mallYqfOrderDailyStats2Excel(mallYqfOrderDailyStatList);
		} catch (Exception e) {
			logger.error("mallYqfOrderDailyStats2Excel error:" + e.getMessage());
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

	public List<MallYqfOrderDailyStat> getMallYqfOrderDailyStatList() {
		return mallYqfOrderDailyStatList;
	}

	public void setMallYqfOrderDailyStatList(
			List<MallYqfOrderDailyStat> mallYqfOrderDailyStatList) {
		this.mallYqfOrderDailyStatList = mallYqfOrderDailyStatList;
	}

	public MallYqfOrderDailyStat getMallYqfOrderSum() {
		return mallYqfOrderSum;
	}

	public void setMallYqfOrderSum(MallYqfOrderDailyStat mallYqfOrderSum) {
		this.mallYqfOrderSum = mallYqfOrderSum;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

}
