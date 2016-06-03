package jogo.palavras.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Classe utilitaria criada para gerar um arquivo padrão com as palavras
 * utilizadas no jogo, quando este for rodado a partir de um .jar 
 * @author lucas
 *
 */
public class ArquivoUtil {
	
	private static List<String> lines;

	public static String criaArquivoJogo(String filename) throws IOException{
		Path file = Paths.get(filename);
		Files.write(file, getLines(), Charset.forName("UTF-8"));
		
		return file.getFileName().toString();
	}
	
	private static List<String> getLines(){
		lines = Arrays.asList("libertarianismo", "livro", "clube", "escritório", "parque",
				"carro", "olhos", "rifle", "carteira", "celular",
				"caneta", "bolacha", "caderno", "mouse", "notebook",
				"mesa", "ventilador", "relógio", "porta", "cadeira");
		
		return lines;
	}
	
}
