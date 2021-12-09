package es.codeurjc.db;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DatabaseSandbox {
	
	@Autowired
	private TournamentRepository tournamentRepository;
	
	@Autowired
	private MatchRepository matchRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@PostConstruct
	public void init() {
		
		Team t1 = new Team("Team1");
		Team t2 = new Team("Team2");
		Team t3 = new Team("Team3");
		
		teamRepository.save(t1);
		teamRepository.save(t2);
		teamRepository.save(t3);
		
		Tournament tournament = new Tournament("T");
		tournamentRepository.save(tournament);
		
		Match m1 = new Match("M1");
		m1.setTeam1(t1);
		m1.setTeam2(t2);
		
		Match m2 = new Match("M2");
		m2.setTeam1(t2);
		m2.setTeam2(t3);
		
		Match m3 = new Match("M3");
		m3.setTeam1(t1);
		m3.setTeam2(t3);
		
		m1.setTournament(tournament);
		m2.setTournament(tournament);
		m3.setTournament(tournament);
		
		matchRepository.save(m1);
		matchRepository.save(m2);
		matchRepository.save(m3);
				
		List<Tournament> tournaments = tournamentRepository.getTournaments(t1);
		
		System.out.println("Tournaments: "+tournaments);
		
		List<Match> matches = matchRepository.getMatches(tournament);
		
		System.out.println("Matches: "+matches);
		
		List<Team> teams = teamRepository.getTeams(tournament);
		
		System.out.println("Teams: "+teams);
	}

}
