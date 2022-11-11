package es.codeurjc.mastercloudapps.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.pivotal.rabbitmq.RabbitEndpointService;
import com.pivotal.rabbitmq.stream.ProducerStream;
import com.pivotal.rabbitmq.topology.TopologyBuilder;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Consumer;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
    RabbitEndpointService rabbit;
	
    private static Logger log = LoggerFactory.getLogger(Application.class);
	
    String NUMBERS = "numbers";
    String CONSUMER = "numbers";
    
	@Bean
    public Consumer<TopologyBuilder> topology() {
        return (builder) -> builder
                .declareExchange(NUMBERS)
                .and()
                .declareQueue(CONSUMER).boundTo(NUMBERS).withMaxLength(5);
    }
	
    int count = 10;
    Duration delay = Duration.ofSeconds(1);
    
	@Bean
    public CommandLineRunner publisher(Consumer<TopologyBuilder> topology) {
        return (args) -> {
            Flux<Integer> integers = Flux
                    .range(1, count)
                    .delayElements(delay)
                    .doOnNext(data -> log.debug("Sending: {}", data));

            ProducerStream<Integer> producerStream = rabbit
                    .declareTopology(topology)
                    .createProducerStream(Integer.class)
                    .route()
                        .toExchange(NUMBERS)
                    .then();

            producerStream
                    .send(integers)
                    .doOnNext(data -> log.debug("Sent: {}", data))
                    .blockLast();
        };
    }

}
