package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.LeituraArquivoException;

public class Tradutor {
	
	private static final String ARQUIVO = "http://localhost:8080/tradutorWeb/palavras.txt";
	
	private Map<String, String> dicionarioIngles = new HashMap<>();
	private Map<String, String> dicionarioPortugues = new HashMap<>();

	public Tradutor() {
		try {
			List<Palavra> conteudoArquivo = ProcessadorArquivo.processar(ARQUIVO);
			gerarDicionarios(conteudoArquivo);
		} catch (LeituraArquivoException e) {
			e.printStackTrace();
		}
	}
	
	public String traduzir(String palavra){
		if(isPalavraExistente(dicionarioIngles, palavra))
			return dicionarioIngles.get(palavra);
		if(isPalavraExistente(dicionarioPortugues, palavra))
			return dicionarioPortugues.get(palavra);
		
		return palavra;
	}
	
	private void gerarDicionarios(List<Palavra> conteudoArquivo){
		for (Palavra palavra : conteudoArquivo) {
			dicionarioIngles.put(palavra.getIngles(), palavra.getPortugues());
			dicionarioPortugues.put(palavra.getPortugues(), palavra.getIngles());
		}
	}
	
	private boolean isPalavraExistente(Map<String, String> dicionario, String palavra){
		return dicionario.containsKey(palavra);
	}

}
