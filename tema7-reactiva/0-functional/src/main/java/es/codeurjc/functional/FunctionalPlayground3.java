package es.codeurjc.functional;

import reactor.core.publisher.Flux;

public class FunctionalPlayground3 {

    public static void main(String[] args) {

        Flux<Integer> numbers = Flux.generate(
            () -> 0,
            (state, sink) -> {
                sink.next(state);
                return state + 1;
            }
        );

        numbers.take(10).subscribe(System.out::println);
    }


}
