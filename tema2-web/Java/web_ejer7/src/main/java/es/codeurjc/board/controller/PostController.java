package es.codeurjc.board.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.board.model.Post;
import es.codeurjc.board.service.ImageService;
import es.codeurjc.board.service.PostService;
import es.codeurjc.board.service.UserSession;

@Controller
public class PostController {

	private static final String POSTS_FOLDER = "posts";

	@Autowired
	private PostService postService;
	
	@Autowired
	private UserSession userSession;
	
	@Autowired
	private ImageService imageService;

	@GetMapping("/")
	public String showPosts(Model model, HttpSession session) {

		model.addAttribute("posts", postService.findAll());
		model.addAttribute("welcome", session.isNew());

		return "index";
	}

	@GetMapping("/post/new")
	public String newPostForm(Model model) {

		model.addAttribute("user", userSession.getUser());

		return "new_post";
	}
	
	@PostMapping("/post/new")
	public String newPost(Model model, Post post, MultipartFile image) throws IOException {

		postService.save(post);
		
		imageService.saveImage(POSTS_FOLDER, post.getId(), image);
		
		userSession.setUser(post.getUser());
		userSession.incNumPosts();
		
		model.addAttribute("numPosts", userSession.getNumPosts());

		return "saved_post";
	}

	@GetMapping("/post/{id}")
	public String showPost(Model model, @PathVariable long id) {

		Post post = postService.findById(id);

		model.addAttribute("post", post);

		return "show_post";
	}
	
	@GetMapping("/post/{id}/image")	
	public ResponseEntity<Object> downloadImage(@PathVariable int id) throws MalformedURLException {

		return imageService.createResponseFromImage(POSTS_FOLDER, id);		
	}
	
	@GetMapping("/post/{id}/delete")
	public String deletePost(Model model, @PathVariable long id) throws IOException {

		postService.deleteById(id);
		
		imageService.deleteImage(POSTS_FOLDER, id);

		return "deleted_post";
	}
}