import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.verification.Times;

public class CaixaEletronicoTest {
	
	/**
	 * OBSERVACAO:
	 * O uso de Mock Objects foi feito com o auxilio do framework Mockito (http://mockito.org/).
	 */
	
	private Hardware mockHardware;
	private ServicoRemoto mockServicoRemoto;
	private CaixaEletronico caixaEletronico;

	@Before
	public void before(){
		mockHardware = mock(Hardware.class);
		mockServicoRemoto = mock(ServicoRemoto.class);
		
		caixaEletronico = new CaixaEletronico(mockHardware, mockServicoRemoto);
	}
	
	@Test
	public void whenLogaNoCaixaThenExibeMensagemDeSucesso() throws FalhaDeHardwareException {
		when(mockHardware.pegarNumeroDaContaCartao()).thenReturn("12345");
		
		ContaCorrente conta = new ContaCorrente("12345", 100.0);
		when(mockServicoRemoto.recuperarConta("12345")).thenReturn(conta);
		
		assertEquals("Usuário Autenticado", caixaEletronico.logar());
	}
	
	@Test
	public void whenFalhaAoLogarNoCaixaThenExibeMensagemDeFalha() throws FalhaDeHardwareException {
		when(mockHardware.pegarNumeroDaContaCartao()).thenThrow(new FalhaDeHardwareException("Falha"));
		
		assertEquals("Não foi possível autenticar o usuário", caixaEletronico.logar());
	}
	
	@Test
	public void wenConsultaSaldoThenRetornaAInformacao(){
		ContaCorrente conta = new ContaCorrente("12345", 500.0);
		
		assertEquals("O saldo é R$500,00", caixaEletronico.saldo(conta));
	}
	
	@Test
	public void whenSacaOValorPedidoThenEntregaODinheiroEPersisteAConta() throws FalhaDeHardwareException{
		ContaCorrente conta = new ContaCorrente("12345", 500.0);
		
		assertEquals("Retire seu dinheiro", caixaEletronico.sacar(conta, 250.0));
		assertEquals(250.0, conta.getSaldo(), 0);
		verify(mockServicoRemoto, new Times(1)).persistirConta("Saque de R$250,00 realizado");
	}
	
	@Test
	public void whenValorInsuficienteParaOSaqueThenNaoRealizaOperacaoENaoPersisteAConta() throws FalhaDeHardwareException{
		ContaCorrente conta = new ContaCorrente("12345", 500.0);
		
		assertEquals("Saldo insuficiente", caixaEletronico.sacar(conta, 750.0));
		assertEquals(500.0, conta.getSaldo(), 0);
		verify(mockServicoRemoto, new Times(0)).persistirConta("Saque de R$750,00 realizado");
	}
	
	@Test
	public void whenHouverProblemaNaEntregaDoDinheiroThenLancaExcessaoENaoPersisteAConta(){
		try {
			doThrow(new FalhaDeHardwareException("Falha na entrega do dinheiro")).when(mockHardware).entregarDinheiro();
			
			ContaCorrente conta = new ContaCorrente("12345", 500.0);
			caixaEletronico.sacar(conta, 100.0);
			
			fail();
		} catch (FalhaDeHardwareException e) {
			assertEquals("Falha na entrega do dinheiro", e.getMessage());
			verify(mockServicoRemoto, new Times(0)).persistirConta("Saque de R$100,00 realizado");
		}
	}
	
	@Test
	public void whenDepositaValorNaContaThenAdicionaOValorAContaEPersisteAConta() throws FalhaDeHardwareException{
		ContaCorrente conta = new ContaCorrente("12345", 500.0);
		
		assertEquals("Depósito recebido com sucesso", caixaEletronico.depositar(conta, 250.0));
		assertEquals(750.0, conta.getSaldo(), 0);
		verify(mockServicoRemoto, new Times(1)).persistirConta("Deposito de R$250,00 realizado");
	}
	
	@Test
	public void whenHouverfalhaNaLeituraDoEnvelopeDeDepositoThenLancaExcecaoENaoPersisteAConta(){
		try {
			doThrow(new FalhaDeHardwareException("Falha na leitura do envelope")).when(mockHardware).lerEnvelope();
			
			ContaCorrente conta = new ContaCorrente("12345", 500.0);
			caixaEletronico.depositar(conta, 250.0);
			
			fail();
		} catch (FalhaDeHardwareException e) {
			assertEquals("Falha na leitura do envelope", e.getMessage());
			verify(mockServicoRemoto, new Times(0)).persistirConta("Deposito de R$250,00 realizado");
		}
	}

}
