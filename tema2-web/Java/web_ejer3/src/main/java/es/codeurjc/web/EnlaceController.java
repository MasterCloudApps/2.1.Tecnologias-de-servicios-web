package es.codeurjc.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EnlaceController {

	@GetMapping("/enlace/{num}")
	public String enlace(Model model, @PathVariable String num) {

		model.addAttribute("num", num);

		return "enlace";
	}
}