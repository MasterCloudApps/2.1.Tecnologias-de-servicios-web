package es.codeurjc.db;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.db.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}