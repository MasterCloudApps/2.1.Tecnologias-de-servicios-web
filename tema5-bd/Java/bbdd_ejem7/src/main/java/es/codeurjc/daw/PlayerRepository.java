package es.codeurjc.daw;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.daw.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
	
}