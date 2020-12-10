package es.codeurjc.board.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import es.codeurjc.board.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {
	
}
