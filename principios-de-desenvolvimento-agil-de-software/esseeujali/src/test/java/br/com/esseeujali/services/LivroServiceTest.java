package br.com.esseeujali.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jettison.JettisonFeature;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.esseeujali.models.Livro;
import br.com.esseeujali.models.rest.Livros;
import br.com.esseeujali.models.rest.Login;
import br.com.esseeujali.util.token.Token;

public class LivroServiceTest {

	private static WebTarget target;

	@BeforeClass
	public static void beforeAll(){
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());
		
		Client client = ClientBuilder.newClient(config);
		target = client.target("http://localhost:8080/esseeujali/api");
	}
	
	@Test
	public void retornaAListaDeLivrosSeOUsuarioEstiverLogado() {
		Login login = new Login("usuario01", "usuario01");
		Token token = fazerLogin(login).readEntity(Token.class);
		
		Response response = target
				.path("/livros")
				.register(JettisonFeature.class)
				.request()
				.header("Authorization", token.getToken())
				.get();
		
		Livros livros = response.readEntity(Livros.class);
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		assertNotNull(livros.getLivros());
	}
	
	@Test
	public void retorna401SeOUsuarioNaoEstiverLogadoAoTentarRecuperarOsLivros() {
		Response response = target
				.path("/livros")
				.register(JettisonFeature.class)
				.request()
				.get();
		
		Livros livros = response.readEntity(Livros.class);
		
		assertEquals(Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
		assertNull(livros);
	}
	
	@Test
	public void retornaOsDadosDeUmLivroEspecificoSeOUsuarioEstiverLogado() {
		Login login = new Login("usuario01", "usuario01");
		Token token = fazerLogin(login).readEntity(Token.class);
		
		Response response = target
				.path("/livros/1")
				.register(JettisonFeature.class)
				.request()
				.header("Authorization", token.getToken())
				.get();
		
		Livro livro = response.readEntity(Livro.class);
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		assertNotNull(livro.getTitulo());
	}
	
	@Test
	public void retorna404SeUmLivroEspecificoNaoExistir() {
		Login login = new Login("usuario01", "usuario01");
		Token token = fazerLogin(login).readEntity(Token.class);
		
		Response response = target
				.path("/livros/150")
				.register(JettisonFeature.class)
				.request()
				.header("Authorization", token.getToken())
				.get();
		
		
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void retorna401SeOUsuarioNaoEstiverLogadoAoTentarRecuperarUmLivroEspecifico() {
		Response response = target
				.path("/livros/1")
				.register(JettisonFeature.class)
				.request()
				.get();
		
		Livro livro = response.readEntity(Livro.class);
		
		assertEquals(Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
		assertNull(livro);
	}
	
	@Test
	public void retorna200AoMarcarUmLivroComoLidoSeOUsuarioEstiverLogado() {
		Login login = new Login("usuario01", "usuario01");
		Token token = fazerLogin(login).readEntity(Token.class);
		
		Entity<Livro> entity = Entity.entity(new Livro(), MediaType.APPLICATION_JSON);
		
		Response response = target
				.path("/livros/1/marcarLeitura")
				.register(JettisonFeature.class)
				.request()
				.header("Authorization", token.getToken())
				.post(entity);
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void retorna404AoMarcarUmLivroInexistenteComoLido() {
		Login login = new Login("usuario01", "usuario01");
		Token token = fazerLogin(login).readEntity(Token.class);
		
		Entity<Livro> entity = Entity.entity(new Livro(), MediaType.APPLICATION_JSON);
		
		Response response = target
				.path("/livros/150/marcarLeitura")
				.register(JettisonFeature.class)
				.request()
				.header("Authorization", token.getToken())
				.post(entity);
		
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void retorna401AoMarcarUmLivroComoLidoSeOUsuarioNaoEstiverLogado() {
		Entity<Livro> entity = Entity.entity(new Livro(), MediaType.APPLICATION_JSON);
		
		Response response = target
				.path("/livros/1/marcarLeitura")
				.register(JettisonFeature.class)
				.request()
				.post(entity);
		
		assertEquals(Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
	}
	
	private Response fazerLogin(Login login){
		Entity<Login> entity = Entity.entity(login, MediaType.APPLICATION_JSON);
		
		Response loginResponse = target
				.path("/auth/login")
				.register(JettisonFeature.class)
				.request()
				.post(entity);
		
		return loginResponse;
	}

}
