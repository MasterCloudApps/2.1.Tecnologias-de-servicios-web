package es.codeurjc.daw;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

public class Libro {

	interface Basico {
	}

	@JsonView(Basico.class)
	private long id = -1;

	@JsonView(Basico.class)
	private String titulo;

	@JsonView(Basico.class)
	private int precio;

	private List<Autor> autores = new ArrayList<>();

	public Libro() {
	}

	public Libro(int id, String titulo, int precio) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.precio = precio;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", precio=" + precio + ", autores=" + autores + "]";
	}

}
