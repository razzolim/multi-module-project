package com.renan.publisher.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class RabbitMQConfig {

	private final String exchangeName;
	private final String routingKey;

	public RabbitMQConfig(
		@Value("${configuration.rabbitmq.main.routing-key}")String routingKey,
		@Value("${configuration.rabbitmq.main.exch.name}") String exchangeName
	) {
		this.routingKey = routingKey;
		this.exchangeName = exchangeName;
	}

	@Bean
	protected TopicExchange topicExchange() {
		return new TopicExchange(exchangeName);
	}

	@Bean
	protected DirectExchange dlxExchange() {
		return new DirectExchange("dlx-exchange");
	}

	@Bean
	@Qualifier("first-queue")
	protected Queue queue() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("x-dead-letter-exchange", "dlx-exchange");
		map.put("x-dead-letter-routing-key", "dlx-routing-key");
		map.put("x-message-ttl", 60000);

		return new Queue(
			"my-first-queue", true, false, false, map
		);
	}

	@Bean
	@Qualifier("dlq-queue")
	protected Queue dlqQueue() {
		return new Queue("dead-letter-queue");
	}

	@Bean
	protected Binding binding(
		@Qualifier("first-queue") Queue queue,
		TopicExchange topicExchange
	) {
		return BindingBuilder
				.bind(queue)
				.to(topicExchange)
				.with(routingKey);
	}

	@Bean
	protected Binding bindingDlq(
		@Qualifier("dlq-queue") Queue queue,
		DirectExchange dlxExchange
	) {
		return BindingBuilder
				.bind(queue)
				.to(dlxExchange)
				.with("dlx-routing-key");
	}

}
