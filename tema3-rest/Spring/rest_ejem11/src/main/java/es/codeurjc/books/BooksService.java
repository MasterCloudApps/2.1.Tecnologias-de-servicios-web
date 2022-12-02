package es.codeurjc.books;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "books", url = "https://www.googleapis.com/")
public interface BooksService {

	public record BooksResponse(List<Book> items) {
	}

	public record Book(VolumeInfo volumeInfo) {
	}

	public record VolumeInfo(String title) {
	}

	@GetMapping("books/v1/volumes")
	BooksResponse getBooks(@RequestParam String q);

}
