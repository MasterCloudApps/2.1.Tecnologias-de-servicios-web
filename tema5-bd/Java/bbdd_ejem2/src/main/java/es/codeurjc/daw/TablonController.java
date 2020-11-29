package es.codeurjc.daw;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TablonController {

	@Autowired
	private AnuncioRepository repository;

	@PostConstruct
	public void init() {
		repository.save(new Anuncio("Pepe", "Hola caracola", "XXXX"));
		repository.save(new Anuncio("Juan", "Hola caracola2", "XXXX2"));
	}

	@GetMapping("/anuncios")
	public List<Anuncio> tablon() {
		return repository.findAll();
	}

	@GetMapping("/anuncio/{id}")
	public ResponseEntity<Anuncio> verAnuncio(@PathVariable long id) {
		Optional<Anuncio> op = repository.findById(id);
		if (op.isPresent()) {
			Anuncio anuncio = op.get();
			return new ResponseEntity<>(anuncio, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Anuncio createBook(@RequestBody Anuncio anuncio) {
		repository.save(anuncio);
		return anuncio;
	}

}