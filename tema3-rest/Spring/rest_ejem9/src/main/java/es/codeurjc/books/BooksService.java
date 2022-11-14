package es.codeurjc.books;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "books", url = "https://www.googleapis.com/")
public interface BooksService {

	@GetMapping("books/v1/volumes")
	BooksResponse getBooks(@RequestParam String q);

}
