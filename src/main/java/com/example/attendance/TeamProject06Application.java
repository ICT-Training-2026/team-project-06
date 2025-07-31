package com.example.attendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EntityScan("com.example.attendance.entity")
public class TeamProject06Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder
	configure(SpringApplicationBuilder application) {
		return application.sources(TeamProject06Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(TeamProject06Application.class, args);
	}
}
