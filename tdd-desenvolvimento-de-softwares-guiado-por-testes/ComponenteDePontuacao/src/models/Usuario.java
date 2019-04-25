package models;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pontuacoes.Pontuacao;
import pontuacoes.TipoPontuacao;

public class Usuario {

	private String nome;
	
	private Map<TipoPontuacao, Pontuacao> pontuacoes;
	
	public Usuario(String nome) {
		this.nome = nome;
		this.pontuacoes = new HashMap<>();
	}
	
	public void adicionaUmaPontuacao(Pontuacao pontuacao) {
		pontuacoes.put(pontuacao.getTipoPontuacao(), pontuacao);
	}
	
	public boolean isPontuacaoExistente(Pontuacao pontuacao){
		if(isPossuiOTipoDePontuacao(pontuacao.getTipoPontuacao())){
			int pontuacaoAtual = getPontuacaoPorTipo(pontuacao.getTipoPontuacao());
			
			if(pontuacaoAtual == pontuacao.getPontuacao())
				return true;
		}
		return false;
	}
	
	public boolean isPossuiOTipoDePontuacao(TipoPontuacao tipoPontuacao) {
		return this.pontuacoes.containsKey(tipoPontuacao);
	}
	
	public int getPontuacaoPorTipo(TipoPontuacao tipoPontuacao){
		if(isPossuiOTipoDePontuacao(tipoPontuacao))
			return this.pontuacoes.get(tipoPontuacao).getPontuacao();
		
		return 0;
	}
	
	public List<TipoPontuacao> getTiposDePontos(){
		return new ArrayList<TipoPontuacao>(pontuacoes.keySet());
	}
	
	public String getNome() {
		return nome;
	}
	
	public Map<TipoPontuacao, Pontuacao> getPontuacoes() {
		return Collections.unmodifiableMap(pontuacoes);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Usuario other = (Usuario) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
