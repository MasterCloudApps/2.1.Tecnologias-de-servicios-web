package es.codeurjc.mastercloudapps.springcloudstream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableBinding({Source.class, Sink.class})
public class ComunicacionSpringCloudStreamEjem1Application {

	public static void main(String[] args) {
		SpringApplication.run(ComunicacionSpringCloudStreamEjem1Application.class, args);
	}

}
