package es.codeurjc.users;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsers(String firstName) {

        if (firstName == null) {
            return userRepository.findAll();
        } else {
            return userRepository.findByFirstName(firstName);
        }
    }

    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public User deleteUser(Long id) {
        User deletedUser = userRepository.findById(id).get();
        userRepository.deleteById(id);
        return deletedUser;
    }
}
