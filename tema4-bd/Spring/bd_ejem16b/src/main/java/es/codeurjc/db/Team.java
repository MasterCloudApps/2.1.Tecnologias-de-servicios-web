package es.codeurjc.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String data;

	@ManyToOne
	private User userA;

	@ManyToOne
	private User userB;

	public Team() {
		super();
	}

	public Team(String data) {
		super();
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public User getUserA() {
		return userA;
	}

	public void setUserA(User userA) {
		this.userA = userA;
	}

	public User getUserB() {
		return userB;
	}

	public void setUserB(User userB) {
		this.userB = userB;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", data=" + data + ", userA=" + userA + ", userB=" + userB + "]";
	}

}
