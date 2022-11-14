package es.codeurjc.mastercloudapps.springcloudstream;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ComunicacionSpringCloudStreamEjem1Application {

	public static void main(String[] args) {
		SpringApplication.run(ComunicacionSpringCloudStreamEjem1Application.class, args);
	}

	@Bean
    public Supplier<Client> producer() {
    	return () -> {
    		return new Client(UUID.randomUUID().toString(), UUID.randomUUID().toString());
    	};
    }
	
	@Bean
	public Consumer<Client> consumer() {
		return client -> {
			System.out.println("Client: " + client);
		};
	}
}
