package es.codeurjc.board.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.board.model.Comment;
import es.codeurjc.board.model.Post;

@Service
public class SampleDataService {

	@Autowired
	private PostService posts; 
	
	@PostConstruct
	public void init() {

		Post p = new Post();
		p.setUser("Pepe");
		p.setTitle("Vendo moto");
		p.setText("Bla bla...");
		p.addComment(new Comment("Juan", "Pues si"));
		p.addComment(new Comment("Maria", "Pues no"));

		posts.save(p);
	}
}
