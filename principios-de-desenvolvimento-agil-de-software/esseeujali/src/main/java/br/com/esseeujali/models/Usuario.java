package br.com.esseeujali.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="usuarios")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String usuario;
	
	@XmlTransient
	private String senha;
	
	private int pontuacao;
	
	@ManyToMany
	private Set<Livro> livrosLidos = new HashSet<Livro>();
	
	@ElementCollection(targetClass=Categoria.class, fetch=FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Set<Categoria> trofeus = new HashSet<Categoria>();
	
	public Usuario() {
	}
	
	public Usuario(String usuario, String senha, int pontuacao){
		this.usuario = usuario;
		this.senha = senha;
		this.pontuacao = pontuacao;
	}
	
	public Long getId() {
		return id;
	}
	public String getUsuario() {
		return usuario;
	}
	public String getSenha() {
		return senha;
	}
	public int getPontuacao() {
		return pontuacao;
	}
	public Set<Livro> getLivrosLidos() {
		return Collections.unmodifiableSet(this.livrosLidos);
	}
	public Set<Categoria> getTrofeus() {
		return Collections.unmodifiableSet(this.trofeus);
	}

	public void marcarLivroComoLido(Livro livro) {
		if(isLivroLido(livro))
			return;
		
		livrosLidos.add(livro);
		this.pontuacao += livro.getPontos();
		
		adicionaTrofeus(livro);
	}
	
	private void adicionaTrofeus(Livro livro){
		if(getQuantidadeLivrosLidosPorCategoria(livro.getCategoria()) >= 5){
			this.trofeus.add(livro.getCategoria());
		}
	}
	
	private int getQuantidadeLivrosLidosPorCategoria(Categoria categoria){
		int qtd = 0;
		
		for (Livro livro : livrosLidos) {
			if(livro.getCategoria().equals(categoria))
				qtd++;
		}
		
		return qtd;
	}

	public boolean isLivroLido(Livro livro) {
		return livrosLidos.contains(livro);
	}

	public int getQuantidadeLivrosLidos() {
		return livrosLidos.size();
	}
	
	
}
