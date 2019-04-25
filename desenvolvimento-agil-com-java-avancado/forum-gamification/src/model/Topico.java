package model;

public class Topico {

	private int id_topico;
	private String titulo;
	private String conteudo;
	private Usuario usuario;
	
	public int getId_topico() {
		return id_topico;
	}
	public void setId_topico(int id_topico) {
		this.id_topico = id_topico;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
