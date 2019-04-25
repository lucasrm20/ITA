package jogo.palavras.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArquivoUtilTest {
	
	private String nomeArquivo;
	
	@Before
	public void before(){
		nomeArquivo = "testeCriacao.txt";
	}

	@After
	public void After(){
		File file = new File(nomeArquivo);
		
		if(file.exists()){
			file.delete();
		}
	}

	@Test
	public void criaArquivoComSucesso() throws IOException {
		String nomeArquivoRetornado = ArquivoUtil.criaArquivoJogo(nomeArquivo);
		
		assertTrue(nomeArquivo.equals(nomeArquivoRetornado));
	}

}
