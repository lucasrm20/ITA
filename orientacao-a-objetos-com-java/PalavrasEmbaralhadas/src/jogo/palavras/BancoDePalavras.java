package jogo.palavras;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import jogo.erros.LeituraArquivoException;
import jogo.palavras.util.ArquivoUtil;

public class BancoDePalavras {
	
	private String caminhoArquivo;
	private List<String> palavrasNoArquivo = new ArrayList<String>();
	
	public BancoDePalavras() throws LeituraArquivoException {
		try{
			String nomePadrao = "palavrasJogo.txt";
			
			if(new File(nomePadrao).exists()){
				this.caminhoArquivo = nomePadrao;
			}else{
				this.caminhoArquivo = ArquivoUtil.criaArquivoJogo(nomePadrao);
			}
		}catch(IOException e){
			throw new LeituraArquivoException("Erro ao criar a lista de palavras. " + e);
		}
	}
	
	public BancoDePalavras(String caminhoArquivo){
		try{
			URL url = getClass().getResource(caminhoArquivo);
			this.caminhoArquivo = url.getPath();
		}catch(NullPointerException e){
			this.caminhoArquivo = caminhoArquivo;
		}
	}

	public String getPalavraAleatoria() throws LeituraArquivoException{
		if(palavrasNoArquivo.isEmpty()){			
			processarArquivo();
		}
		
		String sorteada = sortearPalavra();
		palavrasNoArquivo.remove(sorteada);
		
		return sorteada;
	}
	
	private String sortearPalavra() {
		Collections.shuffle(palavrasNoArquivo);
		
		return palavrasNoArquivo.get(0);
	}

	private void processarArquivo() throws LeituraArquivoException{
		try{
			
			File file = new File(caminhoArquivo);
			Scanner sc = new Scanner(file);
			
			isArquivoVazio(sc);
			
			while (sc.hasNextLine()){
				String linha = sc.nextLine();
				palavrasNoArquivo.add(linha);
			}
			
		}catch(IOException | NullPointerException e){
			throw new LeituraArquivoException("Erro ao abrir arquivo. " + e);
		}
	}
	
	private static void isArquivoVazio(Scanner sc) throws LeituraArquivoException{
		if(!sc.hasNextLine())
			throw new LeituraArquivoException("Arquivo vazio.");
	}
}
