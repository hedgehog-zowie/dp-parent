package com.iuni.dp.mq.common.activemq;

import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;

/**
 * Queue Message Producer
 * @author CaiKe
 * @version dp-mq-1.0.0
 */
public abstract class QueueMsgProducer<T> implements Producer<T> {

	protected Queue destination;
	
	protected JmsTemplate template;

	public void setDestination(Queue destination) {
		this.destination = destination;
	}

	public void setTemplate(JmsTemplate template) {
		this.template = template;
	}

}
