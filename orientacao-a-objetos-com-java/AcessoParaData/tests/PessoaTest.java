import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class PessoaTest {
	
	private Relogio dataFixaParaTestes;
	
	@Before
	public void before(){
		/**
		 * Setei uma data no passado para demonstrar que o problema da passagem
		 * do tempo na realização dos testes foi resolvido.
		 * 
		 */
		dataFixaParaTestes = new Relogio(LocalDate.of(2012, Month.MAY, 31));	//31/05/2012
	}

	@Test
	public void retornaIdadeCorreta() {
		Date dataNascimento = new Date(LocalDate.of(1993, Month.OCTOBER, 25).toEpochDay());
		Pessoa pessoa = new Pessoa(dataNascimento , dataFixaParaTestes);
		
		assertEquals(18, pessoa.getIdade());
	}
	
	@Test
	public void retornaSignoEscorpiao() {
		Date dataNascimento = new Date(LocalDate.of(1993, Month.OCTOBER, 25).toEpochDay());
		Pessoa pessoa = new Pessoa(dataNascimento , dataFixaParaTestes);
		
		assertEquals(Signo.ESCORPIAO.toString(), pessoa.getSigno());
	}
	
	@Test
	public void retornaSignoGemeos() {
		Date dataNascimento = new Date(LocalDate.of(1957, Month.MAY, 24).toEpochDay());
		Pessoa pessoa = new Pessoa(dataNascimento , dataFixaParaTestes);
		
		assertEquals(Signo.GEMEOS.toString(), pessoa.getSigno());
	}
	
	@Test
	public void retornaSignoLibra() {
		Date dataNascimento = new Date(LocalDate.of(1987, Month.OCTOBER, 15).toEpochDay());
		Pessoa pessoa = new Pessoa(dataNascimento , dataFixaParaTestes);
		
		assertEquals(Signo.LIBRA.toString(), pessoa.getSigno());
	}
	
	@Test
	public void retornaSignoVirgem() {
		Date dataNascimento = new Date(LocalDate.of(1967, Month.AUGUST, 28).toEpochDay());
		Pessoa pessoa = new Pessoa(dataNascimento , dataFixaParaTestes);
		
		assertEquals(Signo.VIRGEM.toString(), pessoa.getSigno());
	}

}
