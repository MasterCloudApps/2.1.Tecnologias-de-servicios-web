package es.mastercloudapps.security_example;

import es.mastercloudapps.security_example.model.Role;
import es.mastercloudapps.security_example.model.User;
import es.mastercloudapps.security_example.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityExampleApplication.class, args);
	}



	@Bean
	CommandLineRunner run(UserService userService)
	{
		return args -> {
			userService.saveUser(new User(null, "Nico", "Nico", "password", new ArrayList<>()));
			userService.saveUser(new User(null, "Mica", "Mica", "password", new ArrayList<>()));
			userService.saveUser(new User(null, "Patxi", "Patxi", "password", new ArrayList<>()));
			userService.saveUser(new User(null, "Michel", "Michel", "password", new ArrayList<>()));

			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));

			userService.addRoleToUser("Nico", "ROLE_USER");
			userService.addRoleToUser("Mica", "ROLE_USER");
			userService.addRoleToUser("Patxi", "ROLE_USER");
			userService.addRoleToUser("Michel", "ROLE_USER");
			userService.addRoleToUser("Mica", "ROLE_ADMIN");
			userService.addRoleToUser("Patxi", "ROLE_ADMIN");
		};
	}
}
