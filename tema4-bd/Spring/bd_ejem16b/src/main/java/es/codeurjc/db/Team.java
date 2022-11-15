package es.codeurjc.db;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String data;

	@ManyToOne
	private Player playerA;

	@ManyToOne
	private Player playerB;

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

	public Player getUserA() {
		return playerA;
	}

	public void setUserA(Player playerA) {
		this.playerA = playerA;
	}

	public Player getUserB() {
		return playerB;
	}

	public void setUserB(Player playerB) {
		this.playerB = playerB;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", data=" + data + ", userA=" + playerA + ", userB=" + playerB + "]";
	}

}
