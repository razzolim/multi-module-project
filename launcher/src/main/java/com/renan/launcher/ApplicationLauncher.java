package com.renan.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
	"com.renan.webservice",
	"com.renan.service",
	"com.renan.publisher",
	"com.renan.consumer",
	"com.renan.repository",
	"com.renan.entity"
})
@EnableJpaRepositories(basePackages = "com.renan.repository")
@EntityScan(basePackages = "com.renan.entity")
public class ApplicationLauncher {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationLauncher.class, args);
	}
}
