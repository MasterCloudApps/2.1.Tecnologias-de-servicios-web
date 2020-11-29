package es.codeurjc.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	@Bean
	public UserService usersService() {
		return new UserService(10);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
