package jogo.mecanicas;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jogo.erros.LeituraArquivoException;

public class FabricaMecanicaDoJogo {

	private List<MecanicaDoJogo> mecanicasDeJogo;
	
	private void adicionarMecanica(MecanicaDoJogo mecanica) {
		mecanicasDeJogo.add(mecanica);
	}

	public MecanicaDoJogo getMecanicaParaOJogo() throws LeituraArquivoException{
		mecanicasDeJogo = new ArrayList<MecanicaDoJogo>();
		
		adicionarMecanica(new MecanicaVidas());
		adicionarMecanica(new MecanicaNormal());
		
		Collections.shuffle(mecanicasDeJogo);
		
		return mecanicasDeJogo.get(0);
	}
	
}
