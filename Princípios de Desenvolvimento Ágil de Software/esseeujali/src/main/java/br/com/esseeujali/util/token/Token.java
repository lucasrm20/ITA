package br.com.esseeujali.util.token;

import java.time.LocalDate;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.esseeujali.models.Usuario;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Token {

	private String token;
	
	@XmlTransient
	private Usuario usuario;
	
	@XmlTransient
	private LocalDate dataVencimento;
	
	public Token() {
	}
	
	public Token(Usuario usuario, Relogio relogio) {
		this.usuario = usuario;
		gerarToken();
		definirVencimento(relogio.getTempoBase());
	}
	
	private void gerarToken(){
		this.token = UUID.randomUUID().toString();
	}
	
	private void definirVencimento(LocalDate tempoBase){
		this.dataVencimento = tempoBase.plusDays(7);
	}
	
	public boolean isTokenExpirado(){
		return LocalDate.now().isAfter(this.dataVencimento);
	}
	
	//getters
	public String getToken() {
		return token;
	}
	
	public Usuario getUser() {
		return usuario;
	}
	
	public LocalDate getDataVencimento() {
		return dataVencimento;
	}
}
