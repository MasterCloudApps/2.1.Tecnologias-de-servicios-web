package es.codeurjc.daw;

import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnunciosRepository extends JpaRepository<Anuncio, Long> {
	
	@Query("select a from Anuncio a")
	Stream<Anuncio> streamAll(Pageable p);

}