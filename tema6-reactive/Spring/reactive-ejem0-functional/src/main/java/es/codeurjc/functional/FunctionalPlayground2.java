package es.codeurjc.functional;

import java.util.Optional;
import java.util.stream.Stream;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FunctionalPlayground2 {

    public static void main(String[] args) {

        Optional<String> name = Optional.of("Pepe");
        Mono<String> mono = Mono.justOrEmpty(name);

        Stream<Integer> stream = Stream.of(4,3,5);
        Flux<Integer> emptyFlux = Flux.fromStream(stream);

    }
}
