import static org.junit.Assert.*;

import org.junit.Test;

public class ProdutoTest {

	@Test
	public void produtosComMesmoCodigoDevemSerIguais() {
		Produto produto1 = new Produto(1, "Produto A", 120);
		Produto produto2 = new Produto(1, "Produto B", 20);
		
		assertTrue(produto1.equals(produto2));
		assertTrue(produto2.equals(produto1));
	}
	
	@Test
	public void produtosComCodigoDiferenteDevemSerDiferentes() {
		Produto produto1 = new Produto(1, "Produto A", 120);
		Produto produto2 = new Produto(2, "Produto B", 20);
		
		assertFalse(produto1.equals(produto2));
		assertFalse(produto2.equals(produto1));
	}

}
