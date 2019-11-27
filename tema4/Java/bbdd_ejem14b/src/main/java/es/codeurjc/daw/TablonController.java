package es.codeurjc.daw;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TablonController {
	
	//Si se abre la URL http://127.0.0.1:8080/h2-console y se configura
	//la URL JDBC con el valor jdbc:h2:mem:testdb se puede acceder a la 
	//base de datos de la aplicación

	@Autowired
	private AnunciosRepository repository;

	@PostConstruct
	public void init() {
		repository.save(new Anuncio("Pepe", "Hola caracola", "XXXX"));
		repository.save(new Anuncio("Juan", "Hola caracola", "XXXX"));
		repository.save(new Anuncio("Maria", "Vendo coche", "XXXX"));
		repository.save(new Anuncio("Laura", "Vendo moto", "XXXX"));
		repository.save(new Anuncio("Victor", "Vendo libro", "XXXX"));
		repository.save(new Anuncio("Paula", "Vendo PS4", "XXXX"));
		repository.save(new Anuncio("Alejandro", "Vendo lego", "XXXX"));
		repository.save(new Anuncio("Adrián", "Vendo dinosaurio", "XXXX"));
	}

	@GetMapping("/")
	@Transactional(readOnly = true)
	public String tablon(Model model, Pageable page) {

		try (Stream<Anuncio> anuncios = repository.streamAll(page)) {
			model.addAttribute("anuncios", anuncios.collect(Collectors.toList()));
		}

		return "tablon";
	}

	@GetMapping("/anuncio/nuevo")
	public String nuevoAnuncio(Model model, Anuncio anuncio) {

		repository.save(anuncio);

		return "anuncio_guardado";

	}

	@GetMapping("/anuncio/{id}")
	public String verAnuncio(Model model, @PathVariable long id) {
		
		Anuncio anuncio = repository.findById(id).get();

		model.addAttribute("anuncio", anuncio);

		return "ver_anuncio";
	}
}