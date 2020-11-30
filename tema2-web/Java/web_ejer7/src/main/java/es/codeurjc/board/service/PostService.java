package es.codeurjc.board.service;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import es.codeurjc.board.model.Post;

@Service
public class PostService {

	private ConcurrentMap<Long, Post> posts = new ConcurrentHashMap<>();
	private AtomicLong nextId = new AtomicLong();

	public PostService() {
		save(new Post("Pepe", "Vendo moto", "Barata, barata"));
		save(new Post("Juan", "Compro coche", "Pago bien"));
	}

	public Collection<Post> findAll() {
		return posts.values();
	}

	public Post findById(long id) {
		return posts.get(id);
	}

	public void save(Post post) {

		long id = nextId.getAndIncrement();

		post.setId(id);

		this.posts.put(id, post);
	}

	public void deleteById(long id) {
		this.posts.remove(id);
	}

}
