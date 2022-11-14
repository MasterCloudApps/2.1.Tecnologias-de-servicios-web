package es.codeurjc.mastercloudapps.amqp;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.pivotal.rabbitmq.topology.TopologyBuilder;
import com.pivotal.rabbitmq.RabbitEndpointService;
import com.pivotal.rabbitmq.stream.ConsumerStream;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
    RabbitEndpointService rabbit;

	String NUMBERS = "numbers";
    String CONSUMER = "numbers";
    
	@Bean
    public Consumer<TopologyBuilder> topology() {																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																											q
        return (builder) -> builder
                .declareExchange(NUMBERS)
                .and()
                .declareQueue(CONSUMER).boundTo(NUMBERS).withMaxLength(5);
    }
	
	@Bean
    public CommandLineRunner consumer(Consumer<TopologyBuilder> topology) {
        return (args) -> {
            ConsumerStream<Integer> consumerStream = rabbit
                    .declareTopology(topology)
                    .createConsumerStream(consumerQueue, Integer.class);

            consumerStream
                    .receive()
                    .doOnNext(number -> log.info("Received: {}", number))
                    .subscribe();
        };
    }
	
    String consumerQueue = "numbers";

    private static Logger log = LoggerFactory.getLogger(Application.class);

}
