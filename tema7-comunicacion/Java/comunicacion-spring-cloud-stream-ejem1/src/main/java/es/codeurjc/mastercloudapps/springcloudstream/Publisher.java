package es.codeurjc.mastercloudapps.springcloudstream;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class Publisher {

    private final Source source;

    public Publisher(Source source) {
        this.source = source;
    }

    @Scheduled(fixedRate = 2, timeUnit = TimeUnit.SECONDS)
    public void publish() {
    	System.out.println("Sending event...");
        source.output().send(new GenericMessage<>(new Client(UUID.randomUUID().toString(), UUID.randomUUID().toString())));
    }
    
}
