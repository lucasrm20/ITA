import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CarrinhoDeComprasTest {

	private CarrinhoDeCompras carrinhoDeCompras;

	@Before
	public void before(){
		carrinhoDeCompras = new CarrinhoDeCompras();
	}
	
	@Test
	public void adicionaUmNovoProdutoAoCarrinho() {
		Produto produto1 = new Produto(1, "Produto A", 25.0);
		carrinhoDeCompras.adicionaProduto(produto1, 1);
		
		assertTrue(carrinhoDeCompras.getProdutosAdicionados().containsKey(produto1));
	}
	
	@Test
	public void adicionaUmNovoProdutoAoCarrinhoComAQuantidadeCorreta() {
		Produto produto1 = new Produto(1, "Produto A", 25.0);
		carrinhoDeCompras.adicionaProduto(produto1, 5);
		
		assertTrue(carrinhoDeCompras.getProdutosAdicionados().containsKey(produto1));
		assertEquals(5, carrinhoDeCompras.getProdutosAdicionados().get(produto1), 0.00001);
	}
	
	@Test
	public void atualizaAQuantidadeDeUmProdutoNoCarrinho() {
		Produto produto1 = new Produto(1, "Produto A", 25.0);
		carrinhoDeCompras.adicionaProduto(produto1, 1);		
		carrinhoDeCompras.adicionaProduto(produto1, 5);
		
		assertEquals(6, carrinhoDeCompras.getProdutosAdicionados().get(produto1), 0.00001);
	}
	
	@Test
	public void removeUmProdutoCorretamente(){
		Produto produto1 = new Produto(1, "Produto A", 25.0);
		carrinhoDeCompras.adicionaProduto(produto1, 5);
		
		Produto produto2 = new Produto(1, "Produto B", 35.0);
		carrinhoDeCompras.removeProduto(produto2, 5);
		
		/** Pela lógica do exercício produtos com informações diferentes(nome e preco)
		 *  mas que possuam o mesmo codigo, são considerados iguais, logo
		 *  produto1 e produto2 são o mesmo produto
		 */
		
		assertFalse(carrinhoDeCompras.getProdutosAdicionados().containsKey(produto1));
		assertFalse(carrinhoDeCompras.getProdutosAdicionados().containsKey(produto2));
		assertEquals(0, carrinhoDeCompras.getProdutosAdicionados().size());
	}
	
	@Test
	public void naoRemoveUmProdutoQueNãoExistaNoCarrinho(){
		Produto produto1 = new Produto(1, "Produto A", 25.0);
		carrinhoDeCompras.adicionaProduto(produto1, 5);
		
		Produto produto2 = new ProdutoComTamanho(1, "Produto B", 35.0, 42);
		carrinhoDeCompras.removeProduto(produto2, 3);
		
		assertTrue(carrinhoDeCompras.getProdutosAdicionados().containsKey(produto1));
		assertEquals(1, carrinhoDeCompras.getProdutosAdicionados().size());
	}
	
	@Test
	public void calculaOValotTotalCorretamente(){
		Produto produto1 = new Produto(1, "Produto A", 10.0);
		carrinhoDeCompras.adicionaProduto(produto1, 10);
		
		ProdutoComTamanho produto2 = new ProdutoComTamanho(1, "Produto A Com Tamanho Especifico", 50.0, 35);
		carrinhoDeCompras.adicionaProduto(produto2, 2);
		
		assertEquals(200, carrinhoDeCompras.getValorTotal(), 0.00001);
		
	}

}
