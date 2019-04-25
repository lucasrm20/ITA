import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Pessoa {

	/**
	 * Mantive o atributo dataNascimento como Date como pede o exercicio,
	 * porem para realizacao das logicas do exercicio eu utilizo a API de datas do Java 8
	 * 
	 */
	private Date dataNascimento;
	private Relogio relogio;
	
	public Pessoa(Date dataNascimento, Relogio relogio) {
		this.dataNascimento = dataNascimento;
		this.relogio = relogio;
	}

	public int getIdade(){
		LocalDate dataReferencia = LocalDate.ofEpochDay(relogio.getTempoReferencia());
		LocalDate nascimento = LocalDate.ofEpochDay(dataNascimento.getTime());
		
		return (int) nascimento.until(dataReferencia, ChronoUnit.YEARS);
	}
	
	public String getSigno(){
		LocalDate nascimento = LocalDate.ofEpochDay(dataNascimento.getTime());
		
		if(Signo.ARIES.isDesteSigno(nascimento)){
			return Signo.ARIES.toString();
		}else if(Signo.TOURO.isDesteSigno(nascimento)){
			return Signo.TOURO.toString();
		}else if(Signo.GEMEOS.isDesteSigno(nascimento)){
			return Signo.GEMEOS.toString();
		}else if(Signo.CANCER.isDesteSigno(nascimento)){
			return Signo.CANCER.toString();
		}else if(Signo.LEAO.isDesteSigno(nascimento)){
			return Signo.LEAO.toString();
		}else if(Signo.VIRGEM.isDesteSigno(nascimento)){
			return Signo.VIRGEM.toString();
		}else if(Signo.LIBRA.isDesteSigno(nascimento)){
			return Signo.LIBRA.toString();
		}else if(Signo.ESCORPIAO.isDesteSigno(nascimento)){
			return Signo.ESCORPIAO.toString();
		}else if(Signo.SAGITARIO.isDesteSigno(nascimento)){
			return Signo.SAGITARIO.toString();
		}else if(Signo.CAPRICORNIO.isDesteSigno(nascimento)){
			return Signo.CAPRICORNIO.toString();
		}else if(Signo.AQUARIO.isDesteSigno(nascimento)){
			return Signo.AQUARIO.toString();
		}else{
			return Signo.PEIXES.toString();
		}
		
	}
	
}
