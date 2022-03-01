package es.codeurjc.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String data;

	public User() {
	}

	public User(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", data=" + data + "]";
	}

}
