package armazenamento;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.ArmazenamentoException;
import exceptions.UsuarioInexistenteException;
import models.Usuario;
import pontuacoes.EstrelaPoints;
import pontuacoes.MoedaPoints;
import pontuacoes.TipoPontuacao;

public class ArmazenamentoTest {
	
	private Armazenamento armazenamento;
	private String nomeArquivo = "pontuacoes.xml"; 

	@Before
	public void before(){
		armazenamento = new Armazenamento(new ArmazenadorXML(), nomeArquivo);
	}
	
	@After
	public void After(){
		armazenamento.limparTodoOHistorico();
	}

	@Test
	public void whenArmazenaUmaPontuacaoDeUmUsuarioThenMantemElaArmazenada() throws ArmazenamentoException {
		armazenamento.armazenarPontuacao("Lucas", new EstrelaPoints(10));
		
		assertEquals(1, armazenamento.getUsuarios().size());
		assertTrue(armazenamento.isUsuarioExistente("Lucas"));
	}
	
	@Test
	public void whenArmazenaOutraPontuacaoDeUmUsuarioThenMantemTodasArmazenadas() throws ArmazenamentoException {
		armazenamento.armazenarPontuacao("Lucas", new EstrelaPoints(10));
		armazenamento.armazenarPontuacao("Lucas", new MoedaPoints(50));
		
		assertEquals(1, armazenamento.getUsuarios().size());
		assertTrue(armazenamento.isUsuarioExistente("Lucas"));
	}
	
	@Test
	public void whenRecuperaUmaPontuacaoDeUmUsuarioThenRetornaEssaPontuacao()
			throws UsuarioInexistenteException, ArmazenamentoException {
		armazenamento.armazenarPontuacao("Lucas", new EstrelaPoints(10));
		
		assertEquals(10, armazenamento.recuperarPontosPorTipo("Lucas", TipoPontuacao.ESTRELA));
	}
	
	@Test
	public void whenRecuperaUmaPontuacaoInexistenteDeUmUsuarioThenRetornaZero()
			throws UsuarioInexistenteException, ArmazenamentoException {
		armazenamento.armazenarPontuacao("Lucas", new EstrelaPoints(10));
		
		assertEquals(0, armazenamento.recuperarPontosPorTipo("Lucas", TipoPontuacao.MOEDA));
	}
	
	@Test(expected=UsuarioInexistenteException.class)
	public void whenRecuperaUmaPontuacaoDeUmUsuarioInexistenteThenRetornaUsuarioInexistente()
			throws UsuarioInexistenteException {
		armazenamento.recuperarPontosPorTipo("Lucas", TipoPontuacao.MOEDA);
	}
	
	@Test
	public void whenRecuperaTodosOsUsuariosComPontuacaoThenRetornaUmaListaComEssesUsuarios()
			throws ArmazenamentoException {
		armazenamento.armazenarPontuacao("Lucas", new EstrelaPoints(10));
		armazenamento.armazenarPontuacao("Joao", new EstrelaPoints(12));
		armazenamento.armazenarPontuacao("Maria", new MoedaPoints(20));
		
		List<Usuario> usuariosComPontuacao = armazenamento.getUsuariosComPontuacao();
		assertEquals(3, usuariosComPontuacao.size());
		assertTrue(usuariosComPontuacao.contains(new Usuario("Lucas")));
		assertTrue(usuariosComPontuacao.contains(new Usuario("Joao")));
		assertTrue(usuariosComPontuacao.contains(new Usuario("Maria")));
	}
	
	@Test
	public void whenRecuperoOsTiposDePontoDeUmUsuarioThenRetornaTodosOsTiposDePontosDele()
			throws UsuarioInexistenteException, ArmazenamentoException {
		armazenamento.armazenarPontuacao("Lucas", new EstrelaPoints(10));
		armazenamento.armazenarPontuacao("Lucas", new MoedaPoints(20));
		
		List<TipoPontuacao> tiposDePontosDoUsuario = armazenamento.getOsTiposDePontosDoUsuario("Lucas");
		
		assertEquals(2, tiposDePontosDoUsuario.size());
		assertTrue(tiposDePontosDoUsuario.contains(TipoPontuacao.ESTRELA));
		assertTrue(tiposDePontosDoUsuario.contains(TipoPontuacao.MOEDA));
	}
	
	@Test(expected=UsuarioInexistenteException.class)
	public void whenRecuperoOsTiposDePontoDeUmUsuarioInexistenteThenRetornaExcessao()
			throws UsuarioInexistenteException {
		armazenamento.getOsTiposDePontosDoUsuario("Lucas");
	}

}
