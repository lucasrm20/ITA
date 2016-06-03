package jogo.embaralhadores;

public class EmbaralhadorInvertido implements Embaralhador {

	@Override
	public String embaralharPalavra(String palavra) {
		StringBuffer stringBuffer = new StringBuffer(palavra);
		
		return stringBuffer.reverse().toString();
	}

}
