package es.codeurjc.daw;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.daw.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {
	
}