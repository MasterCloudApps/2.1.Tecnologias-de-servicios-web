package es.codeurjc.mastercloudapps.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	
	private int numData;

	@Scheduled(fixedRate = 1000)
	public void sendMessage() {
		
		String data = "Data " + numData++;
		
		System.out.println("publishToQueue: '" + data + "'");
				
		kafkaTemplate.send("messages", data);
	}
}
