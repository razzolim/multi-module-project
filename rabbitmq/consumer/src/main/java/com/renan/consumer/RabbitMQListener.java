package com.renan.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.renan.service.CreateUserService;
import com.renan.service.dto.CreateUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RabbitMQListener {

	private static final Logger log = LoggerFactory.getLogger(RabbitMQListener.class);

	private final ObjectMapper mapper;
	private final CreateUserService createUserService;

	public RabbitMQListener(ObjectMapper mapper, CreateUserService createUserService) {
		this.mapper = mapper;
		this.createUserService = createUserService;
	}

	@RabbitListener(queues = {"my-first-queue"}, ackMode = "MANUAL")
	public void receive(String message, Channel channel, Message details) throws IOException {
		log.debug("Received user to be created? {}", message);

		if (message == null || message.isBlank()) {
			log.warn("Message is empty, rejecting it...");
			channel.basicReject(details.getMessageProperties().getDeliveryTag(), false);
			return;
		}

		try {
			CreateUserDTO userDto = mapper.readValue(message, CreateUserDTO.class);
			createUserService.create(userDto);
		} catch (JsonProcessingException ex) {
			log.error("Failed to read message due to bad format. Message: {}", message, ex);
			channel.basicReject(details.getMessageProperties().getDeliveryTag(), false);
			log.warn("User creation rejected: {}", message);
			return;
		}

		log.info("Processing received message: {}", message);
		channel.basicAck(details.getMessageProperties().getDeliveryTag(), false);
	}

}
