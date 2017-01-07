package br.com.esseeujali.services;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.esseeujali.dao.UsuarioDAO;
import br.com.esseeujali.exceptions.UsuarioNaoEncontradoException;
import br.com.esseeujali.models.Usuario;
import br.com.esseeujali.models.rest.Login;
import br.com.esseeujali.util.token.Relogio;
import br.com.esseeujali.util.token.Token;
import br.com.esseeujali.util.token.TokenManager;

@Path("/auth")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class LoginService {

	@Path("/login")
	@POST
	public Response login(Login login, @Context HttpServletRequest req){
		EntityManagerFactory factory = (EntityManagerFactory) req.getAttribute("entityManager");
		UsuarioDAO usuarioDAO = new UsuarioDAO(factory);
		
		Usuario usuario;
		
		try {
			usuario = usuarioDAO.buscaLogin(login);
		} catch (UsuarioNaoEncontradoException e) {
			throw new WebApplicationException(Status.UNAUTHORIZED.getReasonPhrase(), Status.UNAUTHORIZED);
		}
		
		Token token = new Token(usuario, new Relogio());
		TokenManager.guardarToken(token);
		
		return Response.status(Status.OK).entity(token).build();
	}
	
}
