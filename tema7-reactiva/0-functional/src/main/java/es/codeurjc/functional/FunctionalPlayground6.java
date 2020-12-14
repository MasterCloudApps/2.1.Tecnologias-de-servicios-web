package es.codeurjc.functional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FunctionalPlayground6 {

    public static void main(String[] args) {

        Flux<Integer> numbers = Flux.range(5, 3);
        numbers.subscribe(System.out::println);
        //5 6 7

        IntStream numbers2 = IntStream.range(5, 8);
        numbers2.forEach(System.out::println);
        //5 6 7

        System.out.println("--------------");

        Flux pairs = Flux.generate(
            () -> 0,
            (state, sink) -> {
                sink.next(state);
                return state + 1;
            }
        );

        pairs
            .take(10)
            .subscribe(System.out::println);
        // 0,1,2,3,4,5...

        System.out.println("--------------");

        Stream<Integer> pairs2 = Stream.iterate(0, state -> state+1);

        pairs2
            .limit(10)
            .forEach(System.out::println);

        System.out.println("--------------");
    }


}
