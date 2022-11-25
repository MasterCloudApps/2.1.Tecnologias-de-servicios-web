package es.codeurjc.users;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Stream<User> getUsers(Optional<String> firstName) {
        return firstName
            .map(fn -> userRepository.findByFirstName(fn).stream())
            .orElseGet(() -> userRepository.findAll().stream());
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> deleteUser(Long id) {
        Optional<User> deletedUser = userRepository.findById(id);
        userRepository.deleteById(id);
        return deletedUser;
    }
}
