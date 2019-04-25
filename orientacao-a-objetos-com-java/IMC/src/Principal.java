
public class Principal {
	
	public static void main(String[] args) {
		
		Paciente paciente1 = new Paciente(70, 1.78);
		Paciente paciente2 = new Paciente(85, 1.70);
		Paciente paciente3 = new Paciente(56, 1.58);
		
		System.out.println(paciente1.calcularIMC());
		System.out.println(paciente1.diagnostico());
		
		System.out.println(paciente2.calcularIMC());
		System.out.println(paciente2.diagnostico());
		
		System.out.println(paciente3.calcularIMC());
		System.out.println(paciente3.diagnostico());
		
	}
	
}
