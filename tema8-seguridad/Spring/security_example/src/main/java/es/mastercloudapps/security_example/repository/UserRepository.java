package es.mastercloudapps.security_example.repository;

import es.mastercloudapps.security_example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
