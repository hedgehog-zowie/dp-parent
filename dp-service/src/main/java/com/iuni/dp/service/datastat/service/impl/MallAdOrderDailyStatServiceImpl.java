package com.iuni.dp.service.datastat.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iuni.dp.persist.datastat.dao.MallAdOrderDailyStatDao;
import com.iuni.dp.persist.datastat.model.MallAdOrderDailyStat;
import com.iuni.dp.service.datastat.service.MallAdOrderDailyStatService;

/**
 * @author ZuoChangjun 2013-07-09 11:18:42
 * @version dp-service-1.0.0
 */
@Transactional
@Service("mallAdOrderDailyStatService")
public class MallAdOrderDailyStatServiceImpl implements MallAdOrderDailyStatService {
	private static Log log = LogFactory
			.getLog(MallAdOrderDailyStatServiceImpl.class);

	@Autowired
	@Qualifier("mallAdOrderDailyStatDao")
	public MallAdOrderDailyStatDao mallAdOrderDailyStatDao = null;

	/**
	 * 增加MallAdOrderDailyStat
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public int insertMallAdOrderDailyStat(
			MallAdOrderDailyStat mallAdOrderDailyStat) throws Exception {
		return mallAdOrderDailyStatDao
				.insertMallAdOrderDailyStat(mallAdOrderDailyStat);
	}

	/**
	 * 增加MallAdOrderDailyStat(供定时任务调用)
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public Integer insertMallAdOrderDailyStatByTask(Map<String,Object> parammap)throws Exception{
		return mallAdOrderDailyStatDao
		.insertMallAdOrderDailyStatByTask(parammap);
	}
	/**
	 * 删除MallAdOrderDailyStat
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public int deleteMallAdOrderDailyStat(
			MallAdOrderDailyStat mallAdOrderDailyStat) throws Exception {
		return mallAdOrderDailyStatDao
				.deleteMallAdOrderDailyStat(mallAdOrderDailyStat);
	}

	/**
	 * 删除MallAdOrderDailyStat
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public int deleteMallAdOrderDailyStatById(Integer id) throws Exception {
		return mallAdOrderDailyStatDao.deleteMallAdOrderDailyStatById(id);
	}

	/**
	 * 更新MallAdOrderDailyStat
	 * 
	 * @return 返回受影响的行数
	 * @author ZuoChangjun
	 */
	public int updateMallAdOrderDailyStat(
			MallAdOrderDailyStat mallAdOrderDailyStat) throws Exception {
		return mallAdOrderDailyStatDao
				.updateMallAdOrderDailyStat(mallAdOrderDailyStat);
	}

	/**
	 * 查询MallAdOrderDailyStat
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallAdOrderDailyStat> selectMallAdOrderDailyStats(
			MallAdOrderDailyStat mallAdOrderDailyStat) throws Exception {
		return mallAdOrderDailyStatDao
				.selectMallAdOrderDailyStats(mallAdOrderDailyStat);
	}

	/**
	 * 查询MallAdOrderDailyStat记录个数
	 * 
	 * @author ZuoChangjun
	 */
	public int selectMallAdOrderDailyStatsCount(
			MallAdOrderDailyStat mallAdOrderDailyStat) throws Exception {
		return mallAdOrderDailyStatDao
				.selectMallAdOrderDailyStatsCount(mallAdOrderDailyStat);
	}

	/**
	 * 分页查询MallAdOrderDailyStat
	 * 
	 * @author ZuoChangjun
	 */
	public List<MallAdOrderDailyStat> selectPagedMallAdOrderDailyStats(
			MallAdOrderDailyStat mallAdOrderDailyStat) throws Exception {
		return mallAdOrderDailyStatDao
				.selectPagedMallAdOrderDailyStats(mallAdOrderDailyStat);
	}

	/**
	 * 加载MallAdOrderDailyStat
	 * 
	 * @author ZuoChangjun
	 */
	public MallAdOrderDailyStat findMallAdOrderDailyStatById(Integer id)
			throws Exception {
		return mallAdOrderDailyStatDao.findMallAdOrderDailyStatById(id);
	}
}
