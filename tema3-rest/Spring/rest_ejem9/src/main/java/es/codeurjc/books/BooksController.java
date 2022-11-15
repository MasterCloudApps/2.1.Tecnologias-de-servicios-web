package es.codeurjc.books;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.books.BooksService.BooksResponse;
import es.codeurjc.books.BooksService.Book;

@RestController
public class BooksController {

	@Autowired
	private BooksService service;

	@GetMapping("/booktitles")
	public List<String> getBookTitles(@RequestParam String title) {

		BooksResponse data = service.getBooks("intitle:" + title);

		List<String> bookTitles = new ArrayList<String>();
		for (Book book : data.items()) {
			bookTitles.add(book.volumeInfo().title());
		}

		return bookTitles;
	}
}
