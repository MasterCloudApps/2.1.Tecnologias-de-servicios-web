package es.mastercloudapps.jwt.repository;

import java.util.Optional;

import es.mastercloudapps.jwt.models.ERole;
import es.mastercloudapps.jwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
