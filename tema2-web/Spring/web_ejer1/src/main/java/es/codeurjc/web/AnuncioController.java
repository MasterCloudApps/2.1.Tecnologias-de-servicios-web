
package es.codeurjc.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnuncioController {

	@PostMapping("/guardaranuncio")
	public String guardarAnuncio(Model model, @RequestParam String nombre, @RequestParam String asunto,
			@RequestParam String comentario) {

		model.addAttribute("nombre", nombre);
		model.addAttribute("asunto", asunto);
		model.addAttribute("comentario", comentario);

		return "anuncio";
	}
}