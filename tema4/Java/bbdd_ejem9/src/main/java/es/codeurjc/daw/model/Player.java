package es.codeurjc.daw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Player {
	
	public interface BasicAtt {}
	public interface TeamAtt {}

	@JsonView(BasicAtt.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@JsonView(BasicAtt.class)
	private String name;
	
	@JsonView(BasicAtt.class)
	private int goals;
	
	@JsonView(TeamAtt.class)
	@ManyToOne
	private Team team;

	protected Player() {
	}

	public Player(String name, int goals) {
		super();
		this.name = name;
		this.goals = goals;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String author) {
		this.name = author;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public void setTeam(Team team) {
		this.team = team;		
	}

	public int getGoals() {
		return goals;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", goals=" + goals + "]";
	}

	

}
