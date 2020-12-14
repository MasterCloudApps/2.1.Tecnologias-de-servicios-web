package es.codeurjc.functional;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class ReactiveTest2 {

    @Test
    public void test(){

        final TestPublisher<String> testPublisher = TestPublisher.create();

        Flux<String> result = testPublisher.flux()
            .map(String::toUpperCase);

        StepVerifier.create(result)
            .then(() -> testPublisher.emit("aA", "bb", "ccc"))
            .expectNext("AA", "BB", "CCC")
            .verifyComplete();
    }
}
