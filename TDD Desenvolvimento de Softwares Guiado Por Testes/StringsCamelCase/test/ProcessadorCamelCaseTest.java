import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.List;

import org.junit.Test;

public class ProcessadorCamelCaseTest {

	@Test
	public void retornaUmaListaDeStringsAoProcessarCadeiaDeCaracteres() {
		List<String> retorno = ProcessadorCamelCase.converterCamelCase("nome");
		
		assertFalse(retorno.isEmpty());
	}
	
	@Test(expected=InvalidParameterException.class)
	public void lancaExcessaoAoReceberUmaCadeiaVazia(){
		ProcessadorCamelCase.converterCamelCase("");
		fail();
	}
	
	@Test
	public void processaUmaUnicaStringTodaMinuscula(){
		List<String> retorno = ProcessadorCamelCase.converterCamelCase("nome");
		
		assertEquals(1, retorno.size());
		assertEquals("nome", retorno.get(0));
	}
	
	@Test
	public void processaUmaUnicaStringCamelCase(){
		List<String> retorno = ProcessadorCamelCase.converterCamelCase("Nome");
		
		assertEquals(1, retorno.size());
		assertEquals("nome", retorno.get(0));
	}
	
	@Test
	public void processaUmaStringMinusculaEUmaCamelCase(){
		List<String> retorno = ProcessadorCamelCase.converterCamelCase("nomeComposto");
		
		assertEquals(2, retorno.size());
		assertEquals("nome", retorno.get(0));
		assertEquals("composto", retorno.get(1));
	}
	
	@Test
	public void processaDuasStringsCamelCase(){
		List<String> retorno = ProcessadorCamelCase.converterCamelCase("NomeComposto");
		
		assertEquals(2, retorno.size());
		assertEquals("nome", retorno.get(0));
		assertEquals("composto", retorno.get(1));
	}
	
	@Test
	public void processaUmaSigla(){
		List<String> retorno = ProcessadorCamelCase.converterCamelCase("CPF");
		
		assertEquals(1, retorno.size());
		assertEquals("CPF", retorno.get(0));
	}
	
	@Test
	public void processaUmaStringEUmaSigla(){
		List<String> retorno = ProcessadorCamelCase.converterCamelCase("numeroCPF");
		
		assertEquals(2, retorno.size());
		assertEquals("numero", retorno.get(0));
		assertEquals("CPF", retorno.get(1));
	}
	
	@Test
	public void processaUmaStringEUmaSiglaEUmaString(){
		List<String> retorno = ProcessadorCamelCase.converterCamelCase("numeroCPFContribuinte");
		
		assertEquals(3, retorno.size());
		assertEquals("numero", retorno.get(0));
		assertEquals("CPF", retorno.get(1));
		assertEquals("contribuinte", retorno.get(2));
	}
	
	@Test
	public void processaUmaStringUmNumeroEUmaString(){
		List<String> retorno = ProcessadorCamelCase.converterCamelCase("recupera10Primeiros");
		
		assertEquals(3, retorno.size());
		assertEquals("recupera", retorno.get(0));
		assertEquals("10", retorno.get(1));
		assertEquals("primeiros", retorno.get(2));
	}
	
	@Test(expected=InvalidParameterException.class)
	public void lancaExcessaoAoReceberUmaCadeiaIniciadaComNumeros(){
		ProcessadorCamelCase.converterCamelCase("10Primeiros");
		fail();
	}
	
	@Test(expected=InvalidParameterException.class)
	public void lancaExcessaoAoReceberUmaCadeiaQueContenhaCaracteresEspeciais(){
		ProcessadorCamelCase.converterCamelCase("nome#Composto");
		fail();
	}
		
}
