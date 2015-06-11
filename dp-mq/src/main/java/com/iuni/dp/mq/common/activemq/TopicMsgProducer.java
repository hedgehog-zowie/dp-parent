package com.iuni.dp.mq.common.activemq;

import javax.jms.Topic;

import org.springframework.jms.core.JmsTemplate;

/**
 * Topic Message Producer
 * @author CaiKe
 * @version drs-1.0.1
 */
public abstract class TopicMsgProducer<T> implements Producer<T> {

	protected Topic destination;
	
	protected JmsTemplate template;

	public void setDestination(Topic destination) {
		this.destination = destination;
	}

	public void setTemplate(JmsTemplate template) {
		this.template = template;
	}
	
}
