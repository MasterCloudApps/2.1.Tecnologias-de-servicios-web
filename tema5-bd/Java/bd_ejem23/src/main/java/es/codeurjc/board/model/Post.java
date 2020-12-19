package es.codeurjc.board.model;

import org.springframework.data.annotation.Id;

public class Post {

	@Id
	private String id;

	private String user;
	private String title;
	private String text;
	
	public Post() {
	}

	public Post(String user, String title, String text) {
		super();
		this.user = user;
		this.title = title;
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String name) {
		this.user = name;
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

	public void setText(String content) {
		this.text = content;
	}

}
