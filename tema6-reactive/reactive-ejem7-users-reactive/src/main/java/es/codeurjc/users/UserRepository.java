package es.codeurjc.users;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {

	Flux<User> findByFirstName(String lastName);
}