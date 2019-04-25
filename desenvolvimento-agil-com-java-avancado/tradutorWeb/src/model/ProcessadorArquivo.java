package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import exception.LeituraArquivoException;

public class ProcessadorArquivo {
	
	private static List<Palavra> conteudoArquivo = new ArrayList<>();

	public static List<Palavra> processar(String caminhoArquivo) throws LeituraArquivoException{
		try{
			
			URL url = new URL(caminhoArquivo);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("->");
				conteudoArquivo.add(new Palavra(split[0], split[1]));
            }
            
            reader.close();
			return conteudoArquivo;
			
		}catch(IOException e){
			throw new LeituraArquivoException("Erro ao abrir arquivo. " + e);
		}
	}

}
