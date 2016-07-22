package armazenamento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import exceptions.ArmazenamentoException;
import models.ListagemUsuarios;
import pontuacoes.EstrelaPoints;
import pontuacoes.MoedaPoints;

public class ArmazenadorXMLTest {
	
	private Armazenador armazenador;

	@Before
	public void before(){
		armazenador = new ArmazenadorXML();
	}

	@Test
	public void leUmArquivoXML() {
		ListagemUsuarios listagemUsuarios = armazenador.lerArquivo("TesteLeitura.xml");
		
		assertEquals(3, listagemUsuarios.size());
		assertTrue(listagemUsuarios.isUsuarioExistente("Lucas"));
		assertTrue(listagemUsuarios.isUsuarioExistente("Joao"));
		assertTrue(listagemUsuarios.isUsuarioExistente("Maria"));
	}
	
	@Test
	public void criaArquivoXML() throws ArmazenamentoException{		
		ListagemUsuarios listagemUsuarios = new ListagemUsuarios();
		listagemUsuarios.inserir("Lucas", new EstrelaPoints(15));
		listagemUsuarios.inserir("Lucas", new MoedaPoints(15));
		
		armazenador.escreverArquivo(listagemUsuarios, "TesteEscrita.xml");
		
		assertTrue(new File("TesteEscrita.xml").exists());
		
		armazenador.deletarArquivo("TesteEscrita.xml");
	}

}
