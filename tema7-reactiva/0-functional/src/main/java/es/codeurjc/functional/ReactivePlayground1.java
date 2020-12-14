package es.codeurjc.functional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ReactivePlayground1 {

    public static void main(String[] args) throws InterruptedException {

       Flux<Long> nums = Flux.interval(Duration.ofMillis(500));

       nums
           .elapsed()
           .subscribe(System.out::println);

       Thread.sleep(10000000);

    }


}
