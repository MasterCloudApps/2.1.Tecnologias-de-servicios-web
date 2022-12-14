package es.codeurjc.mastercloudapps;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;


@ApplicationScoped
public class Consumer {

    @Incoming("messages")
    public void process(String message) throws InterruptedException {
        System.out.println(message); 
    }
}