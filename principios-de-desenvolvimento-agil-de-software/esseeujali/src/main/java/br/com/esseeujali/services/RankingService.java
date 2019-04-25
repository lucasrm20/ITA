package br.com.esseeujali.services;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import br.com.esseeujali.dao.UsuarioDAO;
import br.com.esseeujali.models.Usuario;
import br.com.esseeujali.models.rest.Ranking;

@Path("/ranking")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class RankingService {

	@GET
	public Ranking getRanking(@Context HttpServletRequest req){
		EntityManagerFactory factory = (EntityManagerFactory) req.getAttribute("entityManager");
		UsuarioDAO usuarioDAO = new UsuarioDAO(factory);
		
		List<Usuario> ranking = usuarioDAO.ranking();
		
		return new Ranking(ranking);
	}
	
}
