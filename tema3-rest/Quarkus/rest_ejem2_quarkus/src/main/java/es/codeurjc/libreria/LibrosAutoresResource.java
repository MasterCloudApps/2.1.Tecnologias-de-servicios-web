package es.codeurjc.libreria;

import com.fasterxml.jackson.annotation.JsonView;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

@Path("/")
public class LibrosAutoresResource {

	private final List<Libro> libros = new ArrayList<>();
	private final List<Autor> autores = new ArrayList<>();

	@PostConstruct
	public void init() {

		Libro libro1 = new Libro(0, "Bambi", 3);
		Libro libro2 = new Libro(1, "Batman", 4);
		Libro libro3 = new Libro(2, "Spiderman", 2);

		libros.add(libro1);
		libros.add(libro2);
		libros.add(libro3);

		Autor autor1 = new Autor(0, "Antonio", "Español");
		Autor autor2 = new Autor(1, "Gerard", "Frances");

		autores.add(autor1);
		autores.add(autor2);

		libro1.getAutores().add(autor1);
		autor1.getLibros().add(libro1);

		libro2.getAutores().add(autor2);
		autor2.getLibros().add(libro2);

		libro3.getAutores().add(autor2);
		autor2.getLibros().add(libro3);

	}

	@JsonView(Libro.Basico.class)
	@GET
	@Path("/libros")
	public List<Libro> getLibros() {
		return libros;
	}

	@JsonView(Autor.Basico.class)
	@GET
	@Path("/autores")
	public List<Autor> getAutores() {
		return autores;
	}

	interface LibroDetalle extends Libro.Basico, Libro.Autores, Autor.Basico {
	}

	@JsonView(LibroDetalle.class)
	@GET
	@Path("/libros/{id}")
	public Libro getLibro(@PathParam("id") int id) {
		return libros.get(id);
	}

	interface AutorDetalle extends Autor.Basico, Autor.Libros, Libro.Basico {
	}

	@JsonView(AutorDetalle.class)
	@GET
	@Path("/autores/{id}")
	public Autor getAutor(@PathParam("id") int id) {
		return autores.get(id);
	}
}
