package es.codeurjc.functional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Stream;

public class FunctionalPlayground5 {

    public static void main(String[] args) {

        Mono<String> name = Mono.just("Pepe");
        System.out.println(name.block());

        Flux<String> numbers = Flux.just("one","two","three");
        Mono<List<String>> numList = numbers.collectList();
        System.out.println(numList.block());

        Flux<Integer> nums3 = Flux.just(4,5,6,7,8);
        System.out.println(nums3.collectList().block());

    }


}
