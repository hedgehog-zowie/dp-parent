package com.iuni.dp.persist.datastat.dao;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.MallAdOrderDailyStat;

/**
 * @author ZuoChangjun 2013-07-09 11:18:42
 * @version dp-persist-1.0.0
 */
public interface MallAdOrderDailyStatDao {

   /**
	*  增加MallAdOrderDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public Integer insertMallAdOrderDailyStat(MallAdOrderDailyStat mallAdOrderDailyStat);
	
	/**
	 * 增加MallAdOrderDailyStat(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertMallAdOrderDailyStatByTask(Map<String,Object> paramMap);
	
   /**
	*  删除MallAdOrderDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int deleteMallAdOrderDailyStat(MallAdOrderDailyStat mallAdOrderDailyStat);
	
   /**
	*  删除MallAdOrderDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int deleteMallAdOrderDailyStatById(Integer id);
	
   /**
	*  更新MallAdOrderDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int updateMallAdOrderDailyStat(MallAdOrderDailyStat mallAdOrderDailyStat);
	
   /**
	*  查询MallAdOrderDailyStat
	*  @author ZuoChangjun
 	*/
	public List<MallAdOrderDailyStat> selectMallAdOrderDailyStats(MallAdOrderDailyStat mallAdOrderDailyStat);
	
   /**
	*  查询MallAdOrderDailyStat记录个数
	*  @author ZuoChangjun
 	*/
	public int selectMallAdOrderDailyStatsCount(MallAdOrderDailyStat mallAdOrderDailyStat);
	
  /**
	*  分页查询MallAdOrderDailyStat
	*  @author ZuoChangjun
 	*/
	public List<MallAdOrderDailyStat> selectPagedMallAdOrderDailyStats(MallAdOrderDailyStat mallAdOrderDailyStat);
	
   /**
	*  加载MallAdOrderDailyStat
	*  @author ZuoChangjun
 	*/
	public MallAdOrderDailyStat findMallAdOrderDailyStatById(Integer id);

}