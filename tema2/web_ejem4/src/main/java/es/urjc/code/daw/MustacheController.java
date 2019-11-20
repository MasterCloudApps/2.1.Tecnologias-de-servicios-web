package es.urjc.code.daw;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MustacheController {

	@GetMapping("/basic")
	public String basic(Model model) {

		model.addAttribute("name", "World");
		model.addAttribute("silent", true);

		return "basic_template";
	}

	@GetMapping("/list")
	public String iteration(Model model) {

		List<String> colors = Arrays.asList("Red", "Blue", "Green");

		model.addAttribute("colors", colors);

		return "list_template";
	}

}
