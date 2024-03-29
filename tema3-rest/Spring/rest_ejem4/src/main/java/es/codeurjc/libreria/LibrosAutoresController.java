package es.codeurjc.libreria;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibrosAutoresController {

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

	@GetMapping("/libros")
	public List<Libro> getLibros() {
		return libros;
	}

	@GetMapping("/autores")
	public List<Autor> getAutores() {
		return autores;
	}

	@GetMapping("/libros/{id}")
	public Libro getLibro(@PathVariable int id) {
		return libros.get(id);
	}

	@GetMapping("/autores/{id}")
	public Autor getAutores(@PathVariable int id) {
		return autores.get(id);
	}
}
