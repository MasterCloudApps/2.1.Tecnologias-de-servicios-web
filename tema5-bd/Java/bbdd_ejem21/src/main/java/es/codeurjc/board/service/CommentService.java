package es.codeurjc.board.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.board.model.Comment;
import es.codeurjc.board.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository comments;

	public Optional<Comment> findById(long id) {

		return comments.findById(id);
	}

	public void save(Comment comment) {
		
		this.comments.save(comment);
	}

	/**
	 * Only atomic attributes are updated
	 * @param updatedComment
	 */
	public void replace(Comment updatedComment) {
		
		Comment comment = comments.findById(updatedComment.getId()).orElseThrow();
		
		updatedComment.setPost(comment.getPost());
		
		comments.save(updatedComment);	
	}

	public void delete(Comment comment) {
		
		this.comments.delete(comment);		
	}
}
