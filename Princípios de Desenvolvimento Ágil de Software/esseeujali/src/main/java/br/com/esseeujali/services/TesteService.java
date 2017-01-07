package br.com.esseeujali.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
@Produces({MediaType.TEXT_PLAIN})
public class TesteService {

	@GET
	public String teste(){
		return "Web Service Rodando!";
	}
	
}
