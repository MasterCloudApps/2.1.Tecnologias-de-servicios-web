package es.codeurjc.daw;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.daw.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
	
}