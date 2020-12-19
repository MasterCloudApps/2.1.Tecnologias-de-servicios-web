package es.codeurjc.db;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Collection<Post> getPosts(@RequestParam(required = false) String user) {

		if (user != null) {
			return posts.findByUser(user);
		} else {
			return posts.findAll();
		}
	}

}