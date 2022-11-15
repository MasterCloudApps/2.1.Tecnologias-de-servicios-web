package es.codeurjc.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends JpaRepository<Player, Long> {

	@Query("""
		select distinct u from Player u, Team t 
		where (t.playerA = u and t.playerB = :player) or (t.playerB = u and t.playerA = :player)	
	""")
	List<Player> findPairsOf(Player player);
	

}
