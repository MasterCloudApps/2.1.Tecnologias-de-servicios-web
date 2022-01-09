package es.mastercloudapps.jwt_example.repository;

import es.mastercloudapps.jwt_example.entity.ERole;
import es.mastercloudapps.jwt_example.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
