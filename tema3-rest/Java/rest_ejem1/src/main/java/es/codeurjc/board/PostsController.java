package es.codeurjc.board;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostsController {

	@GetMapping("/posts/1")
	public Post getPost() {

		return new Post("Pepe", "Vendo moto", "...");
	}
}
