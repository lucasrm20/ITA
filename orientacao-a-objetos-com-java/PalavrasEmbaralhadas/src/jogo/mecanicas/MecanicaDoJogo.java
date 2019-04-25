package jogo.mecanicas;

import jogo.erros.LeituraArquivoException;

public interface MecanicaDoJogo {

	public boolean isJogoNaoAcabou();
	
	public boolean isUsuarioAcertou(String chute);
	
	public int getPontuacaoFinal();
	
	public String sortearPalavra() throws LeituraArquivoException;
	
	public String getDescricaoDaRegra();
	
}
