/*
 * @(#)AdvLogConsumer.java 2012-3-12
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
import com.iuni.dp.persist.datareport.model.WxAccessLog;

/**
 * 获取广告日志消费者
 * 
 * @author ZuoChangjun 2012-2-29
 */
// @Service
public class WxAccessLogConsumer extends LogConsumer {

	private static Logger logger = LoggerFactory.getLogger(WxAccessLogConsumer.class);

	private static List<WxAccessLog> wxAccessLogList = new ArrayList<WxAccessLog>();

	/**
	 * 消费者方法
	 * 
	 * @param wxAccessLog
	 */
	public void receive(WxAccessLog wxAccessLog) {
		try {
			currentTime = System.currentTimeMillis();
			// WxAccessLog wxAccessLog =
			// wxAccessLogQueue.poll(1000,TimeUnit.MILLISECONDS);
			if (wxAccessLog != null) {
				wxAccessLogList.add(wxAccessLog);
			}
			// 针对系统Log表数据很空,为了让目前少量请求产生的数据量及时得到提交，所以每隔5分钟刷新一次(比如：16:45:00,16:50:00,16:55:00)
			// if(Calendar.getInstance().get(Calendar.MINUTE)%5==0 &&
			// Calendar.getInstance().get(Calendar.SECOND)==0){
			if (currentTime - lastBatchInsertTime >= FLUSH_TIME_PERIOD) {
				// 批量插入后并清空wxAccessLogList
				batchInsertAdvLog();
				lastBatchInsertTime = currentTime;
			}
			if (wxAccessLogList.size() < BATCH_SIZE) {
				return;
			}
			batchInsertAdvLog();
			lastBatchInsertTime = currentTime;
		} catch (Exception e) {
			logger.error(WxAccessLogConsumer.class.getName()+"保存WxAccessLog异常:"+ e.getMessage());
			wxAccessLogList.clear();// 清除列表,防止出现死循环
		}
	}

	/**
	 * 批量插入获取广告日志
	 * 
	 * @throws InterruptedException
	 */
	public void batchInsertAdvLog() throws InterruptedException {
		if (wxAccessLogList.size() == 0) {
			return;
		}
		String sql = "INSERT INTO WX_ACCESS_LOG( ID, USER_ID, PUBLIC_USER_ID, MSG_TYPE, KEYWORD, KEYWORD_TYPE, EVNET_TYPE, HAS_RESULT, RULE_ID, IMG_ID, IMG_NAME, IMG_URL, TAG_NAME, TXT_ID, COST_TIME, LOG_TIME, CREATE_TIME) values(S_WX_ACCESS_LOG.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		//jdbcTemplate = dynamicJdbcTemplateService.getReverseDynamicJdbcTemplate();
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				try {
					String userId = wxAccessLogList.get(i).getUserId();
					String publicUserId = wxAccessLogList.get(i).getPublicUserId();
					String msgType = wxAccessLogList.get(i).getMsgType();
					String keyword = wxAccessLogList.get(i).getKeyword();
					Integer keywordType = wxAccessLogList.get(i).getKeywordType();
					String eventType = wxAccessLogList.get(i).getEvnetType();
					Integer hasResult = wxAccessLogList.get(i).getHasResult();
					Long ruleId = wxAccessLogList.get(i).getRuleId();
					Long imgId = wxAccessLogList.get(i).getImgId();
					String imgName = wxAccessLogList.get(i).getImgName();
					String imgUrl = wxAccessLogList.get(i).getImgUrl();
					String tagName = wxAccessLogList.get(i).getTagName();
					Long txtId = wxAccessLogList.get(i).getTxtId();
					Integer costTime = wxAccessLogList.get(i).getCostTime();
					Date logTime = wxAccessLogList.get(i).getLogTime();
					//Date createTime = wxAccessLogList.get(i).getCreateTime();
					
					ps.setString(1, userId);
					ps.setString(2, publicUserId);
					ps.setString(3, msgType);
					ps.setString(4, keyword);
					ps.setString(5, keywordType == null ? null : keywordType+ "");
					ps.setString(6, eventType);
					ps.setString(7, hasResult == null ? null : hasResult+ "");
					ps.setString(8, ruleId == null ? null : ruleId + "");
					ps.setString(9, imgId == null ? null : imgId + "");
					ps.setString(10, imgName);
					ps.setString(11, imgUrl);
					ps.setString(12, tagName);
					ps.setString(13, txtId == null ? null : txtId + "");
					ps.setString(14, costTime == null ? null : costTime + "");
					ps.setTimestamp(15, logTime == null ? null: new java.sql.Timestamp(logTime.getTime()));
					//ps.setTimestamp(16, createTime == null ? null: new java.sql.Timestamp(createTime.getTime()));
				} catch (Exception e) {
					logger.error(WxAccessLogConsumer.class.getName()+"保存WxAccessLog异常:"
							+ e.getMessage() + ",userId="
							+ wxAccessLogList.get(i).getUserId() + ",publicUserId="
							+ wxAccessLogList.get(i).getPublicUserId() + ",msgType="
							+ wxAccessLogList.get(i).getMsgType());
					wxAccessLogList.remove(i);// 清除列表,防止出现死循环
				}
			}

			public int getBatchSize() {
				return wxAccessLogList.size();
			}
		});
		logger.info(WxAccessLogConsumer.class.getName()+"保存WxAccessLog成功!batchSize="+ wxAccessLogList.size());
		wxAccessLogList.clear();// 清除列表
		Thread.sleep(1);
	}
}