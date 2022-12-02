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

	@GetMapping("/")
	public Collection<PostDTO> getPosts() {
		return posts.findAll().stream().map(this::toDTO).toList();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> getPost(@PathVariable long id) {

		Post post = posts.findById(id);

		if (post != null) {
			return ResponseEntity.ok(toDTO(post));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {

		Post post = toDomain(postDTO);

		posts.save(post);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();

		return ResponseEntity.created(location).body(toDTO(post));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostDTO> replacePost(@PathVariable long id, @RequestBody PostDTO newPostDTO) {

		Post post = posts.findById(id);

		if (post != null) {

			Post newPost = toDomain(newPostDTO);

			newPost.setId(id);
			posts.save(newPost);

			return ResponseEntity.ok(toDTO(newPost));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PostDTO> deletePost(@PathVariable long id) {

		Post post = posts.findById(id);

		if (post != null) {
			posts.deleteById(id);
			return ResponseEntity.ok(toDTO(post));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	private PostDTO toDTO(Post post){
		return new PostDTO(post.getId(), post.getUser(), post.getTitle(), post.getText());
	}

	private Post toDomain(PostDTO postDTO){
		return new Post(postDTO.id(), postDTO.user(), postDTO.title(), postDTO.text());
	}
}
