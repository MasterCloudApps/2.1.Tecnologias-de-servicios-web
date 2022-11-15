package es.codeurjc.db;

import java.util.List;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.codeurjc.db.model.Player;
import es.codeurjc.db.model.Team;

@RestController
public class TeamController {

	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private PlayerRepository playerRepository;

	@PostConstruct
	public void init() {

		Team team1 = new Team("Selección", 1);
		Team team2 = new Team("FC Barcelona", 1);
		Team team3 = new Team("Atlético de Madrid", 2);
		
		teamRepository.save(team1);
		teamRepository.save(team2);
		teamRepository.save(team3);
		
		Player p1 = new Player("Torres", 10);
		Player p2 = new Player("Iniesta", 10);
		
		p1.getTeams().add(team1);
		p1.getTeams().add(team3);
		
		p2.getTeams().add(team1);
		p2.getTeams().add(team2);
		
		playerRepository.save(p1);
		playerRepository.save(p2);
		
	}

	interface TeamListView extends Team.BasicAtt, Team.PlayersAtt, Player.BasicAtt {}
	
	@JsonView(TeamListView.class)
	@GetMapping("/teams/")
	public List<Team> getTeams() throws Exception {
		return teamRepository.findAll();
	}
	
	interface PlayerView extends Player.BasicAtt, Player.TeamAtt, Team.BasicAtt {}
	
	@JsonView(PlayerView.class)
	@GetMapping("/players/{id}")
	public Player getPlayer(@PathVariable long id) throws Exception {
		return playerRepository.findById(id).orElseThrow();
	}
}
