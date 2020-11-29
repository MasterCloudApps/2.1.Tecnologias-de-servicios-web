package es.codeurjc.daw;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.daw.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
}