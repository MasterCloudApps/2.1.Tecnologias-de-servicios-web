package es.codeurjc.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.codeurjc.board.model.Post;
import es.codeurjc.board.service.PostService;

@Controller
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping("/")
	public String showPosts(Model model) {

		model.addAttribute("posts", postService.findAll());

		return "index";
	}

	@PostMapping("/post/new")
	public String newPost(Model model, Post post) {

		postService.save(post);

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