package es.codeurjc.functional;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Duration;

public class ReactivePlayground5 {

    public static void main(String[] args) throws InterruptedException {

        var data = Flux
            .just(Tuples.of(1, 300), Tuples.of(2, 200), Tuples.of(3, 100))
            .flatMap(v -> Flux.just(v.getT1())
                .delayElements(Duration.ofMillis(v.getT2())));

        data.subscribe(System.out::println);

        Thread.sleep(1000000);
    }
}
