package es.codeurjc.board.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.codeurjc.board.model.Post;
import es.codeurjc.board.service.PostService;
import es.codeurjc.board.service.UserSession;

@Controller
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private UserSession userSession;

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
	public String newPost(Model model, Post post) {

		postService.save(post);
		
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
	
	@GetMapping("/post/{id}/delete")
	public String deletePost(Model model, @PathVariable long id) {

		postService.deleteById(id);

		return "deleted_post";
	}
}