import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pizza {
	
	private static Map<String, Integer> ingredientesGastos = new HashMap<String, Integer>();
	
	private List<String> ingredientes = new ArrayList<>();
	
	public void adicionaIngrediente(String ingrediente){
		ingredientes.add(ingrediente);
		contabilizaIngredientes(ingrediente);
	}

	public double getPreco(){
		int qtdIngredientes = getQuantidadeIngredientes();
		
		if(qtdIngredientes < 3){
			return 15;
		}else if(qtdIngredientes < 6){
			return 20;
		}else{
			return 23;
		}
		
	}

	public int getQuantidadeIngredientes() {
		return ingredientes.size();
	}
	
	
	private static void contabilizaIngredientes(String ingrediente) {
		if(isIngredienteJaUtilizado(ingrediente)){
			int novaQuantidade = ingredientesGastos.get(ingrediente) + 1;
			
			ingredientesGastos.put(ingrediente, novaQuantidade);
		}else{
			ingredientesGastos.put(ingrediente, 1);
		}
	}
	
	private static boolean isIngredienteJaUtilizado(String ingrediente){
		return ingredientesGastos.containsKey(ingrediente);
	}
	
	public static Map<String, Integer> getIngredientesGastos() {
		return Collections.<String, Integer>unmodifiableMap(ingredientesGastos);
	}
	
	public static void zeraRegistrosDeIngredientesGastos(){
		ingredientesGastos = new HashMap<String, Integer>();
	}
	
}
