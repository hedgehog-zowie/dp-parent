package com.iuni.dp.persist.datastat.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.iuni.dp.persist.datastat.dao.MallAdOrderDailyStatDao;
import com.iuni.dp.persist.datastat.model.MallAdOrderDailyStat;

/**
 * @author ZuoChangjun 2013-07-09 11:18:42
 * @version dp-persist-1.0.0
 */
@Scope("prototype")
@Repository("mallAdOrderDailyStatDao")
public class MallAdOrderDailyStatDaoImpl implements MallAdOrderDailyStatDao {
	@Autowired
	@Qualifier("sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate = null;
	
   /**
	*  增加MallAdOrderDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public Integer insertMallAdOrderDailyStat(MallAdOrderDailyStat mallAdOrderDailyStat){
    	return (Integer) sqlMapClientTemplate.insert("MallAdOrderDailyStat.insertMallAdOrderDailyStat",mallAdOrderDailyStat);
	}
	
	/**
	 * 增加MallAdOrderDailyStat(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertMallAdOrderDailyStatByTask(Map<String,Object> paramMap){
		return (Integer) sqlMapClientTemplate.insert("MallAdOrderDailyStat.insertMallAdOrderDailyStatByTask",paramMap);
	}
   /**
	*  删除MallAdOrderDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int deleteMallAdOrderDailyStat(MallAdOrderDailyStat mallAdOrderDailyStat){
		return sqlMapClientTemplate.delete("MallAdOrderDailyStat.deleteMallAdOrderDailyStat",mallAdOrderDailyStat);
	}
	
   /**
	*  删除MallAdOrderDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int deleteMallAdOrderDailyStatById(Integer id){
		return sqlMapClientTemplate.delete("MallAdOrderDailyStat.deleteMallAdOrderDailyStatById",id);
	}
	
   /**
	*  更新MallAdOrderDailyStat
	*  @return 返回受影响的行数
	*  @author ZuoChangjun
 	*/
	public int updateMallAdOrderDailyStat(MallAdOrderDailyStat mallAdOrderDailyStat){
		return sqlMapClientTemplate.update("MallAdOrderDailyStat.updateMallAdOrderDailyStat",mallAdOrderDailyStat);
	}
	
   /**
	*  查询MallAdOrderDailyStat
	*  @author ZuoChangjun
 	*/
	public List<MallAdOrderDailyStat> selectMallAdOrderDailyStats(MallAdOrderDailyStat mallAdOrderDailyStat){
		return sqlMapClientTemplate.queryForList("MallAdOrderDailyStat.selectMallAdOrderDailyStats",mallAdOrderDailyStat);
	}
	
   /**
	*  查询MallAdOrderDailyStat记录个数
	*  @author ZuoChangjun
 	*/
	public int selectMallAdOrderDailyStatsCount(MallAdOrderDailyStat mallAdOrderDailyStat){
		return (Integer)sqlMapClientTemplate.queryForObject("MallAdOrderDailyStat.selectMallAdOrderDailyStatsCount",mallAdOrderDailyStat);
	}
	
   /**
	*  查询MallAdOrderDailyStat
	*  @author ZuoChangjun
 	*/
	public List<MallAdOrderDailyStat> selectPagedMallAdOrderDailyStats(MallAdOrderDailyStat mallAdOrderDailyStat){
		return sqlMapClientTemplate.queryForList("MallAdOrderDailyStat.selectPagedMallAdOrderDailyStats",mallAdOrderDailyStat);
	}
	
   /**
	*  加载MallAdOrderDailyStat
	*  @author ZuoChangjun
 	*/
	public MallAdOrderDailyStat findMallAdOrderDailyStatById(Integer id){
		return (MallAdOrderDailyStat)sqlMapClientTemplate.queryForObject("MallAdOrderDailyStat.findMallAdOrderDailyStatById",id);
	}
}