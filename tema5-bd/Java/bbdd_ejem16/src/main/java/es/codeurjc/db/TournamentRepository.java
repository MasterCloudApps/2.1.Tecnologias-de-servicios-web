package es.codeurjc.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

	@Query("SELECT distinct t FROM Match m JOIN m.tournament t " +
		"WHERE m.team1 = :team OR m.team2 = :team")
	public List<Tournament> getTournaments(Team team);
}
