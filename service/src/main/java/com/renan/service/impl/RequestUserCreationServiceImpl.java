package com.renan.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.renan.publisher.interfaces.Sender;
import com.renan.service.RequestUserCreationService;
import com.renan.service.dto.CreateUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RequestUserCreationServiceImpl implements RequestUserCreationService {

	private static final Logger log = LoggerFactory.getLogger(RequestUserCreationServiceImpl.class);

	private final Sender sender;
	private final ObjectMapper mapper;

	public RequestUserCreationServiceImpl(
			Sender sender,
			ObjectMapper mapper
	) {
		this.sender = sender;
		this.mapper = mapper;
	}

	@Override
	public void request(CreateUserDTO user) {
		log.debug("requesting user creation: {}", user);

		try {
			String obj = mapper.writeValueAsString(user);
			sender.send(obj);
			log.info("User creation requested: {}", user);
		} catch (JsonProcessingException ex) {
			log.error("Failed to write user as string: {}", user, ex);
			throw new IllegalArgumentException("Invalid object");
		} catch (Exception ex) {
			log.error("Unknown exception, user won't be processed", ex);
			throw ex;
		}
	}

}
