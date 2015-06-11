package com.iuni.dp.mq.common.activemq;

/**
 * Consumer Marker Interface
 * @author CaiKe
 * @version dp-mq-1.0.0
 */
public interface Consumer<T> {
	
	/**
	 * Receive Message From AMQ
	 * @param t
	 */
	public void receive(T t);
	
}
