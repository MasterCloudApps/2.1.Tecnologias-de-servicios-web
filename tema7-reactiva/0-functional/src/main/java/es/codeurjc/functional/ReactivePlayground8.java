package es.codeurjc.functional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ReactivePlayground8 {

    public static void main(String[] args) throws InterruptedException {

        Flux<Integer> valuesWithError = Flux.just(1, 2, 3)
            .flatMap(v -> sumService(v));

        Flux<Integer> values = valuesWithError
            .onErrorResume(IllegalArgumentException.class, e -> Flux.just(4, 5, 6));

        values.subscribe(System.out::println);

        // 1, 2, 4, 5, 6

        Thread.sleep(10000);
    }

    private static Mono<Integer> sumService(Integer v) {

        if (v != 3) {
            return Mono.just(v+1);
        } else {
            return Mono.error(new IllegalArgumentException());
        }
    }

}
