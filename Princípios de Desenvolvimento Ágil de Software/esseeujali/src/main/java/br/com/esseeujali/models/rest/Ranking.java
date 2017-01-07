package br.com.esseeujali.models.rest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.esseeujali.models.Usuario;

@XmlRootElement
public class Ranking {

	private List<Usuario> ranking = new ArrayList<Usuario>();
	
	public Ranking() {
	}
	
	public Ranking(List<Usuario> ranking) {
		this.ranking = ranking;
	}
	
	public List<Usuario> getRanking() {
		return ranking;
	}
	public void setRanking(List<Usuario> ranking) {
		this.ranking = ranking;
	}
	
}
