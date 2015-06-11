package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.dao.MallAdChannelStatDao;
import com.iuni.dp.persist.datastat.model.MallAdGoodsOrderDetail;
import com.iuni.dp.persist.datastat.model.MallAdPvuvOrderDaily;
import com.iuni.dp.persist.datastat.model.MallAdPvuvOrderTotal;
import com.iuni.dp.service.common.exception.ServiceException;
import com.iuni.dp.service.datastat.service.MallAdChannelStatService;

/**
 * 广告推广渠道PV UV OrderNum统计(站外推广数据报表)
 * @author ZuoChangjun 2013-7-10
 * @version dp-service-1.0.0
 */
@Service("mallAdChannelStatService")
public class MallAdChannelStatServiceImpl implements MallAdChannelStatService {
	private final static Logger logger = LoggerFactory.getLogger(MallAdChannelStatServiceImpl.class);
	
	@Autowired
	private MallAdChannelStatDao mallAdChannelStatDao;
	/**
	 * 站外推广数据(总表)
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallAdPvuvOrderTotal> queryMallAdPvuvOrderTotals(Map<String, Object> paramsMap) throws ServiceException{
		return mallAdChannelStatDao.queryMallAdPvuvOrderTotals(paramsMap);
	}
	
	/**
	 * 站外推广数据(总表):查询总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryMallAdPvuvOrderTotalsCount(Map<String, Object> paramsMap) throws ServiceException{
		return mallAdChannelStatDao.queryMallAdPvuvOrderTotalsCount(paramsMap);
	}

	/**
	 * 站外推广数据(日表)
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallAdPvuvOrderDaily> queryMallAdPvuvOrderDailys(Map<String, Object> paramsMap) throws ServiceException{
		return mallAdChannelStatDao.queryMallAdPvuvOrderDailys(paramsMap);
	}
	
	/**
	 * 站外推广数据(日表):查询总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryMallAdPvuvOrderDailysCount(Map<String, Object> paramsMap) throws ServiceException{
		return mallAdChannelStatDao.queryMallAdPvuvOrderDailysCount(paramsMap);
	}
	/**
	 * 站外推广数据(推广数据表:outDate(出表日期))
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallAdPvuvOrderDaily> queryMallAdPvuvOrderDailysForFinal(Map<String, Object> paramsMap) throws ServiceException{
		return mallAdChannelStatDao.queryMallAdPvuvOrderDailysForFinal(paramsMap);
	}
	
	/**
	 * 站外推广数据(推广数据表:outDate(出表日期)):查询总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryMallAdPvuvOrderDailysForFinalCount(Map<String, Object> paramsMap) throws ServiceException{
		return mallAdChannelStatDao.queryMallAdPvuvOrderDailysForFinalCount(paramsMap);
	}
	/**
	 * 站外推广数据(总表:明细表)
	 * 
	 * @author ZuoChangjun
	 */
	@Deprecated
	public List<MallAdGoodsOrderDetail> queryMallAdPvuvOrderDetailsForTotal(Map<String, Object> paramsMap) throws ServiceException{
		Long adId=(Long)paramsMap.get("adId");
		String channelName=(String)paramsMap.get("channelName");
		if(adId==null){
			throw new ServiceException("广告ID不能为空.");
		}
		if(StringUtils.isBlank(channelName)){
			throw new ServiceException("广告推广渠道名不能为空.");
		}
		return mallAdChannelStatDao.queryMallAdPvuvOrderDetailsForTotal(paramsMap);
	}
	
	/**
	 * 站外推广数据(日表/推广数据:明细表)
	 * 
	 * @author ZuoChangjun
	 */
	@Deprecated
	public List<MallAdGoodsOrderDetail> queryMallAdPvuvOrderDetailsForDaily(Map<String, Object> paramsMap) throws ServiceException{
		Long adId=(Long)paramsMap.get("adId");
		String channelName=(String)paramsMap.get("channelName");
		String bizDate=(String)paramsMap.get("bizDate");
		if(adId==null){
			throw new ServiceException("广告ID不能为空.");
		}
		if(StringUtils.isBlank(channelName)){
			throw new ServiceException("广告推广渠道名不能为空.");
		}
		if(StringUtils.isBlank(bizDate)){
			throw new ServiceException("业务日期不能为空.");
		}
		return mallAdChannelStatDao.queryMallAdPvuvOrderDetailsForDaily(paramsMap);
	}
	
	/**
	 * 站外推广数据(总表/日表/推广数据:明细表)
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallAdGoodsOrderDetail> queryMallAdPvuvOrderDetails(Map<String, Object> paramsMap)throws ServiceException{
		Long adId=(Long)paramsMap.get("adId");
		String channelName=(String)paramsMap.get("channelName");
		if(adId==null){
			throw new ServiceException("广告ID不能为空.");
		}
		if(StringUtils.isBlank(channelName)){
			throw new ServiceException("广告推广渠道名不能为空.");
		}
		return mallAdChannelStatDao.queryMallAdPvuvOrderDetails(paramsMap);
	}
	
	/**
	 * 站外推广数据(总表/日表/推广数据:明细表):查询总记录数
	 * 
	 * @author ZuoChangjun
	 */
	public Integer queryMallAdPvuvOrderDetailsCount(Map<String, Object> paramsMap)throws ServiceException{
		Long adId=(Long)paramsMap.get("adId");
		String channelName=(String)paramsMap.get("channelName");
		if(adId==null){
			throw new ServiceException("广告ID不能为空.");
		}
		if(StringUtils.isBlank(channelName)){
			throw new ServiceException("广告推广渠道名不能为空.");
		}
		return mallAdChannelStatDao.queryMallAdPvuvOrderDetailsCount(paramsMap);
	}
	
	/**
	 * 站外推广数据(推广数据:明细表)
	 * 
	 * @author ZuoChangjun
	 */
	@Deprecated
	public List<MallAdGoodsOrderDetail> queryMallAdPvuvOrderDetailsForFinal(Map<String, Object> paramsMap)throws ServiceException{
		Long adId=(Long)paramsMap.get("adId");
		String channelName=(String)paramsMap.get("channelName");
		if(adId==null){
			throw new ServiceException("广告ID不能为空.");
		}
		if(StringUtils.isBlank(channelName)){
			throw new ServiceException("广告推广渠道名不能为空.");
		}
		return mallAdChannelStatDao.queryMallAdPvuvOrderDetailsForFinal(paramsMap);
	}
	
	/**
	 * 站外推广数据(推广数据:明细表):查询总记录数
	 * 
	 * @author ZuoChangjun
	 */
	@Deprecated
	public Integer queryMallAdPvuvOrderDetailsForFinalCount(Map<String, Object> paramsMap)throws ServiceException{

		Long adId=(Long)paramsMap.get("adId");
		String channelName=(String)paramsMap.get("channelName");
		if(adId==null){
			throw new ServiceException("广告ID不能为空.");
		}
		if(StringUtils.isBlank(channelName)){
			throw new ServiceException("广告推广渠道名不能为空.");
		}
		return mallAdChannelStatDao.queryMallAdPvuvOrderDetailsForFinalCount(paramsMap);
	
	}
}
