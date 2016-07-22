package placar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import armazenamento.Armazenamento;
import exceptions.ArmazenamentoException;
import exceptions.UsuarioInexistenteException;
import models.Usuario;
import pontuacoes.Pontuacao;
import pontuacoes.TipoPontuacao;

public class Placar {

	private Armazenamento armazenamento;
	
	public Placar(Armazenamento armazenamento) {
		this.armazenamento = armazenamento;
	}
	
	public void registrar(String nomeUsuario, Pontuacao pontuacao) throws ArmazenamentoException{
		armazenamento.armazenarPontuacao(nomeUsuario, pontuacao);
	}
	
	public Map<TipoPontuacao, Integer> getTodosOsPontosDoUsuario(String nomeUsuario) throws UsuarioInexistenteException{
		Map<TipoPontuacao, Integer> pontosDoUsuario = new HashMap<>();
		
		List<TipoPontuacao> tiposDePontos = armazenamento.getOsTiposDePontosDoUsuario(nomeUsuario);
		for (TipoPontuacao tipoPontuacao : tiposDePontos) {
			int pontuacao = armazenamento.recuperarPontosPorTipo(nomeUsuario, tipoPontuacao);
			pontosDoUsuario.put(tipoPontuacao, pontuacao);
		}
		
		return pontosDoUsuario;
	}
	
	public List<Usuario> rankingPorTipoDePontuacao(TipoPontuacao tipoPontuacao){
		List<Usuario> ranking = new ArrayList<>(); 
		
		List<Usuario> usuariosComPontuacao = armazenamento.getUsuariosComPontuacao();
		for (Usuario usuario : usuariosComPontuacao) {
			if(usuario.isPossuiOTipoDePontuacao(tipoPontuacao))
				ranking.add(usuario);
		}
		
		return ordenarPorTipoDePontuacao(tipoPontuacao, ranking);
	}

	private List<Usuario> ordenarPorTipoDePontuacao(TipoPontuacao tipoPontuacao, List<Usuario> ranking) {
		ranking.sort(new Comparator<Usuario>() {

			@Override
			public int compare(Usuario u1, Usuario u2) {
				if(u1.getPontuacaoPorTipo(tipoPontuacao) > u2.getPontuacaoPorTipo(tipoPontuacao))
					return 1;
				if(u1.getPontuacaoPorTipo(tipoPontuacao) < u2.getPontuacaoPorTipo(tipoPontuacao))
					return -1;
				return 0;
			}
		});
		
		return ranking;
	}
	
}
