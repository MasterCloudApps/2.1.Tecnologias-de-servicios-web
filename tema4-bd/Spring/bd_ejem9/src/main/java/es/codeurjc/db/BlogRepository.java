package es.codeurjc.db;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.db.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {
	
}