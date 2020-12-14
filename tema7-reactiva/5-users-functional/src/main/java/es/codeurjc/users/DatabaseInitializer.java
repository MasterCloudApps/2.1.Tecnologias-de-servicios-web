package es.codeurjc.users;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Component
public class DatabaseInitializer {

    private UserRepository userRepository;

    DatabaseInitializer(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initDatabase() {

        Stream<User> users = Stream.of(
            new User("Juan", "López"),
            new User("Pepe", "Reina"),
            new User("Juan", "García"));

        users.forEach(this.userRepository::save);
    }
}
