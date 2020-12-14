package es.codeurjc.functional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.time.Duration;

public class ReactivePlayground4 {

    public static void main(String[] args) throws InterruptedException {

        Flux<Integer> data = Flux
            .just(1,5,10)
            .flatMap(v -> Flux.range(v, 3));

        data.subscribe(System.out::println);
    }
}
