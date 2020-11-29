package es.codeurjc.daw;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.daw.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	
}