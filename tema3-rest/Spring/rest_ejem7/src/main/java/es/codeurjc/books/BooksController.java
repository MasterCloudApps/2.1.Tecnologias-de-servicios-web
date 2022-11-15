package es.codeurjc.books;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;



@RestController
public class BooksController {

	record BooksResponse(List<Book> items) {
	}

	record Book(VolumeInfo volumeInfo) {
	}

	record VolumeInfo(String title) {
	}

    @GetMapping("/booktitles")
    public List<String> getBookTitles(@RequestParam String title) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://www.googleapis.com/books/v1/volumes?q=intitle:" + title;

        BooksResponse data = restTemplate.getForObject(url, BooksResponse.class);

        List<String> bookTitles = new ArrayList<>();

        for (Book book : data.items()) {
            bookTitles.add(book.volumeInfo().title());
        }

        return bookTitles;
    }
}
