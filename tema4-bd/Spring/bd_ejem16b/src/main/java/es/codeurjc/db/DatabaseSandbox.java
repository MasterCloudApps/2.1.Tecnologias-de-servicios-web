package es.codeurjc.db;

import java.util.List;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DatabaseSandbox {
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@PostConstruct
	public void init() {
		
		Player player1 = new Player("player1");
		Player player2 = new Player("player2");
		Player player3 = new Player("player3");
		Player player4 = new Player("player4");
						
		playerRepository.save(player1);
		playerRepository.save(player2);
		playerRepository.save(player3);
		playerRepository.save(player4);
				
		Team t1 = new Team("Team1");
		t1.setUserA(player1);
		t1.setUserB(player2);
		
		Team t2 = new Team("Team2");
		t2.setUserA(player3);
		t2.setUserB(player4);
		
		Team t3 = new Team("Team3");
		t3.setUserA(player1);
		t3.setUserB(player4);
		
		teamRepository.save(t1);
		teamRepository.save(t2);
		teamRepository.save(t3);
		
		List<Team> teams = teamRepository.findAll();
		System.out.println("Teams: "+teams);
		
		List<Player> users1 = playerRepository.findPairsOf(player1);
		System.out.println("Pairs of User1: "+users1);
		
		List<Player> users2 = playerRepository.findPairsOf(player2);
		System.out.println("Pairs of User2: "+users2);
		
		List<Player> users3 = playerRepository.findPairsOf(player3);
		System.out.println("Pairs of User3: "+users3);
		
		List<Player> users4 = playerRepository.findPairsOf(player4);
		System.out.println("Pairs of User4: "+users4);
		
	}

}
