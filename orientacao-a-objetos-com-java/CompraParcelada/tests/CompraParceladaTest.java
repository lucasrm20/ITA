import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CompraParceladaTest {
	
	private double valorCompra;
	private double taxa;

	@Before
	public void before(){
		valorCompra = 1000.0;
		taxa = 0.05; // 5%
	}
	
	@Test
	public void umaParcela() {
		Compra compra = new CompraParcelada(valorCompra, 1, taxa);
		
		assertEquals(1050, compra.total(), 0.01);
	}
	
	@Test
	public void duasParcelas() {
		Compra compra = new CompraParcelada(valorCompra, 2, taxa);
		
		assertEquals(1102.50, compra.total(), 0.01);
	}
	
	@Test
	public void cincoParcelas() {
		Compra compra = new CompraParcelada(valorCompra, 5, taxa);
		
		assertEquals(1276.28, compra.total(), 0.01);
	}
	
	@Test
	public void dezParcelas() {
		Compra compra = new CompraParcelada(valorCompra, 10, taxa);
		
		assertEquals(1628.89, compra.total(), 0.01);
	}

}
