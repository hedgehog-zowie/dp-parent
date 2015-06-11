
/*
 * @(#)WxClickLogConsumer.java 2012-2-29
 *
 * Copyright 2012 SH-BBMF,Inc. All rights reserved.
 */
package com.iuni.dp.mq.weixinmsg.consumer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.iuni.dp.mq.weixinmsg.LogConsumer;
import com.iuni.dp.persist.datareport.model.WxClickLog;

/**
* 广告点击日志消费者
* @author ZuoChangjun 2012-2-29
*/
//@Service
public class WxClickLogConsumer extends LogConsumer {
	
	private static Logger logger = LoggerFactory.getLogger(WxClickLogConsumer.class);	
	public static List<WxClickLog> wxClickLogList=new ArrayList<WxClickLog>();	
	
	/**
	 * 消费者方法
	 * @param wxClickLog
	 */
	public void receive(WxClickLog wxClickLog) {
		try {
			currentTime = System.currentTimeMillis();
			// WxClickLog wxClickLog =wxClickLogQueue.poll(1000,TimeUnit.MILLISECONDS);
			if (wxClickLog != null) {
				wxClickLogList.add(wxClickLog);
			}
			// 针对系统Log表数据很空,为了让目前少量请求产生的数据量及时得到提交，所以每隔5分钟刷新一次(比如：16:45:00,16:50:00,16:55:00)
			// if(Calendar.getInstance().get(Calendar.MINUTE)%5==0 &&
			// Calendar.getInstance().get(Calendar.SECOND)==0){
			if (currentTime - lastBatchInsertTime >= FLUSH_TIME_PERIOD) {
				// 批量插入后并清空wxClickLogList
				batchInsertWxClickLog();
				lastBatchInsertTime = currentTime;
			}
			if (wxClickLogList.size() < CLICK_BATCH_SIZE) {
				return;
			}
			// 批量插入后并清空wxClickLogList
			batchInsertWxClickLog();
			lastBatchInsertTime = currentTime;
		} catch (Exception e) {
			logger.error("bbmf-mq:WxClickLogConsumer保存WxClickLog异常:"
					+ e.getMessage());
			wxClickLogList.clear();// 清除列表,防止出现死循环
		}
	}

	/**
	 * 批量插入广告点击日志
	 * @throws InterruptedException 
	 */
	public void batchInsertWxClickLog() throws InterruptedException{
		if(wxClickLogList.size()==0){
			return;
		}
		String sql = "INSERT INTO WX_CLICK_LOG( ID, USER_ID, PUBLIC_USER_ID, IMG_ID, TXT_ID, LOG_TIME, CREATE_TIME ) values(S_WX_CLICK_LOG.NEXTVAL,?,?,?,?,?,sysdate)";
		//jdbcTemplate = dynamicJdbcTemplateService.getReverseDynamicJdbcTemplate();
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				try {

					String userId = wxClickLogList.get(i).getUserId();
					String publicUserId = wxClickLogList.get(i).getPublicUserId();
					Long imgId = wxClickLogList.get(i).getImgId();
					Long txtId = wxClickLogList.get(i).getTxtId();
					Date logTime = wxClickLogList.get(i).getLogTime();
					//Date createTime = wxClickLogList.get(i).getCreateTime();
					
					ps.setString(1, userId);
					ps.setString(2, publicUserId);
					ps.setString(3, imgId == null ? null : imgId + "");
					ps.setString(4, txtId == null ? null : txtId + "");
					ps.setTimestamp(5, logTime == null ? null: new java.sql.Timestamp(logTime.getTime()));
					//ps.setTimestamp(6, createTime == null ? null: new java.sql.Timestamp(createTime.getTime()));
				
				} catch (Exception e) {
					logger.error(WxClickLogConsumer.class.getName()+"保存WxClickLog异常:"
							+ e.getMessage() + ",userId="
							+ wxClickLogList.get(i).getUserId() + ",publicUserId="
							+ wxClickLogList.get(i).getPublicUserId() + ",imgId="
							+ wxClickLogList.get(i).getImgId());
					wxClickLogList.remove(i);//清除,防止出现死循环
				}
			}

			public int getBatchSize() {
				return wxClickLogList.size();
			}
		});
		logger.info(WxClickLogConsumer.class.getName()+"保存WxClickLog成功!batchSize="+wxClickLogList.size());
		wxClickLogList.clear();
		Thread.sleep(1);
	}
}
