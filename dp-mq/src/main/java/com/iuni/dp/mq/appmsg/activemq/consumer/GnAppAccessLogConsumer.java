package com.iuni.dp.mq.appmsg.activemq.consumer;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.iuni.dp.mq.appmsg.constants.GnAppReportConstant;
import com.iuni.dp.mq.common.activemq.Consumer;
import com.iuni.dp.persist.common.utils.ParseProperties;
import com.iuni.dp.persist.datareport.model.GnAppAccessLog;
import com.iuni.dp.service.common.exception.DBException;
import com.iuni.dp.service.datareport.service.GnAppAccessLogService;

/**
 * 玩机圈APP客户端AccessLog的AMQ消费者
 * @author CaiKe
 * @version drs-1.0.1
 */
@Component("gnAppAccessLogConsumer")
@Scope("singleton")
public class GnAppAccessLogConsumer implements Consumer<GnAppAccessLog> {
	
	private static final Logger logger = LoggerFactory.getLogger(GnAppAccessLogConsumer.class);
	
	protected static final int BATCH_SIZE = ParseProperties.getIntVal(GnAppReportConstant.WJQAPP_AMQ_BATCHDATA_SIZE);
	protected static final int FLUSH_DATA_TIME = ParseProperties.getIntVal(GnAppReportConstant.WJQAPP_AMQ_FLUSHDATA_TIME);
	protected static final int THREAD_SCAN_TIME = ParseProperties.getIntVal(GnAppReportConstant.WJQAPP_AMQ_THREADSCAN_TIME);
	
	private List<GnAppAccessLog> gnAppAccessLogs = new ArrayList<GnAppAccessLog>(BATCH_SIZE * 2);
	private byte[] logs_lock = new byte[0];
	private long lastProcessTime = System.currentTimeMillis();
	
	@Autowired
	private GnAppAccessLogService gnAppAccessLogService;
	
	@Autowired
	private ThreadPoolTaskExecutor executor;
	
	@Override
	public void receive(GnAppAccessLog gnAppAccessLog) {
		
		// wjqClientAccessLog不空则更新缓存
		if(null != gnAppAccessLog) {
			// 新增WjqClientAccessLog数据至本地缓存
			addAccessLog2Cache(gnAppAccessLog);
		}
		
		// 当从AMQ Queue接收到消息时处理WjqClientAccessLog本地缓存
		processAccessLogCache();
	}
	
	/**
	 * 新增WjqClientAccessLog数据至本地缓存
	 * @param wjqClientAccessLog
	 */
	public void addAccessLog2Cache(GnAppAccessLog gnAppAccessLog) {
		synchronized(logs_lock) {
			gnAppAccessLogs.add(gnAppAccessLog);
			
			logger.info("GnAppAccessLogConsumer has received info from AMQ queue, gnAppAccessLog={}" 
					,new Object[]{gnAppAccessLog});
		}
	}
	
	/**
	 * 处理WjqClientAccessLog本地缓存
	 * 1：AMQ Queue接收到消息; 2:轮询扫描线程处理时
	 */
	public void processAccessLogCache() {
		List<GnAppAccessLog> result = null;
		
		synchronized(logs_lock) {
			long currentTime = System.currentTimeMillis();
			if(gnAppAccessLogs.size() >= BATCH_SIZE 
					|| (gnAppAccessLogs.size() > 0 && currentTime - lastProcessTime > FLUSH_DATA_TIME)) {
				result = new ArrayList<GnAppAccessLog>(BATCH_SIZE);
				result.addAll(gnAppAccessLogs);
				gnAppAccessLogs.clear();
				lastProcessTime = System.currentTimeMillis();
			}
		}
		
		if(CollectionUtils.isNotEmpty(result)) {
			try {
				gnAppAccessLogService.batchSaveGnAppAccessLog(result);
				
				logger.info("GnAppAccessLogConsumer process GnAppAccessLog local cache.");
			} catch(DBException e) {
				// 入库异常，回滚该部分数据，将该批次处理数据恢复到本地缓存中
				synchronized(logs_lock) {
					gnAppAccessLogs.addAll(result);
					
					logger.error("GnAppAccessLogConsumer.processAccessLogCache found DBException"
							+ ", roll back the current processing of data, dataSize={}"
							, new Object[]{result.size()});
				}
			}
		}
	}
	
	@PostConstruct
	public void doReceive() {
		try {
//			new Thread(new GnAppAccessLogConsumerThread()).start();
			executor.execute(new GnAppAccessLogConsumerThread());
			logger.info("GnAppAccessLogConsumer.doReceive has executed GnAppAccessLogConsumerThread successfully.");
			
		} catch (Exception e) {
			logger.error("GnAppAccessLogConsumer.doReceive found Exception", e);
		}
	}
	
	class GnAppAccessLogConsumerThread implements Runnable {
		
		public boolean isRunnable = true;
		
		@Override
		public void run() {
			
			while(isRunnable) {
				try {
					// 当轮询扫描线程处理时处理WjqClientAccessLog本地缓存
					processAccessLogCache();
					
					Thread.sleep(THREAD_SCAN_TIME);
				} catch (InterruptedException e) {
					logger.error("GnAppAccessLogConsumerThread.run found InterruptedException.", e);
				} catch (Exception e) {
					logger.error("GnAppAccessLogConsumerThread.run found Exception.", e);
				}
			}
		}

		public boolean isRunnable() {
			return isRunnable;
		}

		public void setRunnable(boolean isRunnable) {
			this.isRunnable = isRunnable;
		}
		
	}
	
}
