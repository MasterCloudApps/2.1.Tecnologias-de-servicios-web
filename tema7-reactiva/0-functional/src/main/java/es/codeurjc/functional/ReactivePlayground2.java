package es.codeurjc.functional;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class ReactivePlayground2 {

    public static void main(String[] args) throws InterruptedException {

        Flux<String> letters= Flux.just("a", "b", "c");
        Flux<Integer> numbers = Flux.just(1, 2, 3);

        Flux<Integer> finalNumbers = letters.thenMany(numbers);

        finalNumbers.subscribe(System.out::println);

    }
}
