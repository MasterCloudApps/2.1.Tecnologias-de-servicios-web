package es.codeurjc.daw;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/anuncios")
public class AnunciosController {

	private Map<Long, Anuncio> anuncios = new ConcurrentHashMap<>();
	private AtomicLong lastId = new AtomicLong();
	
	public AnunciosController() {
		Anuncio a1 = new Anuncio("Pepe", "Hola caracola", "XXXX");
		a1.setId(1);
		Anuncio a2 = new Anuncio("Juan", "Hola caracola", "XXXX");
		a2.setId(2);
		anuncios.put(a1.getId(),a1);
		anuncios.put(a2.getId(),a2);
	}

	@GetMapping("/")
	public Collection<Anuncio> anuncios() {
		return anuncios.values();
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Anuncio nuevoAnuncio(@RequestBody Anuncio anuncio) {

		long id = lastId.incrementAndGet();
		anuncio.setId(id);
		anuncios.put(id, anuncio);

		return anuncio;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Anuncio> actulizaAnuncio(@PathVariable long id, 
			@RequestBody Anuncio anuncioActualizado) {

		Anuncio anuncio = anuncios.get(id);

		if (anuncio != null) {

			anuncioActualizado.setId(id);
			anuncios.put(id, anuncioActualizado);

			return new ResponseEntity<>(anuncioActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Anuncio> getAnuncio(@PathVariable long id) {

		Anuncio anuncio = anuncios.get(id);

		if (anuncio != null) {
			return new ResponseEntity<>(anuncio, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Anuncio> borraAnuncio(@PathVariable long id) {

		Anuncio anuncio = anuncios.remove(id);

		if (anuncio != null) {
			return new ResponseEntity<>(anuncio, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
