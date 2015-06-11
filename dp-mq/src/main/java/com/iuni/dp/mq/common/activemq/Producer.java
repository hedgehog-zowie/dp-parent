package com.iuni.dp.mq.common.activemq;

/**
 * Producer Marker Interface
 * @author CaiKe
 * @version dp-mq-1.0.0
 */
public interface Producer<T> {
	
	/**
	 * Send Message To AMQ
	 * @param t
	 */
	public void send(T t);
	
}
