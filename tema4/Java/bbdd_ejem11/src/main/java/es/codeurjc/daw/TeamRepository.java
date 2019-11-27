package es.codeurjc.daw;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.codeurjc.daw.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

	@Query("select t from Team t where t.name = ?1")
	List<Team> findByName(String name);
	
}