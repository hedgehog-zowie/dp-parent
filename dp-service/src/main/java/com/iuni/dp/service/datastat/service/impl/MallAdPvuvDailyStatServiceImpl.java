package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iuni.dp.persist.datastat.dao.MallAdPvuvDailyStatDao;
import com.iuni.dp.persist.datastat.model.MallAdPvuvDailyStat;
import com.iuni.dp.service.datastat.service.MallAdPvuvDailyStatService;


/**
 * @author ZuoChangjun 2013-07-09 11:18:20
 * @version dp-service-1.0.0
 */
@Transactional
@Service("mallAdPvuvDailyStatService")
public class MallAdPvuvDailyStatServiceImpl implements MallAdPvuvDailyStatService {
	private static Log log = LogFactory.getLog(MallAdPvuvDailyStatServiceImpl.class);
	
	@Autowired
	@Qualifier("mallAdPvuvDailyStatDao")
	public MallAdPvuvDailyStatDao mallAdPvuvDailyStatDao = null;
	
   /**
	*  增加MallAdPvuvDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int insertMallAdPvuvDailyStat(MallAdPvuvDailyStat mallAdPvuvDailyStat)throws Exception{
    		return mallAdPvuvDailyStatDao.insertMallAdPvuvDailyStat(mallAdPvuvDailyStat);
	}
	
	/**
	 * 增加MallAdPvuvDailyStat
	 * 
	 * @return 返回受影响的行数(供定时任务调用)
	 * @author ZuoChangjun
	 */
	public Integer insertMallAdPvuvDailyStatByTask(Map<String,Object> parammap)throws Exception{
		return mallAdPvuvDailyStatDao.insertMallAdPvuvDailyStatByTask(parammap);
	}
	
   /**
	*  删除MallAdPvuvDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int deleteMallAdPvuvDailyStat(MallAdPvuvDailyStat mallAdPvuvDailyStat)throws Exception{
			return mallAdPvuvDailyStatDao.deleteMallAdPvuvDailyStat(mallAdPvuvDailyStat);
	}
	
   /**
	*  删除MallAdPvuvDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int deleteMallAdPvuvDailyStatById(Integer id)throws Exception{
			return mallAdPvuvDailyStatDao.deleteMallAdPvuvDailyStatById(id);
	}
   /**
	*  更新MallAdPvuvDailyStat
    *  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int updateMallAdPvuvDailyStat(MallAdPvuvDailyStat mallAdPvuvDailyStat)throws Exception{
			return mallAdPvuvDailyStatDao.updateMallAdPvuvDailyStat(mallAdPvuvDailyStat);
	}
	
   /**
	*  查询MallAdPvuvDailyStat
	*  @author ZuoChangjun
 	*/
	public List<MallAdPvuvDailyStat> selectMallAdPvuvDailyStats(MallAdPvuvDailyStat mallAdPvuvDailyStat)throws Exception{
			return mallAdPvuvDailyStatDao.selectMallAdPvuvDailyStats(mallAdPvuvDailyStat);
	}
	
   /**
	*  查询MallAdPvuvDailyStat记录个数
	*  @author ZuoChangjun
 	*/
	public int selectMallAdPvuvDailyStatsCount(MallAdPvuvDailyStat mallAdPvuvDailyStat)throws Exception{
			return mallAdPvuvDailyStatDao.selectMallAdPvuvDailyStatsCount(mallAdPvuvDailyStat);
	}
	
   /**
	*  分页查询MallAdPvuvDailyStat
	*  @author ZuoChangjun
 	*/
	public List<MallAdPvuvDailyStat> selectPagedMallAdPvuvDailyStats(MallAdPvuvDailyStat mallAdPvuvDailyStat)throws Exception{
			return mallAdPvuvDailyStatDao.selectPagedMallAdPvuvDailyStats(mallAdPvuvDailyStat);
	}
	
   /**
	*  加载MallAdPvuvDailyStat
	*  @author ZuoChangjun
 	*/
	public MallAdPvuvDailyStat findMallAdPvuvDailyStatById(Integer id)throws Exception{
			return mallAdPvuvDailyStatDao.findMallAdPvuvDailyStatById(id);
	}
}
