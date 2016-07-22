package pontuacoes;

public class MoedaPoints implements Pontuacao {
	
	private int pontuacao;
	private TipoPontuacao tipo;

	public MoedaPoints(int pontuacao) {
		this.pontuacao = pontuacao;
		this.tipo = TipoPontuacao.MOEDA;
	}

	@Override
	public TipoPontuacao getTipoPontuacao() {
		return tipo;
	}

	@Override
	public int getPontuacao() {
		return pontuacao;
	}

}
