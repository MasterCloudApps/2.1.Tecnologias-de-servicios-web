package es.codeurjc.functional;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class ReactiveTest {

    @Test
    public void test(){

        Flux<String> source = Flux.just("John", "Monica", "Mark", "Cloe",
            "Frank", "Casper", "Olivia", "Emily", "Cate")
            .filter(name -> name.length() == 4)
            .map(String::toUpperCase);

        StepVerifier
            .create(source)
            .expectNext("JOHN")
            .expectNextMatches(name -> name.startsWith("MA"))
            .expectNext("CLOE", "CATE")
            .expectComplete()
            .verify();

    }
}
