package es.codeurjc.db.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Student {

	public interface BasicAtt {}
	public interface ProjectAtt {}
	
	@JsonView(BasicAtt.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@JsonView(BasicAtt.class)
	private String name;
	
	@JsonView(BasicAtt.class)
	private int startYear;

	@JsonView(ProjectAtt.class)
	@OneToOne(cascade = CascadeType.ALL)
	private Project project;

	protected Student() {
	}

	public Student(String name, int startYear) {
		super();
		this.name = name;
		this.startYear = startYear;
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

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int goals) {
		this.startYear = goals;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", goals=" + startYear + "]";
	}

}
