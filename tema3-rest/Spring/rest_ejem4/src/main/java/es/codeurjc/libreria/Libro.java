package es.codeurjc.libreria;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Libro {

	private long id = -1;
	private String titulo;
	private int precio;

	@JsonIgnore
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
