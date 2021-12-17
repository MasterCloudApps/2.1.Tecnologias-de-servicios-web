package es.codeurjc.mastercloudapps.amqp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Queue myQueue() {
		Map<String, Object> args = new HashMap<>();
        args.put("x-queue-type", "stream");
    	return new Queue("messages", true, false, false, args);
	}
}
