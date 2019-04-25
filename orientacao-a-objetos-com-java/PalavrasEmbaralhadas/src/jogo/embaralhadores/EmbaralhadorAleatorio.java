package jogo.embaralhadores;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EmbaralhadorAleatorio implements Embaralhador {

	@Override
	public String embaralharPalavra(String palavra) {
		List<String> letras = Arrays.asList(palavra.split(""));
	    Collections.shuffle(letras);
	    
	    StringBuilder embaralhada = new StringBuilder(palavra.length());
	    
	    for (String s : letras) {
	        embaralhada.append(s);
	    }
	    
	    return embaralhada.toString();
	}

}
