
public class Respeitoso implements FormatadorNome {

	private String titulo;
	
	public Respeitoso(Sexo sexo) {
		if(sexo.equals(Sexo.MASCULINO)){
			this.titulo = "Sr. ";
		}else{
			this.titulo = "Sra. ";
		}
	}
	
	@Override
	public String formatarNome(String nome, String sobrenome) {
		return titulo + sobrenome;
	}

}
