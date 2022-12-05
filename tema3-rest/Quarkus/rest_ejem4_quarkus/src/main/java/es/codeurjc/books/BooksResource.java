package es.codeurjc.books;

import es.codeurjc.books.BooksService.Book;
import es.codeurjc.books.BooksService.BooksResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;

@Path("/booktitles")
public class BooksResource {

	@RestClient
	private BooksService service;

	@GET
	public List<String> getBookTitles(@QueryParam("title") String title) {

		BooksResponse data = service.getBooks("intitle:" + title);

		List<String> bookTitles = new ArrayList<>();
		for (Book book : data.items()) {
			bookTitles.add(book.volumeInfo().title());
		}

		return bookTitles;
	}
}
