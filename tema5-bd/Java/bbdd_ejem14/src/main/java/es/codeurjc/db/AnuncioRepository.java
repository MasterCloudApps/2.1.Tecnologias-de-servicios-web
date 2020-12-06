package es.codeurjc.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

	Page<Anuncio> findByNombre(String nombre, Pageable page);

	Page<Anuncio> findByAsunto(String asunto, Pageable page);

}