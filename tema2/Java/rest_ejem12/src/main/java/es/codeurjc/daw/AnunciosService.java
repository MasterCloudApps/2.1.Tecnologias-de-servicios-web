package es.codeurjc.daw;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "anuncios", url = "http://localhost:8080")
public interface AnunciosService {
	
	@GetMapping("/anuncios/")
	List<Anuncio> getAnuncios();
	
	@PostMapping("/anuncios/")
	Anuncio postAnuncio(Anuncio anuncio);
	
	@PutMapping("/anuncios/{id}")
	Anuncio putAnuncioById(@PathVariable("id") Long id);
	
	@DeleteMapping("/anuncios/{id}")
	Anuncio deleteAnuncioById(@PathVariable("id") Long id);
	
	@GetMapping("/anuncios/{id}")
	Anuncio getAnuncioById(@PathVariable("id") Long id);

}
