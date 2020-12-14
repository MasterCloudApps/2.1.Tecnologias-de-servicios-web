package es.codeurjc.books;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static java.util.stream.Collectors.toList;

class BooksResponse {
    public List<Book> items;
}

class Book {
    public VolumeInfo volumeInfo;
}

class VolumeInfo {
    public String title;
}

@RestController
public class BooksController {

    //Return type is not Flux<String> because:
    //  * https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/web-reactive.html#webflux-codecs-jackson
    //  * https://github.com/spring-projects/spring-framework/issues/20807

    @GetMapping("/booktitles")
    public Mono<List<String>> getBookTitles(@RequestParam String title) {

        String url = "https://www.googleapis.com/books/v1/volumes?q=intitle:" + title;

        Mono<BooksResponse> data = WebClient.create(url).get().retrieve().bodyToMono(BooksResponse.class);

        return data
            .flatMapMany(d -> Flux.fromIterable(d.items))
            .map(book -> book.volumeInfo.title)
            .collectList();
    }
}
