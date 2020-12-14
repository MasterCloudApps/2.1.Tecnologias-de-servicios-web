package es.codeurjc.functional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.util.Optional;
import java.util.stream.Stream;

public class FunctionalPlayground3 {

    public static void main(String[] args) {

        Flux numbers = Flux.generate(
            () -> 0,
            (state, sink) -> {
                sink.next(state);
                return state + 1;
            }
        );

        numbers.take(10).subscribe(System.out::println);
    }


}
