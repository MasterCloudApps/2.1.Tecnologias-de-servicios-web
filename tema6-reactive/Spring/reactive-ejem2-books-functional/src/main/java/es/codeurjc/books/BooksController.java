package es.codeurjc.books;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    public Stream<BookDTO> getBookTitles(@RequestParam String title) {

        String url = "https://www.googleapis.com/books/v1/volumes?q=intitle:" + title;

        BooksResponse data = new RestTemplate().getForObject(url, BooksResponse.class);

        return data.items().stream()
            .map(book -> new BookDTO(book.volumeInfo().title()));
    }
}
