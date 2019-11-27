package es.codeurjc.daw;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.codeurjc.daw.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {
	
	@Query("SELECT DISTINCT b FROM Blog b JOIN b.comments c WHERE c.author=?1")
	List<Blog> findByCommentsAuthor(String author);	

}