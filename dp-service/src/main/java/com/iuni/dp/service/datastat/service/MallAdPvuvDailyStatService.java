package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.MallAdPvuvDailyStat;


/**
 * @ClassName MallAdPvuvDailyStatService
 * @author ZuoChangjun 2013-07-09 11:18:20
 * @version dp-service-1.0.0
 */
public interface MallAdPvuvDailyStatService {

   /**
	*  增加MallAdPvuvDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int insertMallAdPvuvDailyStat(MallAdPvuvDailyStat mallAdPvuvDailyStat)throws Exception;
	
	/**
	 * 增加MallAdPvuvDailyStat
	 * 
	 * @return 返回受影响的行数(供定时任务调用)
	 * @author ZuoChangjun
	 */
	public Integer insertMallAdPvuvDailyStatByTask(Map<String,Object> parammap)throws Exception;
	
   /**
	*  删除MallAdPvuvDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int deleteMallAdPvuvDailyStat(MallAdPvuvDailyStat mallAdPvuvDailyStat)throws Exception;
	
   /**
	*  删除MallAdPvuvDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int deleteMallAdPvuvDailyStatById(Integer id)throws Exception;
	
   /**
	*  更新MallAdPvuvDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int updateMallAdPvuvDailyStat(MallAdPvuvDailyStat mallAdPvuvDailyStat)throws Exception;
	
   /**
	*  查询MallAdPvuvDailyStat
	*  @author ZuoChangjun
 	*/
	public List<MallAdPvuvDailyStat> selectMallAdPvuvDailyStats(MallAdPvuvDailyStat mallAdPvuvDailyStat)throws Exception;
	
   /**
	*  查询MallAdPvuvDailyStat记录个数
	*  @author ZuoChangjun
 	*/
	public int selectMallAdPvuvDailyStatsCount(MallAdPvuvDailyStat mallAdPvuvDailyStat)throws Exception;
	
   /**
	*  分页查询MallAdPvuvDailyStat
	*  @author ZuoChangjun
 	*/
	public List<MallAdPvuvDailyStat> selectPagedMallAdPvuvDailyStats(MallAdPvuvDailyStat mallAdPvuvDailyStat)throws Exception;
	
   /**
	*  加载MallAdPvuvDailyStat
	*  @author ZuoChangjun
 	*/
	public MallAdPvuvDailyStat findMallAdPvuvDailyStatById(Integer id)throws Exception;
}