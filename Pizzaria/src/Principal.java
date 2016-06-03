
public class Principal {

	public static void main(String[] args) {
		
		Pizza pizza1 = new Pizza();
		pizza1.adicionaIngrediente("PEPPERONI");
		
		Pizza pizza2 = new Pizza();
		pizza2.adicionaIngrediente("CALABRESA");
		pizza2.adicionaIngrediente("AZEITONA");
		pizza2.adicionaIngrediente("TOMATE");
		
		Pizza pizza3 = new Pizza();
		pizza3.adicionaIngrediente("MUSSARELA");
		pizza3.adicionaIngrediente("PEPPERONI");
		
		CarrinhoDeCompras carrinhoDeCompras = new CarrinhoDeCompras();
		carrinhoDeCompras.adicionaPizza(pizza1);
		carrinhoDeCompras.adicionaPizza(pizza2);
		carrinhoDeCompras.adicionaPizza(pizza3);
		
		System.out.println("TOTAL: " + carrinhoDeCompras.getTotal());
		
		exibirInfoIngredientes();
		
	}

	private static void exibirInfoIngredientes() {
		for (String key : Pizza.getIngredientesGastos().keySet()) {
			System.out.println("Ingrediente: " + key + " - Quantidade: "
					+ Pizza.getIngredientesGastos().get(key));
		}
	}
	
}
