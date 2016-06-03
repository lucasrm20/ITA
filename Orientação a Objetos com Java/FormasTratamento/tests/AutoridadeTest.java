import static org.junit.Assert.*;

import org.junit.Test;

public class AutoridadeTest {

	@Test
	public void testaTratamentoInformal() {
		Autoridade autoridade = new Autoridade("Lucas", "Rodrigues", new Informal());
		
		assertEquals("Lucas", autoridade.getTratamento());
	}
	
	@Test
	public void testaTratamentoRespeitosoMasculino() {
		Autoridade autoridade = new Autoridade("Lucas", "Rodrigues", new Respeitoso(Sexo.MASCULINO));
		
		assertEquals("Sr. Rodrigues", autoridade.getTratamento());
	}
	
	@Test
	public void testaTratamentoRespeitosoFeminino() {
		Autoridade autoridade = new Autoridade("Maria", "Silva", new Respeitoso(Sexo.FEMININO));
		
		assertEquals("Sra. Silva", autoridade.getTratamento());
	}
	
	@Test
	public void testaTratamentoComTitulo() {
		Autoridade autoridade = new Autoridade("Lucas", "Rodrigues", new ComTitulo("Honorável"));
		
		assertEquals("Honorável Lucas Rodrigues", autoridade.getTratamento());
	}

}
