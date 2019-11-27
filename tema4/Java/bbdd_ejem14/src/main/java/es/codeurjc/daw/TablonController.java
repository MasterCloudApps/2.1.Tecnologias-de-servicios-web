package es.codeurjc.daw;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TablonController {

	@Autowired
	private AnuncioRepository repository;

	@PostConstruct
	public void init() {
		repository.save(new Anuncio("Pepe", "Hola caracola", "XXXX"));
		repository.save(new Anuncio("Juan", "Hola caracola", "XXXX"));
	}

	@GetMapping("/")
	public Page<Anuncio> tablon(Pageable page) {
		return repository.findAll(page);
	}

	@GetMapping("/pepe")
	public Page<Anuncio> tablonPorNombre() {
		Page<Anuncio> pages = repository.findByNombre("pepe", PageRequest.of(0, 50));
		return pages;
	}

	@GetMapping("/anuncio/{id}")
	public Anuncio verAnuncio(@PathVariable long id) {
		return repository.findById(id).get();
	}

	@PostMapping("/anuncio/nuevo")
	public Anuncio nuevoAnuncio(Anuncio anuncio) {
		repository.save(anuncio);
		return anuncio;
	}

}