
public interface Hardware {

	public String pegarNumeroDaContaCartao() throws FalhaDeHardwareException;
	public void entregarDinheiro() throws FalhaDeHardwareException;
	public void lerEnvelope() throws FalhaDeHardwareException;
	
}
