package es.codeurjc.daw;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnuncioController {

	@Autowired
	private AnuncioRepository repository;
	
	@PostConstruct
	public void init() {
		repository.save(new Anuncio("Pepe", "Hola...", "XXX"));
		repository.save(new Anuncio("Juan", "Adios...", "XXX"));
	}

	@GetMapping("/")
	public String tablon(Model model) {
		
		model.addAttribute("anuncios", repository.findAll());
		
		return "tablon";
	}
}
