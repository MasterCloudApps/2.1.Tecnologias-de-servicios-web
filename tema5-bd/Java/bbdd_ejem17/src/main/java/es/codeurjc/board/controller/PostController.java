package es.codeurjc.board.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.List;

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
import es.codeurjc.board.service.CommentService;
import es.codeurjc.board.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService posts;

	@Autowired
	private CommentService comments;

	@GetMapping("/")
	public List<Post> getPosts(@RequestParam(required = false) String commentsUser) {
		if(commentsUser == null) {
			return posts.findAll();
		} else {
			return posts.findByCommentsUser(commentsUser);
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

		newPost.setId(id);
		
		posts.replace(newPost);
		
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

		updatedComment.setId(idComment);
		
		comments.replace(updatedComment);
		
		return updatedComment;
	}

	@DeleteMapping("/{idPost}/comments/{idComment}")
	public Comment deleteComment(@PathVariable long idPost, @PathVariable long idComment) {

		Comment comment = comments.findById(idComment).orElseThrow();

		comments.delete(comment);

		return comment;
	}
	
}
