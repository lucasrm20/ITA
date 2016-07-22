package pontuacoes;

public class EstrelaPoints implements Pontuacao {
	
	private int pontuacao;
	private TipoPontuacao tipo;

	public EstrelaPoints(int pontuacao) {
		this.pontuacao = pontuacao;
		this.tipo = TipoPontuacao.ESTRELA;
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
