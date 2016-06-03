import static org.junit.Assert.*;

import org.junit.Test;

public class ProdutoComTamanhoTest {

	@Test
	public void produtosComMesmoCodigoEhTamanhoDevemSerIguais() {
		Produto produto1 = new ProdutoComTamanho(1, "Produto A", 120, 40);
		Produto produto2 = new ProdutoComTamanho(1, "Produto B", 20, 40);
		
		assertTrue(produto1.equals(produto2));
		assertTrue(produto2.equals(produto1));
	}
	
	@Test
	public void produtosComMesmoCodigoEhTamanhoDiferenteDevemSerDiferentes() {
		Produto produto1 = new ProdutoComTamanho(1, "Produto A", 120, 40);
		Produto produto2 = new ProdutoComTamanho(1, "Produto B", 20, 35);
		
		assertFalse(produto1.equals(produto2));
		assertFalse(produto2.equals(produto1));
	}
	
	@Test
	public void produtosComCodigoDiferenteEhTamanhoIgualDevemSerDiferentes() {
		Produto produto1 = new ProdutoComTamanho(1, "Produto A", 120, 40);
		Produto produto2 = new ProdutoComTamanho(2, "Produto B", 20, 40);
		
		assertFalse(produto1.equals(produto2));
		assertFalse(produto2.equals(produto1));
	}
	
	@Test
	public void produtosComCodigoEhTamanhoDiferentesDevemSerDiferentes() {
		Produto produto1 = new ProdutoComTamanho(1, "Produto A", 120, 40);
		Produto produto2 = new ProdutoComTamanho(2, "Produto B", 20, 35);
		
		assertFalse(produto1.equals(produto2));
		assertFalse(produto2.equals(produto1));
	}

}
