package jogo.embaralhadores;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FabricaEmbaralhadores {

	private List<Embaralhador> embaralhadores;
	
	private void adicionarEmbaralhador(Embaralhador embaralhador) {
		embaralhadores.add(embaralhador);
	}

	public Embaralhador getEmbaralhadorAleatorio(){
		embaralhadores = new ArrayList<Embaralhador>();
		
		adicionarEmbaralhador(new EmbaralhadorInvertido());
		adicionarEmbaralhador(new EmbaralhadorAleatorio());
		
		Collections.shuffle(embaralhadores);
		
		return embaralhadores.get(0);
	}
	
}
