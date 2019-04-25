package armazenamento;
import java.util.List;

import exceptions.ArmazenamentoException;
import exceptions.UsuarioInexistenteException;
import models.ListagemUsuarios;
import models.Usuario;
import pontuacoes.Pontuacao;
import pontuacoes.TipoPontuacao;

public class Armazenamento {
	
	private Armazenador armazenador;
	private ListagemUsuarios usuarios;
	
	private String nomeArquivo;
	
	public Armazenamento(Armazenador armazenador, String nomeArquivo) {
		this.armazenador = armazenador;
		this.nomeArquivo = nomeArquivo;
		this.usuarios = armazenador.lerArquivo(nomeArquivo);
	}

	public void armazenarPontuacao(String nomeUsuario, Pontuacao pontuacao) throws ArmazenamentoException {
		usuarios.inserir(nomeUsuario, pontuacao);
		armazenador.escreverArquivo(usuarios, nomeArquivo);
	}
	
	public int recuperarPontosPorTipo(String nomeUsuario, TipoPontuacao tipoPontuacao) throws UsuarioInexistenteException {
		Usuario usuario = usuarios.buscarUsuario(nomeUsuario);
		int pontuacao = usuario.getPontuacaoPorTipo(tipoPontuacao);

		return pontuacao;
	}
	
	public List<TipoPontuacao> getOsTiposDePontosDoUsuario(String nomeUsuario) throws UsuarioInexistenteException{
		Usuario usuario = usuarios.buscarUsuario(nomeUsuario);
		List<TipoPontuacao> tiposDePontos = usuario.getTiposDePontos();
		
		return tiposDePontos;
	}
	
	public void limparTodoOHistorico(){
		armazenador.deletarArquivo(nomeArquivo);
		usuarios = armazenador.lerArquivo(nomeArquivo);
	}
	
	public boolean isUsuarioExistente(String nomeUsuario){
		return usuarios.isUsuarioExistente(nomeUsuario);
	}
	
	public ListagemUsuarios getUsuarios() {
		return usuarios;
	}

	public List<Usuario> getUsuariosComPontuacao() {
		return usuarios.getUsuariosComPontuacao();
	}

}
