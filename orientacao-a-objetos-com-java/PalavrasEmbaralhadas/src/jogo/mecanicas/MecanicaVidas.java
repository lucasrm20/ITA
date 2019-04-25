package jogo.mecanicas;
import jogo.embaralhadores.Embaralhador;
import jogo.embaralhadores.FabricaEmbaralhadores;
import jogo.erros.LeituraArquivoException;
import jogo.palavras.BancoDePalavras;

public class MecanicaVidas implements MecanicaDoJogo {
	
	private FabricaEmbaralhadores fabricaEmbaralhadores;
	private BancoDePalavras bancoDePalavras;
	
	private int vidas;
	private String palavra;
	private String palavraEmbaralhada;
	private int pontuacaoUsuario;
	
	public MecanicaVidas() throws LeituraArquivoException {
		this.fabricaEmbaralhadores = new FabricaEmbaralhadores();
		this.bancoDePalavras = new BancoDePalavras();
		this.vidas = 3;
		this.pontuacaoUsuario = 0;
	}

	@Override
	public boolean isJogoNaoAcabou() {
		return this.vidas > 0;
	}

	@Override
	public boolean isUsuarioAcertou(String chute) {		
		if(this.palavra.equals(chute)){
			this.pontuacaoUsuario += 1;
			return true;
		}
		
		this.vidas -= 1;
		return false;
	}

	@Override
	public int getPontuacaoFinal() {
		return this.pontuacaoUsuario;
	}
	
	@Override
	public String sortearPalavra() throws LeituraArquivoException {
		this.palavra = bancoDePalavras.getPalavraAleatoria();
		
		Embaralhador embaralhador = fabricaEmbaralhadores.getEmbaralhadorAleatorio();
		this.palavraEmbaralhada = embaralhador.embaralharPalavra(this.palavra);
		
		return this.palavraEmbaralhada;
	}
	
	@Override
	public String getDescricaoDaRegra() {
		return "VIDAS - Neste modo você tem um total de " + this.vidas + " vidas e a cada\n" +
				"erro você perde uma vida." +
				"\nTente fazer o máximo de pontos que puder.";
	}

}
