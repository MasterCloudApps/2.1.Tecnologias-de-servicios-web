package es.codeurjc.daw;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.daw.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
}