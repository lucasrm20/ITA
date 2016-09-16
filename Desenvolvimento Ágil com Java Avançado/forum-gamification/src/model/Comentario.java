package model;

public class Comentario {

	private int id_comentario;
	private String comentario;
	private Usuario usuario;
	private Topico topico;
	
	public int getId_comentario() {
		return id_comentario;
	}
	public void setId_comentario(int id_comentario) {
		this.id_comentario = id_comentario;
	}
	
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Topico getTopico() {
		return topico;
	}
	public void setTopico(Topico topico) {
		this.topico = topico;
	}
	
}
