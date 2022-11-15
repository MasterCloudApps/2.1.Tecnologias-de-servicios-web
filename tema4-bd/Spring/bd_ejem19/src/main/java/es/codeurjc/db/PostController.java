package es.codeurjc.db;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostRepository posts;

	@PostConstruct
	public void init() {
		posts.save(new Post("Pepe", "Hola caracola", "XXXX"));
		posts.save(new Post("Juan", "Hola caracola", "XXXX"));
	}

	@GetMapping("/")
	public Page<Post> getPosts(@RequestParam(required = false) String user, Pageable page) {

		if (user != null) {
			return posts.findByUsername(user, page);
		} else {
			return posts.findAll(page);
		}
	}

}