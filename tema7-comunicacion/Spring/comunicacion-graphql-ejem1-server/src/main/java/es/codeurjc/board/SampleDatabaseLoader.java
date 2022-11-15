package es.codeurjc.board;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SampleDatabaseLoader {

	@Autowired 
	private PostRepository posts;
	
	@PostConstruct
	public void init() {
		posts.save(new Post("Pepe", "Vendo moto", "Barata, barata"));
		posts.save(new Post("Juan", "Compro coche", "Pago bien"));
	}
}
