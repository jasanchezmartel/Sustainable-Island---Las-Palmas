package com.sustainableisland.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
		"com.sustainableisland.backend",
		"com.sustainableisland.seazen"
})
@EnableJpaRepositories(basePackages = "com.sustainableisland.seazen.repositories")
@EntityScan(basePackages = "com.sustainableisland.seazen.models")
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
