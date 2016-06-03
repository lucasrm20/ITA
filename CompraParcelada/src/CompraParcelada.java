
public class CompraParcelada extends Compra {

	private int parcelas;
	private double jurosMensal;

	public CompraParcelada(double valor, int parcelas, double jurosMensal) {
		super(valor);
		this.parcelas = parcelas;
		this.jurosMensal = jurosMensal;
	}
	
	@Override
	public double total() {
		double total = 0;
		
		total = valor * Math.pow((1 + jurosMensal), parcelas);
		
		return total;
	}

}
