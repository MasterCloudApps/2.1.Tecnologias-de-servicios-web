package es.codeurjc.daw;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnuncioController {

	@GetMapping("/anuncio")
	public Anuncio anuncios() {

		return new Anuncio("Pepe", "Vendo moto", "...");
	}
}
