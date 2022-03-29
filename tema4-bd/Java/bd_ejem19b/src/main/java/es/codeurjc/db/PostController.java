package es.codeurjc.db;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

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
	@JsonView(Post.Basic.class)
	public Page<Post> getPosts(@RequestParam(required = false) String user, Pageable page) {

		if (user != null) {
			return posts.findByUser(user, page);
		} else {
			return posts.findAll(page);
		}
	}
	
	@GetMapping("/{id}")
	@JsonView(Post.Basic.class)
	public ResponseEntity<Post> getPost(@PathVariable Long id) {

		Optional<Post> post = posts.findById(id);
		if (post.isPresent()) {
			return ResponseEntity.ok(post.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}