package es.codeurjc.mastercloudapps.amqp;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rabbitmq.stream.Environment;
import com.rabbitmq.stream.Message;
import com.rabbitmq.stream.Producer;

@Component
public class Publisher {

	private int numData;
	private Environment environment;
	private String stream = "stream-data";
	private Producer producer;

	@PostConstruct
	public void init() {
		System.out.println("Connecting...");
		
		environment = Environment.builder()
				.build();  
		
		environment.streamCreator().stream(stream).create();
		
		producer = environment.producerBuilder()  
		        .stream(stream)
		        .build();
	}
	
	@Scheduled(fixedRate = 1000)
	public void sendMessage() {
		
		String data = "Data " + numData++;
		
		System.out.println("publishToQueue: '" + data + "'");
				
		Message message = producer.messageBuilder()
			.addData(data.getBytes())
			.build();
		
		producer.send(message, confirmationStatus -> {
			if(confirmationStatus.isConfirmed()) {
				System.out.println("Message sent!");
			} else {
				System.out.println("Ooops, something went wrong!");
			}
		});

	}
	
	@PreDestroy
	public void close() {
		producer.close();
		
		environment.close();
	}
}
