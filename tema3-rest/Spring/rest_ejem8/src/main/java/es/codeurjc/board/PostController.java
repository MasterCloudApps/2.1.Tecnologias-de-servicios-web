package es.codeurjc.board;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService posts;

	@Autowired
	private PostMapper mapper;

	@GetMapping("/")
	public Collection<PostDTO> getPosts() {
		return mapper.toDTOs(posts.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> getPost(@PathVariable long id) {

		Post post = posts.findById(id);

		if (post != null) {
			return ResponseEntity.ok(mapper.toDTO(post));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {

		Post post = mapper.toDomain(postDTO);

		posts.save(post);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();

		return ResponseEntity.created(location).body(mapper.toDTO(post));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostDTO> replacePost(@PathVariable long id, @RequestBody PostDTO newPostDTO) {

		Post post = posts.findById(id);

		if (post != null) {

			Post newPost = mapper.toDomain(newPostDTO);

			newPost.setId(id);
			posts.save(newPost);

			return ResponseEntity.ok(mapper.toDTO(newPost));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PostDTO> deletePost(@PathVariable long id) {

		Post post = posts.findById(id);

		if (post != null) {
			posts.deleteById(id);
			return ResponseEntity.ok(mapper.toDTO(post));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
