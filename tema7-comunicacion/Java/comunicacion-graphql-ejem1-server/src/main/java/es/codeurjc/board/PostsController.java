package es.codeurjc.board;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PostsController {

	@Autowired
	private PostRepository posts;

	@QueryMapping
	public Collection<Post> posts() {
		return posts.findAll();
	}

	@QueryMapping
	public Optional<Post> post(@Argument long id) {
		return posts.findById(id);
	}

	@MutationMapping
	public Post createPost(@Argument Post post) {

		posts.save(post);

		return post;
	}

	@MutationMapping
	public Post replacePost(@Argument long id, @Argument Post post) {

		posts.findById(id).orElseThrow();
		
		post.setId(id);
		posts.save(post);
		
		return post;
	}

	@MutationMapping
	public Post deletePost(@Argument long id) {

		Post post = posts.findById(id).orElseThrow();

		posts.deleteById(id);
		
		return post;
	}
}
