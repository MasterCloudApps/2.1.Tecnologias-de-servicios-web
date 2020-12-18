package es.codeurjc.functional;

import java.time.Duration;

import reactor.core.publisher.Flux;

public class ReactivePlayground7 {

    public static void main(String[] args) throws InterruptedException {

        Flux<String> data = Flux
            .just("re", "rea", "reac", "reactive")
            .delayElements(Duration.ofMillis(100))
            .switchMap(v ->
                Flux.just("Result "+v)
                    .delayElements(Duration.ofMillis(500))
            );

        data.subscribe(System.out::println);

        Thread.sleep(1000000);
    }
}
