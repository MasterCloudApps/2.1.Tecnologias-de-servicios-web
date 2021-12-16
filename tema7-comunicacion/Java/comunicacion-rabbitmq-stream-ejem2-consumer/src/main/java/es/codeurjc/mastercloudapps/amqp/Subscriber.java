package es.codeurjc.mastercloudapps.amqp;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import com.rabbitmq.stream.Consumer;
import com.rabbitmq.stream.Environment;
import com.rabbitmq.stream.Message;
import com.rabbitmq.stream.MessageHandler;
import com.rabbitmq.stream.OffsetSpecification;

@Component
public class Subscriber implements MessageHandler {

	private Environment environment;
	private String stream = "stream-data";
	private Consumer consumer;

	@PostConstruct
	public void init() {
		System.out.println("Connecting...");

		environment = Environment.builder().build();

		environment.streamCreator().stream(stream).create();

		consumer = environment.consumerBuilder()
				.stream(stream)
				.offset(OffsetSpecification.first())
				.messageHandler(this)
//				.messageHandler((offset, message) -> {
//					System.out.println(new String(message.getBodyAsBinary()));
//				})
				.build();
	}

	@PreDestroy
	public void close() {
		consumer.close();
		
		environment.close();
	}

	@Override
	public void handle(Context context, Message message) {

		System.out.println(new String(message.getBodyAsBinary()));
		
	}
	
}
