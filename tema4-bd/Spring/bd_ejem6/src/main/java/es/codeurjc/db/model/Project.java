package es.codeurjc.db.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	private String title;
	private int calification;

	protected Project() {
	}

	public Project(String title, int calification) {
		this.title = title;
		this.calification = calification;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCalification() {
		return calification;
	}

	public void setCalification(int calification) {
		this.calification = calification;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", title=" + title + ", calification=" + calification + "]";
	}

}