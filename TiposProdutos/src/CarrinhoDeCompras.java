import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CarrinhoDeCompras {

	private Map<Produto, Integer> produtosAdicionados = new HashMap<Produto, Integer>();
	
	public void adicionaProduto(Produto produto, Integer quantidade){
		if(isProdutoNoCarrinho(produto)){
			Integer novaQuantidade = produtosAdicionados.get(produto) + quantidade;
			produtosAdicionados.put(produto, novaQuantidade);
		}else{
			produtosAdicionados.put(produto, quantidade);
		}
	}
	
	public void removeProduto(Produto produto, int quantidade){
		if(isProdutoNoCarrinho(produto)){
			Integer novaQuantidade = produtosAdicionados.get(produto) - quantidade;
			
			if(novaQuantidade > 0){
				produtosAdicionados.put(produto, novaQuantidade);
			}else{				
				produtosAdicionados.remove(produto);
			}
		}
	}
	
	public double getValorTotal(){
		double total = 0;
		
		for (Produto produto : produtosAdicionados.keySet()) {
			total += produtosAdicionados.get(produto) * produto.getPreco();
		}
		
		return total;
	}

	private boolean isProdutoNoCarrinho(Produto produto) {
		return produtosAdicionados.containsKey(produto);
	}
	
	public Map<Produto, Integer> getProdutosAdicionados() {
		return Collections.unmodifiableMap(produtosAdicionados);
	}
	
}
