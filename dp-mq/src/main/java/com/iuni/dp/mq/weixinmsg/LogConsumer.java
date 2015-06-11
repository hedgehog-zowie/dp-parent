/*
 * @(#)LogConsumer.java 2013-8-5
 *
 * Copyright 2013 Shenzhen Gionee,Inc. All rights reserved.
 */
package com.iuni.dp.mq.weixinmsg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 * 
 * @author ZuoChangjun 2013-8-5
 */
public class LogConsumer implements Consumer {
	@Autowired
	protected  JdbcTemplate jdbcTemplate;
	
	public static final int BATCH_SIZE = 500;// 500
	public static final int CLICK_BATCH_SIZE = 10;// 10
	public static final int QUEUE_SIZE = 10000000;// 10000000
	public static final long FLUSH_TIME_PERIOD = 2 * 60 * 1000;// 2分钟
	public long currentTime = System.currentTimeMillis();
	public long lastBatchInsertTime = currentTime;
}