package es.codeurjc.daw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AnunciosController {

	@Autowired
	AnunciosService service;
	
	@GetMapping("/")
	public String tablon(Model model) {

		model.addAttribute("anuncios", service.getAnuncios());

		return "tablon";
	}

	@PostMapping("/anuncio/nuevo")
	public String nuevoAnuncio(Model model, Anuncio anuncio) {
		
		service.postAnuncio(anuncio);

		return "anuncio_guardado";

	}

	@GetMapping("/anuncio/{num}")
	public String verAnuncio(Model model, @PathVariable int num) {

		Anuncio anuncio = service.getAnuncioById((long) num);

		model.addAttribute("anuncio", anuncio);

		return "ver_anuncio";
	}

}
