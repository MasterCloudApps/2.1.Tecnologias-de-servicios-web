package es.codeurjc.books;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

    @GetMapping("/booktitles")
    public Stream<String> getBookTitles(@RequestParam String title) {

        String url = "https://www.googleapis.com/books/v1/volumes?q=intitle:" + title;

        BooksResponse data = new RestTemplate().getForObject(url, BooksResponse.class);

        return data.items.stream()
            .map(book -> book.volumeInfo.title);
    }
}
