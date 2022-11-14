package es.codeurjc.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("""
		select distinct u from User u, Team t 
		where (t.userA = u and t.userB = :user) or (t.userB = u and t.userA = :user)	
	""")
	List<User> findPairsOf(User user);
	

}
