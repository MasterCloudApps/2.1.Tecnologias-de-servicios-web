package es.codeurjc.board.service;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.codeurjc.board.model.Post;
import es.codeurjc.board.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository posts;

	@PostConstruct
	public void init() {
		
		save(new Post("Pepe", "Vendo moto", "Barata, barata"));
		save(new Post("Juan", "Compro coche", "Pago bien"));
		
		for(int i=0; i<100; i++) {
			save(new Post("User"+i, "Title"+i, "Text"+i));
		}
	}

	public Collection<Post> findAll() {
		return posts.findAll();
	}
	
	public Page<Post> findAll(Pageable pageable) {
		return posts.findAll(pageable);
	}

	public Optional<Post> findById(long id) {
		return posts.findById(id);
	}

	public void save(Post post) {

		posts.save(post);
	}

	public void deleteById(long id) {
		this.posts.deleteById(id);
	}

}
