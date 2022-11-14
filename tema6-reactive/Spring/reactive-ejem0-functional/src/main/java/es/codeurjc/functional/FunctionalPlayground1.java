package es.codeurjc.functional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

public class FunctionalPlayground1 {

    public static void main(String[] args) {

        Stream<String> n = Stream.of("one","two","three");
        Flux<String> n2 = Flux.just("one","two","three");
        Mono<String> number = Mono.just("one");

        Stream<String> emptyStream = Stream.empty();
        Flux<String> emptyFlux = Flux.empty();
        Mono<String> emptyMono = Mono.empty();

    }


}
