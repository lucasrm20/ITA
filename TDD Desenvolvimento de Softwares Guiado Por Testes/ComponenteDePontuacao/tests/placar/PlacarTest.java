package placar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import armazenamento.Armazenamento;
import exceptions.ArmazenamentoException;
import exceptions.UsuarioInexistenteException;
import models.Usuario;
import pontuacoes.EstrelaPoints;
import pontuacoes.MoedaPoints;
import pontuacoes.TipoPontuacao;

public class PlacarTest {
	
	/**
	 * OBSERVACAO:
	 * O uso de Mock Objects foi feito com o auxilio do framework Mockito (http://mockito.org/).
	 */

	private Placar placar;
	private Armazenamento mockArmazenamento;

	@Before
	public void before() {
		mockArmazenamento = mock(Armazenamento.class);
		placar = new Placar(mockArmazenamento);
	}
	
	@Test
	public void whenRegistrarThenRegistraUmTipoDePontoParaUmUsuario()
			throws ArmazenamentoException, UsuarioInexistenteException {
		
		when(mockArmazenamento.isUsuarioExistente("Lucas")).thenReturn(true);
		when(mockArmazenamento.recuperarPontosPorTipo("Lucas", TipoPontuacao.ESTRELA)).thenReturn(25);
		
		placar.registrar("Lucas", new EstrelaPoints(25));
		
		assertTrue(mockArmazenamento.isUsuarioExistente("Lucas"));
		assertEquals(25, mockArmazenamento.recuperarPontosPorTipo("Lucas", TipoPontuacao.ESTRELA));
	}
	
	@Test
	public void whenGetTodosOsPontosDeUsuarioThenRetornaUmMapaComTodosEles()
			throws UsuarioInexistenteException, ArmazenamentoException {
		
		List<TipoPontuacao> todosOspontos = new ArrayList<>();
		todosOspontos.add(TipoPontuacao.ESTRELA);
		todosOspontos.add(TipoPontuacao.MOEDA);
		when(mockArmazenamento.getOsTiposDePontosDoUsuario("Lucas")).thenReturn(todosOspontos);
		when(mockArmazenamento.recuperarPontosPorTipo("Lucas", TipoPontuacao.ESTRELA)).thenReturn(15);
		when(mockArmazenamento.recuperarPontosPorTipo("Lucas", TipoPontuacao.MOEDA)).thenReturn(5);
		
		placar.registrar("Lucas", new EstrelaPoints(15));
		placar.registrar("Lucas", new MoedaPoints(5));
		
		Map<TipoPontuacao, Integer> pontosDoUsuario = placar.getTodosOsPontosDoUsuario("Lucas");
		
		assertEquals(2, pontosDoUsuario.size());
		assertTrue(pontosDoUsuario.containsKey(TipoPontuacao.ESTRELA));
		assertEquals(new Integer(15), pontosDoUsuario.get(TipoPontuacao.ESTRELA));
		assertTrue(pontosDoUsuario.containsKey(TipoPontuacao.MOEDA));
		assertEquals(new Integer(5), pontosDoUsuario.get(TipoPontuacao.MOEDA));
	}
	
	@Test
	public void whenGetTodosOsPontosDeUsuarioThenNaoRetornaPontuacoesQueEleNaoPossua()
			throws UsuarioInexistenteException, ArmazenamentoException {
		
		List<TipoPontuacao> todosOspontos = new ArrayList<>();
		todosOspontos.add(TipoPontuacao.ESTRELA);
		when(mockArmazenamento.getOsTiposDePontosDoUsuario("Lucas")).thenReturn(todosOspontos);
		when(mockArmazenamento.recuperarPontosPorTipo("Lucas", TipoPontuacao.ESTRELA)).thenReturn(15);
		
		placar.registrar("Lucas", new EstrelaPoints(15));
		
		Map<TipoPontuacao, Integer> pontosDoUsuario = placar.getTodosOsPontosDoUsuario("Lucas");
		
		assertEquals(1, pontosDoUsuario.size());
		assertTrue(pontosDoUsuario.containsKey(TipoPontuacao.ESTRELA));
		assertEquals(new Integer(15), pontosDoUsuario.get(TipoPontuacao.ESTRELA));
		assertFalse(pontosDoUsuario.containsKey(TipoPontuacao.MOEDA));
	}
	
	@Test(expected=UsuarioInexistenteException.class)
	public void whenGetTodosOsPontosDeUmUsuarioInexistenteThenRetornaExcecao() throws UsuarioInexistenteException {
		doThrow(UsuarioInexistenteException.class).when(mockArmazenamento).getOsTiposDePontosDoUsuario("Lucas");
		placar.getTodosOsPontosDoUsuario("Lucas");
	}
	
	public void whenRankingPorTipoDePontuacaoThenRetornaUmRankingDeUsuarios() throws ArmazenamentoException{
		List<Usuario> usuariosComPontuacao = new ArrayList<>();
		Usuario usuario1 = new Usuario("Guerra");
		usuario1.adicionaUmaPontuacao(new EstrelaPoints(25));
		usuariosComPontuacao.add(usuario1);
		
		Usuario usuario2 = new Usuario("Fernandes");
		usuario2.adicionaUmaPontuacao(new EstrelaPoints(19));
		usuariosComPontuacao.add(usuario2);
		
		Usuario usuario3 = new Usuario("Rodrigo");
		usuario3.adicionaUmaPontuacao(new EstrelaPoints(17));
		usuariosComPontuacao.add(usuario3);
		
		when(mockArmazenamento.getUsuariosComPontuacao()).thenReturn(usuariosComPontuacao);
		
		placar.registrar("Fernandes", new EstrelaPoints(19));
		placar.registrar("Guerra", new EstrelaPoints(25));
		placar.registrar("Rodrigo", new EstrelaPoints(17));
		
		List<Usuario> ranking = placar.rankingPorTipoDePontuacao(TipoPontuacao.ESTRELA);
		
		assertEquals(3, ranking.size());
		assertEquals(new Usuario("Guerra"), ranking.get(0));
		assertEquals(new Usuario("Fernandes"), ranking.get(1));
		assertEquals(new Usuario("Rodrigo"), ranking.get(2));
	}
	
	public void whenRankingPorTipoDePontuacaoThenNaoRetornaUsuariosComOutrosTiposDePontos()
			throws ArmazenamentoException {
		
		List<Usuario> usuariosComPontuacao = new ArrayList<>();
		
		Usuario usuario2 = new Usuario("Fernandes");
		usuario2.adicionaUmaPontuacao(new EstrelaPoints(19));
		usuariosComPontuacao.add(usuario2);
		
		Usuario usuario3 = new Usuario("Rodrigo");
		usuario3.adicionaUmaPontuacao(new EstrelaPoints(17));
		usuariosComPontuacao.add(usuario3);
		
		when(mockArmazenamento.getUsuariosComPontuacao()).thenReturn(usuariosComPontuacao);
		
		placar.registrar("Guerra", new MoedaPoints(25));
		placar.registrar("Rodrigo", new EstrelaPoints(17));
		placar.registrar("Fernandes", new EstrelaPoints(19));
		
		List<Usuario> ranking = placar.rankingPorTipoDePontuacao(TipoPontuacao.ESTRELA);
		
		assertEquals(2, ranking.size());
		assertEquals(new Usuario("Fernandes"), ranking.get(0));
		assertEquals(new Usuario("Rodrigo"), ranking.get(1));
	}

}
