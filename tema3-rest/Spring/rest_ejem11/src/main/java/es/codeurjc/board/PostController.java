package es.codeurjc.board;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.io.IOException;
import java.net.MalformedURLException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/posts")
public class PostController {

	private static final String POSTS_FOLDER = "posts";

	@Autowired
	private PostService posts;

	@Autowired
	private ImageService imgService;

	@GetMapping("/")
	public Collection<Post> getPosts() {
		return posts.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Post> getPost(@PathVariable long id) {

		Post post = posts.findById(id);

		if (post != null) {
			return ResponseEntity.ok(post);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/")
	public ResponseEntity<Post> createPost(@RequestBody Post post) {

		posts.save(post);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();

		return ResponseEntity.created(location).body(post);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Post> replacePost(@PathVariable long id, @RequestBody Post newPost) {

		Post post = posts.findById(id);

		if (post != null) {

			newPost.setId(id);
			posts.save(newPost);

			return ResponseEntity.ok(post);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Post> deletePost(@PathVariable long id) throws IOException {

		Post post = posts.findById(id);

		if (post != null) {
			posts.deleteById(id);
			
			if(post.getImage() != null) {
				this.imgService.deleteImage(POSTS_FOLDER, id);
			}
			
			return ResponseEntity.ok(post);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/{id}/image")
	public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageFile)
			throws IOException {

		Post post = posts.findById(id);

		if (post != null) {

			URI location = fromCurrentRequest().build().toUri();

			post.setImage(location.toString());
			posts.save(post);

			imgService.saveImage(POSTS_FOLDER, post.getId(), imageFile);

			return ResponseEntity.created(location).build();

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws MalformedURLException {

		return this.imgService.createResponseFromImage(POSTS_FOLDER, id);
	}
	
	@DeleteMapping("/{id}/image")
	public ResponseEntity<Object> deleteImage(@PathVariable long id) throws IOException {

		Post post = posts.findById(id);
		
		if(post != null) {
			
			post.setImage(null);
			posts.save(post);
			
			this.imgService.deleteImage(POSTS_FOLDER, id);
			
			return ResponseEntity.noContent().build();
			
		} else {
			return ResponseEntity.notFound().build();
		}		
	}
}
