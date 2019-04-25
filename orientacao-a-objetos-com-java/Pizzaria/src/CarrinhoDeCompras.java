import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeCompras {

	private List<Pizza> pizzas = new ArrayList<>();
	
	public void adicionaPizza(Pizza pizza){
		if(pizza.getQuantidadeIngredientes() > 0){
			pizzas.add(pizza);
		}else{
			throw new IllegalArgumentException("Uma pizza deve ter pelo menos 1(UM) ingrediente!");
		}
	}

	public double getTotal() {
		double total = 0;
		
		for (Pizza pizza : pizzas) {
			total += pizza.getPreco();
		}
		
		return total;
	}
	
}
