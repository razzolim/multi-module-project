package com.renan.webservice;

import com.renan.service.RequestUserCreationService;
import com.renan.webservice.request.UserCreateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	private final RequestUserCreationService requestUserCreationService;

	public UserController(RequestUserCreationService requestUserCreationService) {
		this.requestUserCreationService = requestUserCreationService;
	}

	@PostMapping("/request/creation")
	public ResponseEntity send(@RequestBody UserCreateRequest body) {
		log.debug("Received request to request user creation: {}", body);
		requestUserCreationService.request(body.toDTO());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
