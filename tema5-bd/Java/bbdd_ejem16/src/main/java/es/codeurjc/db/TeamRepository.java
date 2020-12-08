package es.codeurjc.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository extends JpaRepository<Team, Long> {

	@Query("SELECT distinct team FROM Match m, Team team "
			+ "WHERE (m.team1 = team OR m.team2 = team) AND  m.tournament = :t")
	public List<Team> getTeams(Tournament t);

}
