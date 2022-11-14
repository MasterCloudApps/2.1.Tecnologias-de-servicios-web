package es.codeurjc.db;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DatabaseSandbox {
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void init() {
		
		User user1 = new User("user1");
		User user2 = new User("user2");
		User user3 = new User("user3");
		User user4 = new User("user4");
						
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		userRepository.save(user4);
				
		Team t1 = new Team("Team1");
		t1.setUserA(user1);		
		t1.setUserB(user2);
		
		Team t2 = new Team("Team2");
		t2.setUserA(user3);		
		t2.setUserB(user4);
		
		Team t3 = new Team("Team3");
		t3.setUserA(user1);		
		t3.setUserB(user4);
		
		teamRepository.save(t1);
		teamRepository.save(t2);
		teamRepository.save(t3);
		
		List<Team> teams = teamRepository.findAll();
		System.out.println("Teams: "+teams);
		
		List<User> users1 = userRepository.findPairsOf(user1);
		System.out.println("Pairs of User1: "+users1);
		
		List<User> users2 = userRepository.findPairsOf(user2);
		System.out.println("Pairs of User2: "+users2);
		
		List<User> users3 = userRepository.findPairsOf(user3);
		System.out.println("Pairs of User3: "+users3);
		
		List<User> users4 = userRepository.findPairsOf(user4);
		System.out.println("Pairs of User4: "+users4);
		
	}

}
