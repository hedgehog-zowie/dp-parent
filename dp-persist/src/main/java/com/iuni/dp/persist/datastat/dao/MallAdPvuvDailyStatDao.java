package com.iuni.dp.persist.datastat.dao;

import java.util.List;
import java.util.Map;

import com.iuni.dp.persist.datastat.model.MallAdPvuvDailyStat;

/**
 * @author ZuoChangjun 2013-07-09 11:18:20
 * @version dp-persist-1.0.0
 */
public interface MallAdPvuvDailyStatDao {

   /**
	*  增加MallAdPvuvDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public Integer insertMallAdPvuvDailyStat(MallAdPvuvDailyStat mallAdPvuvDailyStat);
	
	/**
	 * 增加MallAdPvuvDailyStat
	 * 
	 * @return 返回受影响的行数(供定时任务调用)
	 * @author ZuoChangjun
	 */
	public Integer insertMallAdPvuvDailyStatByTask(Map<String,Object> parammap);
	
   /**
	*  删除MallAdPvuvDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int deleteMallAdPvuvDailyStat(MallAdPvuvDailyStat mallAdPvuvDailyStat);
	
   /**
	*  删除MallAdPvuvDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int deleteMallAdPvuvDailyStatById(Integer id);
	
   /**
	*  更新MallAdPvuvDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int updateMallAdPvuvDailyStat(MallAdPvuvDailyStat mallAdPvuvDailyStat);
	
   /**
	*  查询MallAdPvuvDailyStat
	*  @author ZuoChangjun
 	*/
	public List<MallAdPvuvDailyStat> selectMallAdPvuvDailyStats(MallAdPvuvDailyStat mallAdPvuvDailyStat);
	
   /**
	*  查询MallAdPvuvDailyStat记录个数
	*  @author ZuoChangjun
 	*/
	public int selectMallAdPvuvDailyStatsCount(MallAdPvuvDailyStat mallAdPvuvDailyStat);
	
  /**
	*  分页查询MallAdPvuvDailyStat
	*  @author ZuoChangjun
 	*/
	public List<MallAdPvuvDailyStat> selectPagedMallAdPvuvDailyStats(MallAdPvuvDailyStat mallAdPvuvDailyStat);
	
   /**
	*  加载MallAdPvuvDailyStat
	*  @author ZuoChangjun
 	*/
	public MallAdPvuvDailyStat findMallAdPvuvDailyStatById(Integer id);

}