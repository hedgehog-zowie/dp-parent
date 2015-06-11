package com.iuni.dp.service.datastat.service;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.MallAdOrderDailyStat;


/**
 * @ClassName MallAdOrderDailyStatService
 * @author ZuoChangjun 2013-07-09 11:18:42
 * @version dp-service-1.0.0
 */
public interface MallAdOrderDailyStatService {

   /**
	*  增加MallAdOrderDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int insertMallAdOrderDailyStat(MallAdOrderDailyStat mallAdOrderDailyStat)throws Exception;
	
	/**
	 * 增加MallAdOrderDailyStat(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertMallAdOrderDailyStatByTask(Map<String,Object> parammap)throws Exception;
   /**
	*  删除MallAdOrderDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int deleteMallAdOrderDailyStat(MallAdOrderDailyStat mallAdOrderDailyStat)throws Exception;
	
   /**
	*  删除MallAdOrderDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int deleteMallAdOrderDailyStatById(Integer id)throws Exception;
	
   /**
	*  更新MallAdOrderDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int updateMallAdOrderDailyStat(MallAdOrderDailyStat mallAdOrderDailyStat)throws Exception;
	
   /**
	*  查询MallAdOrderDailyStat
	*  @author ZuoChangjun
 	*/
	public List<MallAdOrderDailyStat> selectMallAdOrderDailyStats(MallAdOrderDailyStat mallAdOrderDailyStat)throws Exception;
	
   /**
	*  查询MallAdOrderDailyStat记录个数
	*  @author ZuoChangjun
 	*/
	public int selectMallAdOrderDailyStatsCount(MallAdOrderDailyStat mallAdOrderDailyStat)throws Exception;
	
   /**
	*  分页查询MallAdOrderDailyStat
	*  @author ZuoChangjun
 	*/
	public List<MallAdOrderDailyStat> selectPagedMallAdOrderDailyStats(MallAdOrderDailyStat mallAdOrderDailyStat)throws Exception;
	
   /**
	*  加载MallAdOrderDailyStat
	*  @author ZuoChangjun
 	*/
	public MallAdOrderDailyStat findMallAdOrderDailyStatById(Integer id)throws Exception;
}