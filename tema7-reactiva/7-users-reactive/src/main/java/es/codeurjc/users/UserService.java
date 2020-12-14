package es.codeurjc.users;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> createUser(User user) {
        return userRepository.save(user);
    }

    public Flux<User> getUsers(Optional<String> firstName) {
        return firstName
            .map(fn -> userRepository.findByFirstName(fn))
            .orElseGet(() -> userRepository.findAll());
    }

    public Mono<User> getUser(Long id) {
        return userRepository.findById(id)
            .switchIfEmpty(
                Mono.error(new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User with id "+id+" not found")));
    }

    public Mono<User> deleteUser(Long id) {
        Mono<User> deletedUser = userRepository.findById(id);
        return deletedUser.flatMap(user -> userRepository.deleteById(id).then(Mono.just(user)));
    }
}
