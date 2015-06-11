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
import com.iuni.dp.persist.datastat.model.MallAdGoodsOrderDetail;
import com.iuni.dp.persist.datastat.model.MallAdPvuvOrderDaily;
import com.iuni.dp.persist.datastat.model.MallAdPvuvOrderTotal;
import com.iuni.dp.service.common.bean.Page;
import com.iuni.dp.service.common.exception.ServiceException;
import com.iuni.dp.service.datastat.service.MallAdChannelStatService;

/**
 * 广告推广渠道PV UV OrderNum统计(站外推广数据报表)
 * 
 * @author ZuoChangjun 2013-7-10
 * @version dp-admin-1.0.0
 */
@Controller("mallAdChannelStatAction")
@Scope("prototype")
public class MallAdChannelStatAction extends BaseAction {
	private static final long serialVersionUID = -9059865076156626736L;

	private static final Logger logger = LoggerFactory.getLogger(MallAdChannelStatAction.class);

	private Long adId;// 广告ID
	private String adName;// 推广名
	private String channelName;// 推广站点名
	private String bizDate;// 业务数据所属日期
	private String beginDate;// 业务数据开始日期
	private String endDate;// 业务数据结束日期
	private int detailType;// 明细类型，1:总表明细;2：日表明细;3:推广表明细
	/**
	 * 站外推广数据(总表)
	 */
	private List<MallAdPvuvOrderTotal> mallAdPvuvOrderTotalList;
	/**
	 * 站外推广数据(日表/推广数据表)
	 */
	private List<MallAdPvuvOrderDaily> mallAdPvuvOrderDailyList;
	/**
	 * 站外推广数据(总表/日表/推广数据表:明细表)
	 */
	private List<MallAdGoodsOrderDetail> mallAdGoodsOrderDetailList;

	@Autowired
	private MallAdChannelStatService mallAdChannelStatService;

	/**
	 * 站外推广数据(总表)
	 * 
	 * @return
	 */
	public String queryMallAdPvuvOrderTotals() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("adId", adId);
		paramsMap.put("adName", StringUtils.defaultIfEmpty(adName, null));
		paramsMap.put("channelName",StringUtils.defaultIfEmpty(channelName, null));
		try {
			// 查询总记录
			Integer totalRecord = mallAdChannelStatService.queryMallAdPvuvOrderTotalsCount(paramsMap);
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
			mallAdPvuvOrderTotalList = mallAdChannelStatService.queryMallAdPvuvOrderTotals(paramsMap);
		} catch (ServiceException se) {
			logger.error("queryMallAdPvuvOrderTotals error:" + se.getMessage());
			return ERROR;
		} catch (Exception e) {
			logger.error("queryMallAdPvuvOrderTotals error:" + e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 站外推广数据(日表)
	 * 
	 * @return
	 */
	public String queryMallAdPvuvOrderDailys() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("beginDate", StringUtils.defaultIfEmpty(beginDate, null));
		paramsMap.put("endDate", StringUtils.defaultIfEmpty(endDate, null));
		paramsMap.put("adId", adId);
		paramsMap.put("adName", StringUtils.defaultIfEmpty(adName, null));
		paramsMap.put("channelName",StringUtils.defaultIfEmpty(channelName, null));
		try {
			// 查询总记录
			Integer totalRecord = mallAdChannelStatService.queryMallAdPvuvOrderDailysCount(paramsMap);
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
			mallAdPvuvOrderDailyList = mallAdChannelStatService.queryMallAdPvuvOrderDailys(paramsMap);
		} catch (ServiceException se) {
			logger.error("queryMallAdPvuvOrderTotals error:" + se.getMessage());
			return ERROR;
		} catch (Exception e) {
			logger.error("queryMallAdPvuvOrderTotals error:" + e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 站外推广数据(推广数据表)
	 * 
	 * @return
	 */
	public String queryMallAdPvuvOrderDailysForFinal() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("beginDate", StringUtils.defaultIfEmpty(beginDate, null));
		paramsMap.put("endDate", StringUtils.defaultIfEmpty(endDate, null));
		paramsMap.put("adId", adId);
		paramsMap.put("adName", StringUtils.defaultIfEmpty(adName, null));
		paramsMap.put("channelName",StringUtils.defaultIfEmpty(channelName, null));
		try {
			// 查询总记录
			Integer totalRecord = mallAdChannelStatService.queryMallAdPvuvOrderDailysForFinalCount(paramsMap);
			if (totalRecord == null || totalRecord.intValue() == 0) {
				return SUCCESS;
			}
			// 根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord,page.getPageSize());
			// 设置分页起始值
			paramsMap.put("startRec", page.getStartRec());
			paramsMap.put("endRec", page.getEndRec());
			// 查询列表
			mallAdPvuvOrderDailyList = mallAdChannelStatService.queryMallAdPvuvOrderDailysForFinal(paramsMap);
		} catch (ServiceException se) {
			logger.error("queryMallAdPvuvOrderTotals error:" + se.getMessage());
			return ERROR;
		} catch (Exception e) {
			logger.error("queryMallAdPvuvOrderTotals error:" + e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 站外推广数据(总表:明细表)
	 * 
	 * @return
	 */
	@Deprecated
	public String queryMallAdPvuvOrderDetailsForTotal() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("adId", adId);
		paramsMap.put("channelName",StringUtils.defaultIfEmpty(channelName, null));
		try {
			mallAdGoodsOrderDetailList = mallAdChannelStatService.queryMallAdPvuvOrderDetailsForTotal(paramsMap);
		} catch (ServiceException se) {
			logger.error("queryMallAdPvuvOrderDetailsForTotal error:" + se.getMessage());
			return ERROR;
		} catch (Exception e) {
			logger.error("queryMallAdPvuvOrderDetailsForTotal error:" + e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 站外推广数据(日表/推广数据表:明细表)
	 * 
	 * @return
	 */
	@Deprecated
	public String queryMallAdPvuvOrderDetailsForDaily() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("adId", adId);
		// paramsMap.put("adName", StringUtils.defaultIfEmpty(adName, null));
		paramsMap.put("channelName",StringUtils.defaultIfEmpty(channelName, null));
		paramsMap.put("bizDate", StringUtils.defaultIfEmpty(bizDate, null));
		try {
			mallAdGoodsOrderDetailList = mallAdChannelStatService.queryMallAdPvuvOrderDetailsForDaily(paramsMap);
		} catch (ServiceException se) {
			logger.error("queryMallAdPvuvOrderDetailsForDaily error:" + se.getMessage());
			return ERROR;
		} catch (Exception e) {
			logger.error("queryMallAdPvuvOrderDetailsForDaily error:" + e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 站外推广数据(总表/日表/推广表:明细表)
	 * 
	 * @return
	 */
	public String queryMallAdPvuvOrderDetails() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("adId", adId);
		// paramsMap.put("adName", StringUtils.defaultIfEmpty(adName, null));
		paramsMap.put("channelName",StringUtils.defaultIfEmpty(channelName, null));
		paramsMap.put("bizDate", StringUtils.defaultIfEmpty(bizDate, null));
		try {
			// 查询总记录
			Integer totalRecord = mallAdChannelStatService.queryMallAdPvuvOrderDetailsCount(paramsMap);
			if (totalRecord == null || totalRecord.intValue() == 0) {
				return SUCCESS;
			}
			// 根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord,page.getPageSize());
			// 设置分页起始值
			paramsMap.put("startRec", page.getStartRec());
			paramsMap.put("endRec", page.getEndRec());
			// 查询列表
			mallAdGoodsOrderDetailList = mallAdChannelStatService.queryMallAdPvuvOrderDetails(paramsMap);
		} catch (ServiceException se) {
			logger.error("queryMallAdPvuvOrderDetails error:" + se.getMessage());
			return ERROR;
		} catch (Exception e) {
			logger.error("queryMallAdPvuvOrderDetails error:" + e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 站外推广数据(推广数据表:明细表)
	 * 
	 * @return
	 */
	@Deprecated
	public String queryMallAdPvuvOrderDetailsForFinal() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("adId", adId);
		// paramsMap.put("adName", StringUtils.defaultIfEmpty(adName, null));
		paramsMap.put("channelName",StringUtils.defaultIfEmpty(channelName, null));
		paramsMap.put("bizDate", StringUtils.defaultIfEmpty(bizDate, null));
		try {
			// 查询总记录
			Integer totalRecord = mallAdChannelStatService.queryMallAdPvuvOrderDetailsForFinalCount(paramsMap);
			if (totalRecord == null || totalRecord.intValue() == 0) {
				return SUCCESS;
			}
			// 根据当前页、总记录数、页大小获得Page
			page = Page.genPage(page.getCurrentPage(), totalRecord,page.getPageSize());
			// 设置分页起始值
			paramsMap.put("startRec", page.getStartRec());
			paramsMap.put("endRec", page.getEndRec());
			// 查询列表
			mallAdGoodsOrderDetailList = mallAdChannelStatService.queryMallAdPvuvOrderDetailsForFinal(paramsMap);
		} catch (ServiceException se) {
			logger.error("queryMallAdPvuvOrderDetails error:" + se.getMessage());
			return ERROR;
		} catch (Exception e) {
			logger.error("queryMallAdPvuvOrderDetails error:" + e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 导出excel站外推广数据(总表)
	 * 
	 * @return
	 */
	public void mallAdPvuvOrderTotals2Excel() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("adId", adId);
		paramsMap.put("adName", StringUtils.defaultIfEmpty(adName, null));
		paramsMap.put("channelName",StringUtils.defaultIfEmpty(channelName, null));
		// 设置分页起始值
		paramsMap.put("startRec", 1);
		paramsMap.put("endRec", Integer.MAX_VALUE);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = "站外推广数据(总表)_" + sdf.format(new Date());
		try {
			fileName = new String(fileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.error("mallAdPvuvOrderTotals2Excel UnsupportedEncodingException:" + e.getMessage());
		}
		int columnNum = 8;
		ExcelUtils exportExcelUtils = new ExcelUtils(response,fileName, columnNum);
		try {
			// 查询列表
			mallAdPvuvOrderTotalList = mallAdChannelStatService.queryMallAdPvuvOrderTotals(paramsMap);
			exportExcelUtils.mallAdPvuvOrderTotal2Excel(mallAdPvuvOrderTotalList);
		} catch (Exception e) {
			logger.error("mallAdPvuvOrderTotals2Excel error:" + e.getMessage());
		}
	}

	/**
	 * 导出excel站外推广数据(日表)
	 * 
	 * @return
	 */
	public void mallAdPvuvOrderDailys2Excel() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		// 设置分页起始值
		paramsMap.put("startRec", 1);
		paramsMap.put("endRec", Integer.MAX_VALUE);
		paramsMap.put("beginDate", StringUtils.defaultIfEmpty(beginDate, null));
		paramsMap.put("endDate", StringUtils.defaultIfEmpty(endDate, null));
		paramsMap.put("adId", adId);
		paramsMap.put("adName", StringUtils.defaultIfEmpty(adName, null));
		paramsMap.put("channelName",StringUtils.defaultIfEmpty(channelName, null));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = "站外推广数据(日表)_" + sdf.format(new Date());
		try {
			fileName = new String(fileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.error("mallAdPvuvOrderDailys2Excel UnsupportedEncodingException:" + e.getMessage());
		}
		int columnNum = 8;
		ExcelUtils excelUtils = new ExcelUtils(response,fileName, columnNum);
		try {
			// 查询列表
			mallAdPvuvOrderDailyList = mallAdChannelStatService.queryMallAdPvuvOrderDailys(paramsMap);
			excelUtils.mallAdPvuvOrderDaily2Excel(mallAdPvuvOrderDailyList);
		} catch (Exception e) {
			logger.error("mallAdPvuvOrderDailys2Excel error:" + e.getMessage());
		}
	}
	
	/**
	 * 导出excel站外推广数据(推广数据表)
	 * 
	 * @return
	 */
	public void mallAdPvuvOrderDailysForFinal2Excel() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		// 设置分页起始值
		paramsMap.put("startRec", 1);
		paramsMap.put("endRec", Integer.MAX_VALUE);
		paramsMap.put("beginDate", StringUtils.defaultIfEmpty(beginDate, null));
		paramsMap.put("endDate", StringUtils.defaultIfEmpty(endDate, null));
		paramsMap.put("adId", adId);
		paramsMap.put("adName", StringUtils.defaultIfEmpty(adName, null));
		paramsMap.put("channelName",StringUtils.defaultIfEmpty(channelName, null));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = "站外推广数据(推广数据表)_" + sdf.format(new Date());
		try {
			fileName = new String(fileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.error("mallAdPvuvOrderDailysForFinal2Excel UnsupportedEncodingException:" + e.getMessage());
		}
		int columnNum = 8;
		ExcelUtils exportExcelUtils = new ExcelUtils(response,fileName, columnNum);
		try {
			// 查询列表
			mallAdPvuvOrderDailyList = mallAdChannelStatService.queryMallAdPvuvOrderDailysForFinal(paramsMap);
			exportExcelUtils.mallAdPvuvOrderDailysForFinal2Excel(mallAdPvuvOrderDailyList);
		} catch (Exception e) {
			logger.error("mallAdPvuvOrderDailysForFinal2Excel error:" + e.getMessage());
		}
	}
	/**
	 * 导出excel站外推广数据(总表：明细表)
	 * 
	 * @return
	 */
	public void mallAdPvuvOrderDetailsForTotal2Excel() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("adId", adId);
		paramsMap.put("channelName",StringUtils.defaultIfEmpty(channelName, null));
		// 设置分页起始值
		paramsMap.put("startRec", 1);
		paramsMap.put("endRec", Integer.MAX_VALUE);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = "站外推广数据(总表之明细表)_" + sdf.format(new Date());
		try {
			fileName = new String(fileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.error("mallAdPvuvOrderDetailsForTotal2Excel UnsupportedEncodingException:" + e.getMessage());
		}
		int columnNum = 8;
		ExcelUtils exportExcelUtils = new ExcelUtils(response,fileName, columnNum);
		try {
			// 查询列表
			mallAdGoodsOrderDetailList = mallAdChannelStatService.queryMallAdPvuvOrderDetails(paramsMap);
			exportExcelUtils.mallAdPvuvOrderDetails2Excel(mallAdGoodsOrderDetailList);
		} catch (Exception e) {
			logger.error("mallAdPvuvOrderDetailsForTotal2Excel error:" + e.getMessage());
		}
	}
	
	/**
	 * 导出excel站外推广数据(日表/推广数据表：明细表)
	 * 
	 * @return
	 */
	public void mallAdPvuvOrderDetailsForDaily2Excel() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("bizDate", StringUtils.defaultIfEmpty(bizDate, null));
		paramsMap.put("adId", adId);
		paramsMap.put("channelName",StringUtils.defaultIfEmpty(channelName, null));
		// 设置分页起始值
		paramsMap.put("startRec", 1);
		paramsMap.put("endRec", Integer.MAX_VALUE);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = "站外推广数据(日表之明细表)_" + sdf.format(new Date());
		if(detailType == 2){
			fileName = "站外推广数据(日表之明细表)_" + sdf.format(new Date());
		}		
		else if(detailType == 3){
			fileName = "站外推广数据(推广数据表之明细表)_" + sdf.format(new Date());
		}
		try {
			fileName = new String(fileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.error("mallAdPvuvOrderDetailsForDaily2Excel UnsupportedEncodingException:" + e.getMessage());
		}
		int columnNum = 8;
		ExcelUtils exportExcelUtils = new ExcelUtils(response,fileName, columnNum);
		try {
			// 查询列表
			mallAdGoodsOrderDetailList = mallAdChannelStatService.queryMallAdPvuvOrderDetails(paramsMap);
			exportExcelUtils.mallAdPvuvOrderDetails2Excel(mallAdGoodsOrderDetailList);
		} catch (Exception e) {
			logger.error("mallAdPvuvOrderDetailsForDaily2Excel error:" + e.getMessage());
		}
	}
	
	/**
	 * 导出excel站外推广数据(推广数据表：明细表)
	 * 
	 * @return
	 */
	@Deprecated
	public void mallAdPvuvOrderDetailsForFinal2Excel() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("bizDate", StringUtils.defaultIfEmpty(bizDate, null));
		paramsMap.put("adId", adId);
		paramsMap.put("channelName",StringUtils.defaultIfEmpty(channelName, null));
		// 设置分页起始值
		paramsMap.put("startRec", 1);
		paramsMap.put("endRec", Integer.MAX_VALUE);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = "站外推广数据(推广数据表之明细表)_" + sdf.format(new Date());
/*		if(detailType == 2){
			fileName = "站外推广数据(日表之明细表)_" + sdf.format(new Date());
		}		
		else if(detailType == 3){
			fileName = "站外推广数据(推广数据表之明细表)_" + sdf.format(new Date());
		}*/
		try {
			fileName = new String(fileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.error("mallAdPvuvOrderDetailsForFinal2Excel UnsupportedEncodingException:" + e.getMessage());
		}
		int columnNum = 8;
		ExcelUtils exportExcelUtils = new ExcelUtils(response,fileName, columnNum);
		try {
			// 查询列表
			mallAdGoodsOrderDetailList = mallAdChannelStatService.queryMallAdPvuvOrderDetailsForFinal(paramsMap);
			exportExcelUtils.mallAdPvuvOrderDetails2Excel(mallAdGoodsOrderDetailList);
		} catch (Exception e) {
			logger.error("mallAdPvuvOrderDetailsForFinal2Excel error:" + e.getMessage());
		}
	}
	// ===================================get/set===============================

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) throws UnsupportedEncodingException {
		if(StringUtils.isBlank(adName)){
			this.adName = null;
		}else{
			this.adName = new String(adName.getBytes("iso-8859-1"),"UTF-8");
		}
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) throws UnsupportedEncodingException {
		if(StringUtils.isBlank(channelName)){
			this.channelName = null;
		}else{
			this.channelName = new String(channelName.getBytes("iso-8859-1"),"UTF-8");
		}
	}

	public String getBizDate() {
		return bizDate;
	}

	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}

	public int getDetailType() {
		return detailType;
	}

	public void setDetailType(int detailType) {
		this.detailType = detailType;
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

	public List<MallAdPvuvOrderTotal> getMallAdPvuvOrderTotalList() {
		return mallAdPvuvOrderTotalList;
	}

	public void setMallAdPvuvOrderTotalList(
			List<MallAdPvuvOrderTotal> mallAdPvuvOrderTotalList) {
		this.mallAdPvuvOrderTotalList = mallAdPvuvOrderTotalList;
	}

	public List<MallAdPvuvOrderDaily> getMallAdPvuvOrderDailyList() {
		return mallAdPvuvOrderDailyList;
	}

	public void setMallAdPvuvOrderDailyList(
			List<MallAdPvuvOrderDaily> mallAdPvuvOrderDailyList) {
		this.mallAdPvuvOrderDailyList = mallAdPvuvOrderDailyList;
	}

	public List<MallAdGoodsOrderDetail> getMallAdGoodsOrderDetailList() {
		return mallAdGoodsOrderDetailList;
	}

	public void setMallAdGoodsOrderDetailList(
			List<MallAdGoodsOrderDetail> mallAdGoodsOrderDetailList) {
		this.mallAdGoodsOrderDetailList = mallAdGoodsOrderDetailList;
	}

}
