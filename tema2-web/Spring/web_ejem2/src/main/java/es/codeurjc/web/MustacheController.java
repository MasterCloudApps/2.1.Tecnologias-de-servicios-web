package es.codeurjc.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MustacheController {
	
	@GetMapping("/page")
	public String page(Model model) {
		return "page";
	}
	
	@GetMapping("/basic")
	public String basic(Model model) {

		model.addAttribute("name", "World");
		model.addAttribute("hello", true);

		return "basic_template";
	}

	@GetMapping("/list")
	public String iteration(Model model) {

		List<String> colors = Arrays.asList("Red", "Blue", "Green");

		model.addAttribute("colors", colors);

		return "list_template";
	}

	@GetMapping("/list_objects")
	public String iterationObj(Model model) {

		List<Person> people = new ArrayList<>();
		people.add(new Person("Pepe","Pérez"));
		people.add(new Person("Juan","González"));
		people.add(new Person("Ramón","Lucas"));

		model.addAttribute("people", people);

		return "list_obj_template";
	}

}
