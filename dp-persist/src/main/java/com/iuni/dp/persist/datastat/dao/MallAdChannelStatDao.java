package com.iuni.dp.persist.datastat.dao;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.MallAdGoodsOrderDetail;
import com.iuni.dp.persist.datastat.model.MallAdPvuvOrderDaily;
import com.iuni.dp.persist.datastat.model.MallAdPvuvOrderTotal;

/**
 * 广告推广渠道PV UV OrderNum统计(站外推广数据报表)
 * @author ZuoChangjun 2013-7-10
 * @version dp-persist-1.0.0
 */
public interface MallAdChannelStatDao {
	/**
	 * 站外推广数据(总表)
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallAdPvuvOrderTotal> queryMallAdPvuvOrderTotals(Map<String, Object> paramsMap);
	
	/**
	 * 站外推广数据(总表):查询总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryMallAdPvuvOrderTotalsCount(Map<String, Object> paramsMap);

	/**
	 * 站外推广数据(日表)
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallAdPvuvOrderDaily> queryMallAdPvuvOrderDailys(Map<String, Object> paramsMap);
	
	/**
	 * 站外推广数据(日表):查询总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryMallAdPvuvOrderDailysCount(Map<String, Object> paramsMap);
	
	/**
	 * 站外推广数据(推广数据表:outDate(出表日期))
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallAdPvuvOrderDaily> queryMallAdPvuvOrderDailysForFinal(Map<String, Object> paramsMap);
	
	/**
	 * 站外推广数据(推广数据表:outDate(出表日期)):查询总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryMallAdPvuvOrderDailysForFinalCount(Map<String, Object> paramsMap);
	
	/**
	 * 站外推广数据(总表:明细表)
	 * 
	 * @author ZuoChangjun
	 */
	@Deprecated
	public List<MallAdGoodsOrderDetail> queryMallAdPvuvOrderDetailsForTotal(Map<String, Object> paramsMap);
	
	/**
	 * 站外推广数据(日表/推广数据:明细表)
	 * 
	 * @author ZuoChangjun
	 */
	@Deprecated
	public List<MallAdGoodsOrderDetail> queryMallAdPvuvOrderDetailsForDaily(Map<String, Object> paramsMap);
	
	/**
	 * 站外推广数据(总表/日表/推广数据:明细表)
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallAdGoodsOrderDetail> queryMallAdPvuvOrderDetails(Map<String, Object> paramsMap);
	
	/**
	 * 站外推广数据(总表/日表/推广数据:明细表):查询总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryMallAdPvuvOrderDetailsCount(Map<String, Object> paramsMap);
	
	/**
	 * 站外推广数据(总表/日表:明细表)
	 * 
	 * @author ZuoChangjun
	 */
	@Deprecated
	public List<MallAdGoodsOrderDetail> queryMallAdPvuvOrderDetailsForFinal(Map<String, Object> paramsMap);
	
	/**
	 * 站外推广数据(推广数据:明细表):查询总记录数
	 * 
	 * @author ZuoChangjun
	 */
	@Deprecated
	public Integer queryMallAdPvuvOrderDetailsForFinalCount(Map<String, Object> paramsMap);
}
