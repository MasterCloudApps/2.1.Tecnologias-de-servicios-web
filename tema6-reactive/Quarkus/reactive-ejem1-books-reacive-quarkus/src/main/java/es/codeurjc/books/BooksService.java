package es.codeurjc.books;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("/books/v1")
@RegisterRestClient
public interface BooksService {

	public record BooksResponse(List<Book> items) {
	}

	public record Book(VolumeInfo volumeInfo) {
	}

	public record VolumeInfo(String title) {
	}

	@GET
	@Path("volumes")
	Uni<BooksResponse> getBooks(@QueryParam("q") String q);

}
