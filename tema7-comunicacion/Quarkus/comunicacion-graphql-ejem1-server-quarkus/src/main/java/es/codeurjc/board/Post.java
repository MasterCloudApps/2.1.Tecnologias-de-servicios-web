package es.codeurjc.board;

import org.eclipse.microprofile.graphql.Ignore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {

	@org.eclipse.microprofile.graphql.Id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String username;
	private String title;
	private String text;

	public Post() {
	}

	public Post(String username, String title, String text) {
		super();
		this.username = username;
		this.title = title;
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	@Ignore
	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String user) {
		this.username = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", user=" + username + ", title=" + title + ", text=" + text + "]";
	}

}
