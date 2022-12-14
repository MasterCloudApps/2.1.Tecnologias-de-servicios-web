package es.codeurjc.mastercloudapps;

import java.util.concurrent.ExecutionException;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.smallrye.reactive.messaging.annotations.Blocking;
import io.vertx.core.json.JsonObject;


@ApplicationScoped
public class Consumer {

    @Incoming("messages")
    @Blocking
    public void process(String message) throws InterruptedException, ExecutionException {
        System.out.println("Message:" + message); 
    }
}