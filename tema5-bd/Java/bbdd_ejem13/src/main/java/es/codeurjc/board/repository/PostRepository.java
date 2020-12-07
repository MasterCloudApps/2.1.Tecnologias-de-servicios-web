package es.codeurjc.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import es.codeurjc.board.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>, QuerydslPredicateExecutor<Post> {

	@Query("SELECT DISTINCT p FROM Post p JOIN p.comments c WHERE c.user=?1")
	List<Post> findByCommentsUser(String user);	
	
}
