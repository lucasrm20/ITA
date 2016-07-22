package models;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import models.Usuario;
import pontuacoes.EstrelaPoints;
import pontuacoes.MoedaPoints;
import pontuacoes.TipoPontuacao;

public class UsuarioTest {

	@Test
	public void whenAdicionaUmaPontuacaoThenGuardaEstaPontuacao() {
		Usuario usuario = new Usuario("Lucas");
		usuario.adicionaUmaPontuacao(new MoedaPoints(10));
		
		assertEquals(1, usuario.getPontuacoes().size());
		assertTrue(usuario.isPontuacaoExistente(new MoedaPoints(10)));
	}
	
	@Test
	public void whenAdicionaPontuacoesDiferentesThenGuardaTodas() {
		Usuario usuario = new Usuario("Lucas");
		usuario.adicionaUmaPontuacao(new MoedaPoints(10));
		usuario.adicionaUmaPontuacao(new EstrelaPoints(10));
		
		assertEquals(2, usuario.getPontuacoes().size());
		assertTrue(usuario.isPontuacaoExistente(new MoedaPoints(10)));
		assertTrue(usuario.isPontuacaoExistente(new EstrelaPoints(10)));
	}
	
	@Test
	public void whenAlteraUmaPontuacaoThenAtualizaAntiga() {
		Usuario usuario = new Usuario("Lucas");
		usuario.adicionaUmaPontuacao(new MoedaPoints(10));
		usuario.adicionaUmaPontuacao(new MoedaPoints(50));
		
		assertEquals(1, usuario.getPontuacoes().size());
		assertTrue(usuario.isPontuacaoExistente(new MoedaPoints(50)));
		assertFalse(usuario.isPontuacaoExistente(new MoedaPoints(10)));
	}
	
	@Test
	public void whenBuscaUmaPontuacaoEspecificaThenRetornaTrueSeElaExistir(){
		Usuario usuario = new Usuario("Lucas");
		usuario.adicionaUmaPontuacao(new MoedaPoints(10));
		
		assertTrue(usuario.isPontuacaoExistente(new MoedaPoints(10)));
	}
	
	@Test
	public void whenBuscaUmaPontuacaoEspecificaInexistenteThenRetornaFalse(){
		Usuario usuario = new Usuario("Lucas");
		
		assertFalse(usuario.isPontuacaoExistente(new MoedaPoints(10)));
	}
	
	@Test
	public void whenUsuarioPossuiUmTipoDePontuacaoThenRetornaTrue(){
		Usuario usuario = new Usuario("Lucas");
		usuario.adicionaUmaPontuacao(new MoedaPoints(10));
		usuario.adicionaUmaPontuacao(new EstrelaPoints(10));
		
		assertTrue(usuario.isPossuiOTipoDePontuacao(TipoPontuacao.MOEDA));
		assertTrue(usuario.isPossuiOTipoDePontuacao(TipoPontuacao.ESTRELA));
	}
	
	@Test
	public void whenUsuarioNaoPossuiUmTipoDePontuacaoThenRetornaFalse(){
		Usuario usuario = new Usuario("Lucas");
		
		assertFalse(usuario.isPossuiOTipoDePontuacao(TipoPontuacao.MOEDA));
		assertFalse(usuario.isPossuiOTipoDePontuacao(TipoPontuacao.ESTRELA));
	}
	
	@Test
	public void whenPedeOsTiposDePontosQueUmUsuarioPossuiThenRetornaUmaListaDosTipos(){
		Usuario usuario = new Usuario("Lucas");
		usuario.adicionaUmaPontuacao(new MoedaPoints(10));
		usuario.adicionaUmaPontuacao(new EstrelaPoints(25));
		
		List<TipoPontuacao> tiposDePontos = usuario.getTiposDePontos();
		
		assertEquals(2, tiposDePontos.size());
		assertTrue(tiposDePontos.contains(TipoPontuacao.MOEDA));
		assertTrue(tiposDePontos.contains(TipoPontuacao.ESTRELA));
	}
	
	@Test
	public void whenPedeOsTiposDePontosDeUmUsuarioQueNaoPossuiNenhumThenRetornaUmaListaVazia(){
		Usuario usuario = new Usuario("Lucas");
		
		List<TipoPontuacao> tiposDePontos = usuario.getTiposDePontos();
		
		assertEquals(0, tiposDePontos.size());
		assertFalse(tiposDePontos.contains(TipoPontuacao.MOEDA));
		assertFalse(tiposDePontos.contains(TipoPontuacao.ESTRELA));
	}

}
