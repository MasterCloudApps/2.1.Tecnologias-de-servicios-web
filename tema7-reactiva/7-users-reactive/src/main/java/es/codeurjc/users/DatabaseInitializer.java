package es.codeurjc.users;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;

@Component
@DependsOn({"initializer"})
public class DatabaseInitializer {

    private UserRepository userRepository;

    DatabaseInitializer(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initDatabase() {

        Flux<User> users = Flux.just(
            new User("Juan", "López"),
            new User("Pepe", "Reina"),
            new User("Juan", "García"));

        users
            .flatMap(this.userRepository::save)
            .blockLast();
    }

//    Los métodos @PostConstruct no pueden ser asíncronos. Hay que forzar la suscripción o bloquear hasta finalización
//    @PostConstruct
//    public Mono<Void> initDatabase() {
//
//        Flux<User> users = Flux.just(
//            new User("Juan", "López"),
//            new User("Pepe", "Reina"),
//            new User("Juan", "García"));
//
//        return users
//            .flatMap(this.userRepository::save)
//            .then(Mono.empty());
//    }
}
