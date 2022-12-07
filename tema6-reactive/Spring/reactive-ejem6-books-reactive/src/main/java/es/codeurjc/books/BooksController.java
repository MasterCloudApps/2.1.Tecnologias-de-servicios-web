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

    record BookDTO(String title) { }

    @GetMapping("/booktitles")
    public Flux<BookDTO> getBookTitles(@RequestParam String title) {

        String url = "https://www.googleapis.com/books/v1/volumes?q=intitle:" + title;

        Mono<BooksResponse> data = WebClient.create(url).get().retrieve().bodyToMono(BooksResponse.class);

        return data
            .flatMapMany(d -> Flux.fromIterable(d.items()))
            .map(book -> new BookDTO(book.volumeInfo().title()));
    }
}
