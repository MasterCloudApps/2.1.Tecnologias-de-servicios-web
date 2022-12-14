package es.codeurjc.mastercloudapps;

import java.util.concurrent.ExecutionException;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import io.quarkus.scheduler.Scheduled;

@ApplicationScoped
public class Producer {

    @Channel("messages")
    Emitter<String> emitter;

    private int numData;

    @Scheduled(every="1s")
    public void produce() throws InterruptedException, ExecutionException {
        String data = "Data " + numData++;
		
		System.out.println("publishToQueue: '" + data + "'");
		
        emitter.send(data);
    }

}