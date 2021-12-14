package es.codeurjc.mastercloudapps.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@RabbitListener(id = "receiver1", queues = "messages", ackMode = "AUTO")
	public void received1(String message) {
		
		System.out.println("Receiver 1 Message: "+message);
	}

	@RabbitListener(id = "receiver2", queues = "messages", ackMode = "AUTO")
	public void received2(String message) {
		
		System.out.println("Receiver 2 Message: "+message);
	}
}
