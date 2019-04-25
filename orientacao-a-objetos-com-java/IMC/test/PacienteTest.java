import static org.junit.Assert.*;

import org.junit.Test;

public class PacienteTest {

	@Test
	public void retornaBaixoPesoMuitoGrave() {
		Paciente paciente = new Paciente(30, 1.5);
		
		String msgEsperada = "Baixo peso muito grave = IMC abaixo de 16 kg/m²";
		
		assertEquals(13.33, paciente.calcularIMC(), 0.01);
		assertEquals(msgEsperada, paciente.diagnostico());
	}
	
	@Test
	public void retornaBaixoPesoGrave() {
		Paciente paciente = new Paciente(37, 1.5);
		
		String msgEsperada = "Baixo peso grave = IMC entre 16 e 16,99 kg/m²";
		
		assertEquals(16.44, paciente.calcularIMC(), 0.01);
		assertEquals(msgEsperada, paciente.diagnostico());
	}
	
	@Test
	public void retornaBaixoPeso() {
		Paciente paciente = new Paciente(40, 1.5);
		
		String msgEsperada = "Baixo peso = IMC entre 17 e 18,49 kg/m²";
		
		assertEquals(17.77, paciente.calcularIMC(), 0.01);
		assertEquals(msgEsperada, paciente.diagnostico());
	}
	
	@Test
	public void retornaPesoNormal() {
		Paciente paciente = new Paciente(45, 1.5);
		
		String msgEsperada = "Peso normal = IMC entre 18,50 e 24,99 kg/m²";
		
		assertEquals(20.0, paciente.calcularIMC(), 0.01);
		assertEquals(msgEsperada, paciente.diagnostico());
	}
	
	@Test
	public void retornaSobrepeso() {
		Paciente paciente = new Paciente(60, 1.5);
		
		String msgEsperada = "Sobrepeso = IMC entre 25 e 29,99 kg/m²";
		
		assertEquals(26.66, paciente.calcularIMC(), 0.01);
		assertEquals(msgEsperada, paciente.diagnostico());
	}
	
	@Test
	public void retornaObesidadeGrauI() {
		Paciente paciente = new Paciente(70, 1.5);
		
		String msgEsperada = "Obesidade grau I = IMC entre 30 e 34,99 kg/m²";
		
		assertEquals(31.11, paciente.calcularIMC(), 0.01);
		assertEquals(msgEsperada, paciente.diagnostico());
	}
	
	@Test
	public void retornaObesidadeGrauII() {
		Paciente paciente = new Paciente(80, 1.5);
		
		String msgEsperada = "Obesidade grau II = IMC entre 35 e 39,99 kg/m²";
		
		assertEquals(35.55, paciente.calcularIMC(), 0.01);
		assertEquals(msgEsperada, paciente.diagnostico());
	}
	
	@Test
	public void retornaObesidadeGrauIII() {
		Paciente paciente = new Paciente(90, 1.5);
		
		String msgEsperada = "Obesidade grau III (obesidade mórbida) = IMC igual ou maior que 40 kg/m²";
		
		assertEquals(40.0, paciente.calcularIMC(), 0.01);
		assertEquals(msgEsperada, paciente.diagnostico());
	}

}
