package es.codeurjc.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Project {
	
	public interface BasicAtt {}
	public interface StudentAtt {}

	@JsonView(BasicAtt.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	@JsonView(BasicAtt.class)
	private String title;
	
	@JsonView(BasicAtt.class)
	private int calification;

	@JsonView(StudentAtt.class)
	@OneToOne(mappedBy = "project")
	private Student student;

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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", title=" + title + ", calification=" + calification + "]";
	}

}