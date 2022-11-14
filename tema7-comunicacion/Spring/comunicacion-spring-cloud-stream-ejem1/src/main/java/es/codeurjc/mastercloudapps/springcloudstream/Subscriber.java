package es.codeurjc.mastercloudapps.springcloudstream;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
public class Subscriber {

	@StreamListener(Sink.INPUT)
	public void handle(Client client) {
		System.out.println("Event received: " + client);
	}
	
}
