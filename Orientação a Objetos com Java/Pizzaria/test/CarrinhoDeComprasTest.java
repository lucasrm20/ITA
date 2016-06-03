import static org.junit.Assert.*;

import org.junit.Test;

public class CarrinhoDeComprasTest {

	@Test
	public void verificaSeOPrecoDasPizzasEhSomadoCorretamente() {
		Pizza pizza1 = new Pizza();
		pizza1.adicionaIngrediente("MUSSARELA");
		pizza1.adicionaIngrediente("CALABRESA");
		pizza1.adicionaIngrediente("BACON");
		
		Pizza pizza2 = new Pizza();
		pizza2.adicionaIngrediente("MUSSARELA");
		
		CarrinhoDeCompras carrinhoDeCompras = new CarrinhoDeCompras();
		carrinhoDeCompras.adicionaPizza(pizza1);
		carrinhoDeCompras.adicionaPizza(pizza2);
		
		assertEquals(35.0, carrinhoDeCompras.getTotal(), 0.00001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void verificaSeUmaPizzaSemIngredientesNaoPodeSerAdicionada(){
		Pizza pizza = new Pizza();
		
		CarrinhoDeCompras carrinhoDeCompras = new CarrinhoDeCompras();
		carrinhoDeCompras.adicionaPizza(pizza);
		
	}

}
