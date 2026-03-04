package com.sustainableisland.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.sustainableisland")
@EnableJpaRepositories(basePackages = "com.sustainableisland")
@EntityScan(basePackages = "com.sustainableisland")
public class SeaZen {

	public static void main(String[] args) {
		SpringApplication.run(SeaZen.class, args);
	}

}
