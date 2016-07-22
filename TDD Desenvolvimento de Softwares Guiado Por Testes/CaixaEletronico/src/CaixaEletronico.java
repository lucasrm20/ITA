import java.text.DecimalFormat;

public class CaixaEletronico {

	private Hardware hardware;
	private ServicoRemoto servicoRemoto;

	public CaixaEletronico(Hardware Hardware, ServicoRemoto ServicoRemoto) {
		hardware = Hardware;
		servicoRemoto = ServicoRemoto;
	}

	public String logar() {
		try {
			String numeroDaConta = hardware.pegarNumeroDaContaCartao();
			ContaCorrente conta = servicoRemoto.recuperarConta(numeroDaConta);
			
			return "Usuário Autenticado";
		} catch (FalhaDeHardwareException e) {
			return "Não foi possível autenticar o usuário";
		}
	}

	public String saldo(ContaCorrente conta) {
		return String.format("O saldo é " + formataValorEmReais(conta.getSaldo()));
	}

	public String sacar(ContaCorrente conta, double valor) throws FalhaDeHardwareException {
		double saque = conta.sacar(valor);
		
		if(saque == 0)
			return "Saldo insuficiente";
		
		hardware.entregarDinheiro();
		servicoRemoto.persistirConta("Saque de " + formataValorEmReais(valor) + " realizado");
		return "Retire seu dinheiro";
	}

	public String depositar(ContaCorrente conta, double valor) throws FalhaDeHardwareException {
		hardware.lerEnvelope();
		conta.deposita(valor);
		servicoRemoto.persistirConta("Deposito de " + formataValorEmReais(valor) + " realizado");
		return "Depósito recebido com sucesso";
	}

	public String formataValorEmReais(double valor){
		DecimalFormat df = new DecimalFormat("0.00");
		return String.format("R$" + df.format(valor));
	}

}
