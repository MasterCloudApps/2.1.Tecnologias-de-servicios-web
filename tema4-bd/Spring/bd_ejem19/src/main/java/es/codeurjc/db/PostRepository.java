package es.codeurjc.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

	Page<Post> findByUsername(String username, Pageable page);

	Page<Post> findByTitle(String title, Pageable page);

}