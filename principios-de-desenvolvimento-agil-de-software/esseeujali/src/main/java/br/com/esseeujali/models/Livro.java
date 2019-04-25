package br.com.esseeujali.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="livros")
public class Livro {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String titulo;
	private String autor;
	private int paginas;
	private Categoria categoria;
	private String capa;
	
	public Livro() {
	}
	
	public Livro(String titulo, String autor, int paginas, Categoria categoria, String capa){
		this.titulo = titulo;
		this.autor = autor;
		this.paginas = paginas;
		this.categoria = categoria;
		this.capa = capa;
	}
	
	public Livro(Long id, String titulo, String autor, int paginas, Categoria categoria, String capa){
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.paginas = paginas;
		this.categoria = categoria;
		this.capa = capa;
	}
	
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getAutor() {
		return autor;
	}
	public int getPaginas() {
		return paginas;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public String getCapa() {
		return capa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public int getPontos() {
		if(this.paginas < 100)
			return 1;
		
		return (this.paginas / 100) + 1;
	}
	
}
