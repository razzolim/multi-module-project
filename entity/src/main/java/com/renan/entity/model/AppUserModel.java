package com.renan.entity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_user")
public class AppUserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_id_seq")
	@SequenceGenerator(name = "app_user_id_seq", sequenceName = "app_user_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false, updatable = false)
	private Integer id;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, length = 50)
	private String surname;

	@Column(nullable = false, length = 50)
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
}
