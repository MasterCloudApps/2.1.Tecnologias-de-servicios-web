package es.codeurjc.db;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;

@Entity
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String data;

	@ManyToOne
	private Team team1;

	@ManyToOne
	private Team team2;

	@ManyToOne
	Tournament tournament;

	public Match() {
		super();
	}

	public Match(String data) {
		super();
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	@Override
	public String toString() {
		return "Match [id=" + id + ", data=" + data + "]";
	}

}
