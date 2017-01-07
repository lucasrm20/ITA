package br.com.esseeujali.services;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.esseeujali.dao.LivroDAO;
import br.com.esseeujali.dao.UsuarioDAO;
import br.com.esseeujali.exceptions.LivroNaoEncontradoException;
import br.com.esseeujali.exceptions.UsuarioNaoEncontradoException;
import br.com.esseeujali.models.Livro;
import br.com.esseeujali.models.Usuario;
import br.com.esseeujali.models.rest.Livros;

@Path("/livros")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class LivroService {

	@GET
	public Livros listaTodosOsLivros(@Context HttpServletRequest req){
		EntityManagerFactory factory = (EntityManagerFactory) req.getAttribute("entityManager");
		LivroDAO livroDAO = new LivroDAO(factory);
		
		List<Livro> livros = livroDAO.buscaTodos();
		
		return new Livros(livros);
	}
	
	@GET
	@Path("{id}")
	public Response getLivro(@PathParam("id") Long id, @Context HttpServletRequest req) {
		EntityManagerFactory factory = (EntityManagerFactory) req.getAttribute("entityManager");
		LivroDAO livroDAO = new LivroDAO(factory);
		
		Livro livro;
		try {
			livro = livroDAO.buscaLivro(id);
		} catch (LivroNaoEncontradoException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.status(Status.OK).entity(livro).build();
	}
	
	@POST
	@Path("{id}/marcarLeitura")
	public Response marcarLivroComLido(@PathParam("id") Long id, @Context HttpServletRequest req) throws UsuarioNaoEncontradoException {
		EntityManagerFactory factory = (EntityManagerFactory) req.getAttribute("entityManager");
		Usuario usuarioLogado = (Usuario) req.getAttribute("usuarioLogado");
		
		LivroDAO livroDAO = new LivroDAO(factory);
		Livro livro;
		
		try {
			livro = livroDAO.buscaLivro(id);
		} catch (LivroNaoEncontradoException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		UsuarioDAO usuarioDAO = new UsuarioDAO(factory);
		usuarioDAO.marcarLivroComoLido(usuarioLogado, livro);
		
		return Response.status(Status.OK).build();
	}
	
}
