package es.codeurjc.mastercloudapps;

import java.util.concurrent.ExecutionException;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import io.quarkus.scheduler.Scheduled;
import io.smallrye.mutiny.Multi;

@ApplicationScoped
public class Producer {

    @Channel("messages")
    Emitter<String> messages;

    private int numData;

    @Scheduled(every="1s")
    public void produce() throws InterruptedException, ExecutionException {
        String data = "Data: " + numData++;
        messages.send(data);
        System.out.println(data); 
    }

}