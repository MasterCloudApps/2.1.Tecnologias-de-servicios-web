package es.codeurjc.daw;

public class Anuncio {

	private long id = -1;
	private String nombre;
	private String asunto;
	private String comentario;

	public Anuncio() {

	}

	public Anuncio(String nombre, String asunto, String comentario) {
		super();
		this.nombre = nombre;
		this.asunto = asunto;
		this.comentario = comentario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Anuncio [id=" + id + ",nombre=" + nombre + ", asunto=" + asunto + ", comentario=" + comentario + "]";
	}

}
