import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

public class PizzariaTest {
	
	@After
	public void after(){
		Pizza.zeraRegistrosDeIngredientesGastos();
	}

	@Test
	public void verificaSeOPrecoDaPizzaEstaCorretoComMenosDeTresIngredientes() {
		Pizza pizza = new Pizza();
		pizza.adicionaIngrediente("MUSSARELA");
		
		assertEquals(15.0, pizza.getPreco(), 0.00001);
	}
	
	@Test
	public void verificaSeOPrecoDaPizzaEstaCorretoEntreTresECincoIngredientes() {
		Pizza pizza = new Pizza();
		pizza.adicionaIngrediente("MUSSARELA");
		pizza.adicionaIngrediente("CALABRESA");
		pizza.adicionaIngrediente("BACON");
		
		assertEquals(20.0, pizza.getPreco(), 0.00001);
	}
	
	@Test
	public void verificaSeOPrecoDaPizzaEstaCorretoComMaisDeCincoIngredientes() {
		Pizza pizza = new Pizza();
		pizza.adicionaIngrediente("MUSSARELA");
		pizza.adicionaIngrediente("CALABRESA");
		pizza.adicionaIngrediente("BACON");
		pizza.adicionaIngrediente("AZEITONA");
		pizza.adicionaIngrediente("TOMATE");
		pizza.adicionaIngrediente("MILHO");
		
		assertEquals(23.0, pizza.getPreco(), 0.00001);
	}
	
	@Test
	public void verificaAContabilidadeDeIngredientesDeTodasAsPizzas(){
		Pizza pizza1 = new Pizza();
		pizza1.adicionaIngrediente("MUSSARELA");
		
		Pizza pizza2 = new Pizza();
		pizza2.adicionaIngrediente("MUSSARELA");
		pizza2.adicionaIngrediente("CALABRESA");
		
		assertEquals(2, Pizza.getIngredientesGastos().size());
		
		assertTrue(Pizza.getIngredientesGastos().containsKey("MUSSARELA"));
		assertTrue(Pizza.getIngredientesGastos().containsKey("CALABRESA"));
		
		assertEquals(2, Pizza.getIngredientesGastos().get("MUSSARELA").intValue());
		assertEquals(1, Pizza.getIngredientesGastos().get("CALABRESA").intValue());
	}

}
