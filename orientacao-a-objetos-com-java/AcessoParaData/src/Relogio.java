import java.time.LocalDate;

public class Relogio {
	
	private long tempoReferencia;
	
	/**
	 * Instanciando sem argumentos, pega-se a data atual
	 */
	public Relogio() {
		this.tempoReferencia = agora();
	}
	
	/**
	 * Instanciando usando o parametro, ele sera usado como referencia 
	 * @param dataReferencia
	 */
	public Relogio(LocalDate dataReferencia){
		this.tempoReferencia = dataReferencia.toEpochDay();
	}
	
	private long agora(){
		return System.currentTimeMillis();
	}
	
	public long getTempoReferencia() {
		return tempoReferencia;
	}
	
}
