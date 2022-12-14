package es.codeurjc.books;

import es.codeurjc.books.BooksService.Book;
import es.codeurjc.books.BooksService.BooksResponse;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;

@Path("/booktitles")
public class BooksResource {

	record BookDTO(String title) { }

	@RestClient
	private BooksService service;

	@GET
	public Multi<BookDTO> getBookTitles(@QueryParam("title") String title) {

		Uni<BooksResponse> data = service.getBooks("intitle:" + title);

		return data
				.onItem().transformToMulti(d -> Multi.createFrom().items(d.items().stream()))
				.onItem().transform(book -> new BookDTO(book.volumeInfo().title()));
	}
}
