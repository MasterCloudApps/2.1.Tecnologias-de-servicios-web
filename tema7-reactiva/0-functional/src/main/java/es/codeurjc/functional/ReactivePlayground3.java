package es.codeurjc.functional;

import reactor.core.publisher.Flux;

public class ReactivePlayground3 {

    public static void main(String[] args) throws InterruptedException {

        Flux<String> letters= Flux.just("a", "b", "c")
            .doOnNext(l -> System.out.print(" l"+l));

        Flux<Integer> numbers = Flux.just(1, 2, 3)
            .doOnNext(n -> System.out.print(" n"+n));

        Flux<Integer> finalNumbers = letters.thenMany(numbers);

        finalNumbers.subscribe(f -> System.out.print(" f"+f));

    }
}
