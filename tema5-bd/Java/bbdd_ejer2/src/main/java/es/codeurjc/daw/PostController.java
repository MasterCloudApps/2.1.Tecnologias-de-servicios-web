package es.codeurjc.daw;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

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

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostRepository posts;

	@Autowired
	private CommentRepository comments;

	@PostConstruct
	public void init() {

		Post p = new Post();
		p.setName("Pepe");
		p.setNickname("Pepito");
		p.setContent("Bla bla...");
		p.setComments(List.<Comment>of(new Comment("Juan", "Pues si", new Date()),
				new Comment("Maria", "Pues no", new Date())));

		posts.save(p);
	}

	@GetMapping("/")
	public List<Post> getPosts() {
		return posts.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Post> getPost(@PathVariable long id) {

		Optional<Post> post = posts.findById(id);

		return ResponseEntity.of(post);
	}

	@PostMapping("/")
	public ResponseEntity<Post> createPost(@RequestBody Post post) {

		posts.save(post);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();

		return ResponseEntity.created(location).body(post);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Post> replacePost(@RequestBody Post newPost, @PathVariable long id) {

		Optional<Post> post = posts.findById(id);

		return ResponseEntity.of(post.map(p -> {

			newPost.setId(id);
			posts.save(newPost);

			return newPost;
		}));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Post> deletePost(@PathVariable long id) {

		Optional<Post> post = posts.findById(id);

		post.ifPresent(i -> posts.deleteById(id));

		return ResponseEntity.of(post);
	}

	@PostMapping("/{idPost}/comments/")
	public ResponseEntity<Comment> addComment(@PathVariable long idPost, @RequestBody Comment comment) {

		Optional<Post> post = posts.findById(idPost);

		if (post.isPresent()) {

			comments.save(comment);

			URI location = fromCurrentRequest().path("/{id}").buildAndExpand(comment.getId()).toUri();

			post.get().getComments().add(comment);
			posts.save(post.get());

			return ResponseEntity.created(location).body(comment);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{idPost}/comments/{idComment}")
	public ResponseEntity<Comment> getComment(@PathVariable long idPost, @PathVariable long idComment) {

		Optional<Comment> comment = comments.findById(idComment);

		return ResponseEntity.of(comment);
	}


	@DeleteMapping("/{idPost}/comments/{idComment}")
	public ResponseEntity<Comment> deleteComment(@PathVariable long idPost, @PathVariable long idComment) {

		Optional<Post> post = posts.findById(idPost);

		return ResponseEntity.of(post.map(p -> {

			Comment deletedComment = null;

			for (var it = p.getComments().iterator(); it.hasNext();) {

				Comment c = it.next();
				if (c.getId() == idComment) {
					deletedComment = c;
					it.remove();
					break;
				}
			}

			posts.save(p);

			return deletedComment;
		}));
	}

}
