package es.codeurjc.books;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class BooksController {

    record BooksResponse(List<Book> items) {
    }

    record Book(VolumeInfo volumeInfo) {
    }

    record VolumeInfo(String title) {
    }

    //Return type is not Flux<String> because:
    //  * https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/web-reactive.html#webflux-codecs-jackson
    //  * https://github.com/spring-projects/spring-framework/issues/20807

    @GetMapping("/booktitles")
    public Mono<List<String>> getBookTitles(@RequestParam String title) {

        String url = "https://www.googleapis.com/books/v1/volumes?q=intitle:" + title;

        Mono<BooksResponse> data = WebClient.create(url).get().retrieve().bodyToMono(BooksResponse.class);

        return data
            .flatMapMany(d -> Flux.fromIterable(d.items()))
            .map(book -> book.volumeInfo().title())
            .collectList();
    }
}
