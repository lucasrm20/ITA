package br.com.esseeujali.util.token;

import java.time.LocalDate;

public class Relogio {

	private LocalDate tempoBase;
	
	public Relogio(){
		this.tempoBase = LocalDate.now();
	}
	
	public Relogio(LocalDate tempoBase){
		this.tempoBase = tempoBase;
	}
	
	public LocalDate getTempoBase() {
		return tempoBase;
	}
	
}
