package jogo.mecanicas;
import jogo.embaralhadores.Embaralhador;
import jogo.embaralhadores.FabricaEmbaralhadores;
import jogo.erros.LeituraArquivoException;
import jogo.palavras.BancoDePalavras;

public class MecanicaNormal implements MecanicaDoJogo {
	
	private FabricaEmbaralhadores fabricaEmbaralhadores;
	private BancoDePalavras bancoDePalavras;
	
	private int palavrasRestantes;
	private String palavra;
	private String palavraEmbaralhada;
	private int pontuacaoUsuario;
	
	public MecanicaNormal() throws LeituraArquivoException {
		this.fabricaEmbaralhadores = new FabricaEmbaralhadores();
		this.bancoDePalavras = new BancoDePalavras();
		this.palavrasRestantes = 10;
		this.pontuacaoUsuario = 0;
	}

	@Override
	public boolean isJogoNaoAcabou() {
		return this.palavrasRestantes > 0;
	}

	@Override
	public boolean isUsuarioAcertou(String chute) {
		this.palavrasRestantes -= 1;
		
		if(this.palavra.equals(chute)){
			this.pontuacaoUsuario += 1;
			
			return true;
		}
		
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
		return "NORMAL - Neste modo você receberá um total de " + this.palavrasRestantes +
				"\npalavras, uma de cada vez, para tentar acertar." +
				"\nTente fazer o máximo de pontos que puder.";
	}

}
