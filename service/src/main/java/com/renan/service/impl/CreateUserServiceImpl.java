package com.renan.service.impl;

import com.renan.entity.model.AppUserModel;
import com.renan.repository.AppUserRepository;
import com.renan.service.CreateUserService;
import com.renan.service.dto.CreateUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateUserServiceImpl implements CreateUserService {

	private static final Logger log = LoggerFactory.getLogger(CreateUserServiceImpl.class);

	private final AppUserRepository appUserRepository;

	public CreateUserServiceImpl(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}

	@Override
	public void create(CreateUserDTO dto) {
		log.info("Creating user: {}", dto);

		AppUserModel model = new AppUserModel();
		model.setEmail(dto.getEmail());
		model.setName(dto.getName());
		model.setSurname(dto.getSurname());

		appUserRepository.saveAndFlush(model);
	}

}
