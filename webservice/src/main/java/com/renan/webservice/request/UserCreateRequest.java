package com.renan.webservice.request;

import com.renan.service.dto.CreateUserDTO;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public class UserCreateRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String surname;

	@NotBlank
	private String email;

	public UserCreateRequest() {
		//
	}

	public UserCreateRequest(String name, String surname, String email) {
		this.name = name;
		this.surname = surname;
		this.email = email;
	}

	public CreateUserDTO toDTO() {
		return new CreateUserDTO(null, this.name, this.surname, this.email);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserCreateRequest{" +
				"name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
