package es.codeurjc.functional;

import java.time.Duration;

import reactor.core.publisher.Flux;

public class ReactivePlayground1 {

    public static void main(String[] args) throws InterruptedException {

       Flux<Long> nums = Flux.interval(Duration.ofMillis(500));

       nums
           .elapsed()
           .subscribe(System.out::println);

       Thread.sleep(10000000);

    }


}
