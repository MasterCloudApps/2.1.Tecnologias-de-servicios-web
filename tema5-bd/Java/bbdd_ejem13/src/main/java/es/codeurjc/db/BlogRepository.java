package es.codeurjc.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import es.codeurjc.db.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long>, QuerydslPredicateExecutor<Blog> {
	
	

}