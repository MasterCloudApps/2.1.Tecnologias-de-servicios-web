package es.codeurjc.daw;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

public class Autor {

	interface Basico {
	}

	interface Libros {
	}

	@JsonView(Basico.class)
	private long id = -1;

	@JsonView(Basico.class)
	private String nombre;

	@JsonView(Basico.class)
	private String nacionalidad;

	@JsonView(Libros.class)
	private List<Libro> libros = new ArrayList<>();

	public Autor() {
	}

	public Autor(long id, String nombre, String nacionalidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", libros=" + libros + "]";
	}
}
