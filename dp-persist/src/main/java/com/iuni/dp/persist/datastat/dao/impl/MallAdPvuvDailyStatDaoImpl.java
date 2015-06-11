package com.iuni.dp.persist.datastat.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.datastat.dao.MallAdPvuvDailyStatDao;
import com.iuni.dp.persist.datastat.model.MallAdPvuvDailyStat;

/**
 * @author ZuoChangjun 2013-07-09 11:18:20
 * @version dp-persist-1.0.0
 */
@Scope("prototype")
@Repository("mallAdPvuvDailyStatDao")
public class MallAdPvuvDailyStatDaoImpl implements MallAdPvuvDailyStatDao {
	@Autowired
	@Qualifier("sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate = null;
	
   /**
	*  增加MallAdPvuvDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public Integer insertMallAdPvuvDailyStat(MallAdPvuvDailyStat mallAdPvuvDailyStat){
    	return (Integer) sqlMapClientTemplate.insert("MallAdPvuvDailyStat.insertMallAdPvuvDailyStat",mallAdPvuvDailyStat);
	}
	
	/**
	 * 增加MallAdPvuvDailyStat
	 * 
	 * @return 返回受影响的行数(供定时任务调用)
	 * @author ZuoChangjun
	 */
	public Integer insertMallAdPvuvDailyStatByTask(Map<String,Object> paramMap){
		return (Integer) sqlMapClientTemplate.insert("MallAdPvuvDailyStat.insertMallAdPvuvDailyStatByTask",paramMap);
	}
   /**
	*  删除MallAdPvuvDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int deleteMallAdPvuvDailyStat(MallAdPvuvDailyStat mallAdPvuvDailyStat){
		return sqlMapClientTemplate.delete("MallAdPvuvDailyStat.deleteMallAdPvuvDailyStat",mallAdPvuvDailyStat);
	}
	
   /**
	*  删除MallAdPvuvDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int deleteMallAdPvuvDailyStatById(Integer id){
		return sqlMapClientTemplate.delete("MallAdPvuvDailyStat.deleteMallAdPvuvDailyStatById",id);
	}
	
   /**
	*  更新MallAdPvuvDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int updateMallAdPvuvDailyStat(MallAdPvuvDailyStat mallAdPvuvDailyStat){
		return sqlMapClientTemplate.update("MallAdPvuvDailyStat.updateMallAdPvuvDailyStat",mallAdPvuvDailyStat);
	}
	
   /**
	*  查询MallAdPvuvDailyStat
	*  @author ZuoChangjun
 	*/
	public List<MallAdPvuvDailyStat> selectMallAdPvuvDailyStats(MallAdPvuvDailyStat mallAdPvuvDailyStat){
		return sqlMapClientTemplate.queryForList("MallAdPvuvDailyStat.selectMallAdPvuvDailyStats",mallAdPvuvDailyStat);
	}
	
   /**
	*  查询MallAdPvuvDailyStat记录个数
	*  @author ZuoChangjun
 	*/
	public int selectMallAdPvuvDailyStatsCount(MallAdPvuvDailyStat mallAdPvuvDailyStat){
		return (Integer)sqlMapClientTemplate.queryForObject("MallAdPvuvDailyStat.selectMallAdPvuvDailyStatsCount",mallAdPvuvDailyStat);
	}
	
   /**
	*  查询MallAdPvuvDailyStat
	*  @author ZuoChangjun
 	*/
	public List<MallAdPvuvDailyStat> selectPagedMallAdPvuvDailyStats(MallAdPvuvDailyStat mallAdPvuvDailyStat){
		return sqlMapClientTemplate.queryForList("MallAdPvuvDailyStat.selectPagedMallAdPvuvDailyStats",mallAdPvuvDailyStat);
	}
	
   /**
	*  加载MallAdPvuvDailyStat
	*  @author ZuoChangjun
 	*/
	public MallAdPvuvDailyStat findMallAdPvuvDailyStatById(Integer id){
		return (MallAdPvuvDailyStat)sqlMapClientTemplate.queryForObject("MallAdPvuvDailyStat.findMallAdPvuvDailyStatById",id);
	}
}