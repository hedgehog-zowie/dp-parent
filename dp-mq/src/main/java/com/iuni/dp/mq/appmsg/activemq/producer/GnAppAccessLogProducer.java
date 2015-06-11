package com.iuni.dp.mq.appmsg.activemq.producer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.iuni.dp.mq.common.activemq.QueueMsgProducer;
import com.iuni.dp.persist.datareport.model.GnAppAccessLog;

/**
 * 金立相关APP客户端AccessLog的AMQ生产者
 * @author CaiKe
 * @version drs-1.0.1
 */
public class GnAppAccessLogProducer extends QueueMsgProducer<GnAppAccessLog> {

	private static final Logger logger = LoggerFactory.getLogger(GnAppAccessLogProducer.class);
	
	public static ArrayBlockingQueue<GnAppAccessLog> gnAppAccessLogQueue = new ArrayBlockingQueue<GnAppAccessLog>(1000000);
	
	private ThreadPoolTaskExecutor executor;
	
	@Override
	public void send(GnAppAccessLog gnAppAccessLog) {
		if(null == gnAppAccessLog) {
			return;
		}
		
//		template.convertAndSend(destination, wjqClientAccessLog);
		
		try {
			gnAppAccessLogQueue.put(gnAppAccessLog);
		} catch (InterruptedException e) {
			logger.error("GnAppAccessLogProducer.send found InterruptedException.", e);
		} catch (Exception e) {
			logger.error("GnAppAccessLogProducer.send found Exception.", e);
		}
	}

	@PostConstruct
	public void doSend() {
		try {
//			new Thread(new GnAppAccessLogProducerThread()).start();
			executor.execute(new GnAppAccessLogProducerThread());
			
		} catch (Exception e) {
			logger.error("GnAppAccessLogProducer.doSend found Exception", e);
		}
	}
	
	public void setExecutor(ThreadPoolTaskExecutor executor) {
		this.executor = executor;
	}

	class GnAppAccessLogProducerThread implements Runnable {

		public boolean isRunnable = true;
		
		@Override
		public void run() {
			while(isRunnable) {
				try {
					GnAppAccessLog gnAppAccessLog = gnAppAccessLogQueue.poll(1000, TimeUnit.MILLISECONDS);
					if(null != gnAppAccessLog) {
						template.convertAndSend(destination, gnAppAccessLog);
						logger.info("GnAppAccessLogProducer send WjqClientAccessLog,wjqClientAccessLog={}"
								, new Object[]{gnAppAccessLog});
						Thread.sleep(1);
					}
					
				} catch (InterruptedException e) {
					logger.error("GnAppAccessLogProducerThread.run found InterruptedException.");
				} catch (Exception e) {
					logger.error("GnAppAccessLogProducerThread.run found Exception.");
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
