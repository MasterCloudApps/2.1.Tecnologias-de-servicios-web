package es.codeurjc.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUser(String user);

	List<Post> findByTitle(String title);

}