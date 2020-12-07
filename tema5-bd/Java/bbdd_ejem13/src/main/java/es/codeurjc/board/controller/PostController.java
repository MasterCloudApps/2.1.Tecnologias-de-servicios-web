package es.codeurjc.board.controller;

import static es.codeurjc.board.model.QPost.post;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.board.model.Comment;
import es.codeurjc.board.model.Post;
import es.codeurjc.board.repository.CommentRepository;
import es.codeurjc.board.repository.PostRepository;

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
		p.setUser("Pepe");
		p.setTitle("Vendo moto");
		p.setText("Bla bla...");
		p.addComment(new Comment("Juan", "Pues si"));
		p.addComment(new Comment("Maria", "Pues no"));

		posts.save(p);
	}

	@GetMapping("/")
	public Iterable<Post> getPosts(@RequestParam(required = false) String commentsUser) {
		if(commentsUser == null) {
			return posts.findAll();
		} else {
			return posts.findAll(post.comments.any().user.eq(commentsUser));
		}
	}

	@GetMapping("/{id}")
	public Post getPost(@PathVariable long id) {

		return posts.findById(id).orElseThrow();
	}

	@PostMapping("/")
	public ResponseEntity<Post> createPost(@RequestBody Post post) {

		posts.save(post);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();

		return ResponseEntity.created(location).body(post);
	}

	@PutMapping("/{id}")
	public Post replacePost(@RequestBody Post newPost, @PathVariable long id) {

		Post post = posts.findById(id).orElseThrow();

		newPost.setId(id);

		// We assume that comments are not updated with PUT operation
		post.getComments().forEach(c -> newPost.addComment(c));

		posts.save(newPost);

		return newPost;
	}

	@DeleteMapping("/{id}")
	public Post deletePost(@PathVariable long id) {

		Post post = posts.findById(id).orElseThrow();

		posts.deleteById(id);

		return post;
	}

	@GetMapping("/{idPost}/comments/{idComment}")
	public Comment getComment(@PathVariable long idPost, @PathVariable long idComment) {

		return comments.findById(idComment).orElseThrow();
	}

	@PostMapping("/{idPost}/comments/")
	public ResponseEntity<Comment> addComment(@PathVariable long idPost, @RequestBody Comment comment) {

		Post post = posts.findById(idPost).orElseThrow();

		comment.setPost(post);
		comments.save(comment);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(comment.getId()).toUri();

		return ResponseEntity.created(location).body(comment);
	}

	@PutMapping("/{idPost}/comments/{idComment}")
	public Comment replaceComment(@PathVariable long idPost, @PathVariable long idComment,
			@RequestBody Comment updatedComment) {

		Comment comment = comments.findById(idComment).orElseThrow();

		updatedComment.setId(idComment);
		updatedComment.setPost(comment.getPost());
		
		comments.save(updatedComment);

		return updatedComment;
	}

	@DeleteMapping("/{idPost}/comments/{idComment}")
	public Comment deleteComment(@PathVariable long idPost, @PathVariable long idComment) {

		Comment comment = comments.findById(idComment).orElseThrow();

		comments.delete(comment);

		return comment;
	}
	
}
