package br.com.esseeujali.services;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.esseeujali.dao.UsuarioDAO;
import br.com.esseeujali.exceptions.UsuarioNaoEncontradoException;
import br.com.esseeujali.models.Usuario;

@Path("/usuario")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class UsuarioService {

	@GET
	public Response getInfo(@Context HttpServletRequest req) throws UsuarioNaoEncontradoException{
		EntityManagerFactory factory = (EntityManagerFactory) req.getAttribute("entityManager");
		UsuarioDAO usuarioDAO = new UsuarioDAO(factory);
		
		Usuario usuarioLogado = (Usuario) req.getAttribute("usuarioLogado");
		Usuario usuario = usuarioDAO.buscarUsuarioPorId(usuarioLogado.getId());
		
		return Response.status(Status.OK).entity(usuario).build();
	}
	
}
