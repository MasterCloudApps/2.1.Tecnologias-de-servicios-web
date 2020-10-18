package es.codeurjc.daw;

import java.util.ArrayList;
import java.util.List;

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
	public List<String> getBookTitles(@RequestParam String title) {

		RestTemplate restTemplate = new RestTemplate();

		String url = "https://www.googleapis.com/books/v1/volumes?q=intitle:" + title;

		BooksResponse data = restTemplate.getForObject(url, BooksResponse.class);

		List<String> bookTitles = new ArrayList<String>();

		for (Book book : data.items) {
			bookTitles.add(book.volumeInfo.title);
		}

		return bookTitles;
	}
}
