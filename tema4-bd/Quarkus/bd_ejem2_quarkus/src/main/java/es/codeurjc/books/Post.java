package es.codeurjc.books;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import java.util.Collection;

@Entity
public class Post extends PanacheEntity {

	public String username;
	public String title;
	public String text;

	public Post() {
	}
	public Post(String username, String title, String text) {
		super();
		this.username = username;
		this.title = title;
		this.text = text;
	}

	public static Collection<Post> findByUsername(String username) {
		return list("username", username);
	}
}
