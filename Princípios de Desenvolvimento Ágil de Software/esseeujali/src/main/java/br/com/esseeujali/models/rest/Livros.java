package br.com.esseeujali.models.rest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.esseeujali.models.Livro;

@XmlRootElement
public class Livros {

	private List<Livro> livros = new ArrayList<Livro>();
	
	public Livros() {
	}
	
	public Livros(List<Livro> livros) {
		this.livros = livros;
	}
	
	public List<Livro> getLivros() {
		return livros;
	}
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	
}
