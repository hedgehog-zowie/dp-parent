package com.iuni.dp.service.datareport.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iuni.dp.persist.common.utils.ParseProperties;
import com.iuni.dp.service.datareport.constants.ReportDataConstant;
import com.iuni.dp.service.datareport.constants.ReportDataType;
import com.iuni.dp.service.datareport.utils.Data2FileUtil;

/**
 * 上报数据相应队列对应本地缓存池
 * @param <T>
 * @author CaiKe
 * @version V1.0.0
 */
public class LocalCachePool<T> {

	private static Logger logger = LoggerFactory.getLogger(LocalCachePool.class);
	
	private static final Long cacheExpireTime = ParseProperties.getLongVal(ReportDataConstant.rptData_cache_expireTime);
	private static final String cacheDataList = "cacheDataList";
	
	private Integer fileMaxLineLimit;

	private Integer queueNum;
	
	private Map<String, List<T>> cacheMap;
	
	private Map<String, Long> expireMap;
	
	private byte[] lock = new byte[0];
	
	public LocalCachePool(Integer fileMaxLineLimit, Integer queueNum) {
		this.fileMaxLineLimit = fileMaxLineLimit;
		this.queueNum = queueNum;
		init();
	}
	
	/**
	 * 初始化缓存池,及过期时间缓存
	 */
	private void init() {
		long stime = System.currentTimeMillis();
		cacheMap = new HashMap<String, List<T>>(queueNum);
		expireMap = new HashMap<String, Long>(queueNum);
		for (int i = 0; i < queueNum; i++) {
			cacheMap.put(cacheDataList + i, new ArrayList<T>(fileMaxLineLimit));
			expireMap.put(cacheDataList + i, 0L);
		}
		logger.info("LocalCachePool.init() success: queueNumber={}, fileMaxLineLimit={}, costTime={}ms"
				, new Object[] { queueNum, fileMaxLineLimit, System.currentTimeMillis() - stime });
	}
	
	/**
	 * ReportData类型上报数据相应队列数据写入缓存并处理
	 * @param dataList
	 * @param index
	 * @param dataType
	 */
	public void setRptData2CacheFromQueue(List<T> dataList, Integer index, ReportDataType rptDataType) {
		synchronized(lock) {
			writeDataProcessor(dataList, index, rptDataType);
		}
	}
	
	/**
	 * 缓存数据处理
	 * 针对数据类型为String的List<T>进行处理
	 * @param dataList
	 * @param index
	 * @param dataType
	 */
	private void writeDataProcessor(List<T> dataList, Integer index, ReportDataType rptDataType) {
		if(CollectionUtils.isNotEmpty(dataList)) {
			
			// 写入数据Size
			int dataSize = dataList.size();
			// 当前CacheSize
			List<T> cacheList = getCacheDataList(index);
			int cacheSize = cacheList.size();
			
			// 数据序列小于文件最大记录数
			if(dataSize < fileMaxLineLimit) {
				//  缓存 cacheDataList_i当前可用容量
				int availableCapacity = (fileMaxLineLimit - cacheSize);
				if(dataList.size() < availableCapacity) {
					cacheList.addAll(dataList);
					// 更新缓存过期时间
					setCacheExpireTime(index);
					
					logger.debug("LocalCachePool.writeDataProcessor write into cache success" + "Cache index: " + index);
				}
				else if(dataList.size() == availableCapacity) {
					cacheList.addAll(dataList);
					
					Data2FileUtil.fileProcessForBean(cacheList, index, rptDataType);
					cacheList.clear();
					// 初始化缓存过期时间
					initCacheExpireTime(index);
					
					logger.debug("LocalCachePool.writeDataProcessor write into file success" + "Queue index: " + index);
				}
				else {
					List<T> preList = new ArrayList<T>(dataList.subList(0, availableCapacity));
					List<T> afterList = new ArrayList<T>(dataList.subList(availableCapacity + 1, dataList.size()));
					cacheList.addAll(preList);
					Data2FileUtil.fileProcessForBean(cacheList, index, rptDataType);
					cacheList.clear();
					cacheList.addAll(afterList);
					// 更新缓存过期时间
					setCacheExpireTime(index);
					
					logger.debug("LocalCachePool.writeDataProcessor write into file and cache success" + "Cache index: " + index);
				}
			}
			// 数据序列不小于文件最大记录数
			else {
				//缓存内容优先写出
				if(CollectionUtils.isNotEmpty(cacheList)) {
					dataList.addAll(0, cacheList);
					cacheList.clear();
					initCacheExpireTime(index);
				}
				
				//重新计算dataList
				dataSize = dataList.size();
				int fileNum = dataSize / fileMaxLineLimit;
				int remainNum = dataSize % fileMaxLineLimit;
				
				for(int i = 0; i < fileNum; i++) {
					List<T> preList = null;
					if(i < fileNum - 1) {
						preList = new ArrayList<T>(dataList.subList(0, fileMaxLineLimit));
						dataList = new ArrayList<T>(dataList.subList(fileMaxLineLimit + 1, dataList.size()));
						Data2FileUtil.fileProcessForBean(preList, index, rptDataType);
					}
					else {
						preList = new ArrayList<T>(dataList.subList(0, fileMaxLineLimit));
						if(remainNum > 0) {
							dataList = new ArrayList<T>(dataList.subList(fileMaxLineLimit + 1, dataList.size()));
						} else {
							dataList.clear();
						}
						Data2FileUtil.fileProcessForBean(preList, index, rptDataType);
					}
				}
				
				// 递归处理
				if(CollectionUtils.isNotEmpty(dataList)) {
					writeDataProcessor(dataList, index, rptDataType);
				}
			}
		}
	}
	
	/**
	 * ReportData类型上报数据过期缓存数据写文件
	 * @param index
	 * @param rptDataType
	 */
	public void writeExpireCache(Integer index, ReportDataType rptDataType) {
		synchronized(lock) {
			Long expireTime = getCacheExpireTime(index);
			if (0L != expireTime.longValue()) {
				Long curTime = System.currentTimeMillis();
				Long time = (curTime - expireTime) / 1000;
				if (time.longValue() > cacheExpireTime) {
					List<T> dataList = getCacheDataList(index);
					Data2FileUtil.fileProcessForBean(dataList, index, rptDataType);
					dataList.clear();
					initCacheExpireTime(index);
					
					logger.debug("LocalCachePool.writeExpireCache invoke success" 
							+ "cache index: " + index + " rptDataType: " + rptDataType.getValue());
				}
			}
		}
	}
	
	/**
	 * 根据缓存索引获取缓存序列
	 * @param int index
	 * @return List<T>
	 */
	public List<T> getCacheDataList(Integer index) {
		synchronized(lock) {
			return cacheMap.get(cacheDataList + index);
		}
	}
	
	/**
	 * 根据缓存索引获取缓存过期时间
	 * @param index
	 * @return Long
	 */
	public Long getCacheExpireTime(Integer index) {
		synchronized(lock) {
			return expireMap.get(cacheDataList + index);
		}
	}
	
	/**
	 * 根据缓存索引设置缓存过期时间
	 * @param int index
	 */
	public void setCacheExpireTime(Integer index) {
		synchronized(lock) {
			expireMap.put(cacheDataList + index, System.currentTimeMillis());
		}
	}
	
	/**
	 * 根据缓存索引初始化缓存过期时间
	 * @param int index
	 */
	public void initCacheExpireTime(Integer index) {
		synchronized(lock) {
			expireMap.put(cacheDataList + index, 0L);
		}
	}
	
}
