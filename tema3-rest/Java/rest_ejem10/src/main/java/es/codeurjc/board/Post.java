package es.codeurjc.board;

public class Post {

	private Long id;
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

	public Long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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
		return "Post [id="+id+", user=" + user + ", title=" + title + ", text=" + text + "]";
	}

}
