
public class ProdutoComTamanho extends Produto {

	private int tamanho;
	
	public ProdutoComTamanho(Integer codigo, String nome, double preco, int tamanho) {
		super(codigo, nome, preco);
		this.tamanho = tamanho;
	}

	//hashcode e equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + tamanho;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoComTamanho other = (ProdutoComTamanho) obj;
		if (tamanho != other.tamanho)
			return false;
		return true;
	}
	
}
