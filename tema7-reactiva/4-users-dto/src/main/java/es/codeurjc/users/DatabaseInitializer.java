package es.codeurjc.users;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseInitializer {

    private UserRepository userRepository;

    DatabaseInitializer(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initDatabase() {

        List<User> users = List.of(
            new User("Juan", "López"),
            new User("Pepe", "Reina"),
            new User("Juan", "García"));

        for(User user : users){
            this.userRepository.save(user);
        }
    }
}
