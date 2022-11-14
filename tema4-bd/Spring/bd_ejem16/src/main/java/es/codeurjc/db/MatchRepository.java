package es.codeurjc.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MatchRepository extends JpaRepository<Match, Long> {

	@Query("SELECT m FROM Match m WHERE m.tournament = :t")
	public List<Match> getMatches(Tournament t);

}
