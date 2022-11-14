package es.codeurjc.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

	private List<Post> posts = new ArrayList<>();

	public PostController() {
		posts.add(new Post("Pepe", "Vendo moto", "Barata, barata"));
		posts.add(new Post("Juan", "Compro coche", "Pago bien"));
	}

	@GetMapping("/")
	public String showPosts(Model model) {

		model.addAttribute("posts", posts);

		return "index";
	}

	@PostMapping("/post/new")
	public String newPost(Model model, Post post) {

		posts.add(post);

		return "saved_post";
	}

	@GetMapping("/post/{numPost}")
	public String showPost(Model model, @PathVariable int numPost) {

		Post post = posts.get(numPost - 1);

		model.addAttribute("post", post);
		model.addAttribute("numPost", numPost);

		return "show_post";
	}
	
	@GetMapping("/post/{numPost}/delete")
	public String deletePost(Model model, @PathVariable int numPost) {

		posts.remove(numPost - 1);

		return "deleted_post";
	}
}