package com.renan.publisher.impl;

import com.renan.publisher.interfaces.Sender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FirstQueueSender implements Sender {

	private final RabbitTemplate template;
	private final String exchangeName;
	private final String routingKey;

	public FirstQueueSender(
			RabbitTemplate template,
			@Value("${configuration.rabbitmq.main.exch.name}") String exchangeName,
			@Value("${configuration.rabbitmq.main.routing-key}") String routingKey
	) {
		this.template = template;
		this.exchangeName = exchangeName;
		this.routingKey = routingKey;
	}

	@Override
	public void send(String message) {
		template.convertAndSend(exchangeName, routingKey, message);
	}

}
