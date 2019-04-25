
public class ContaCorrente {

	private String numero;
	private Double saldo;
	
	public ContaCorrente(String numero, Double saldo) {
		this.numero = numero;
		this.saldo = saldo;
	}
	
	public ContaCorrente() {
	}
	
	public String getNumero() {
		return numero;
	}
	public Double getSaldo() {
		return saldo;
	}

	public double sacar(double valor) {
		if(valor <= this.saldo){
			this.saldo -= valor;
			return valor;
		}
		
		return 0;
	}

	public void deposita(double valor) {
		this.saldo += valor;
	}
	
}
