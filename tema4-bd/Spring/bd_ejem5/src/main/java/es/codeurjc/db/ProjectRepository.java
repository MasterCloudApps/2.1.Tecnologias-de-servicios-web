package es.codeurjc.db;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.db.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	
}