package br.com.esseeujali.services;

import static org.junit.Assert.*;

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

import br.com.esseeujali.models.Usuario;
import br.com.esseeujali.models.rest.Login;
import br.com.esseeujali.util.token.Token;

public class UsuarioServiceTest {

	private static WebTarget target;

	@BeforeClass
	public static void beforeAll(){
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());
		
		Client client = ClientBuilder.newClient(config);
		target = client.target("http://localhost:8080/esseeujali/api");
	}
	
	@Test
	public void retornaOsDadosDoUsuarioLogado() {
		Login login = new Login("usuario01", "usuario01");
		Token token = fazerLogin(login).readEntity(Token.class);
		
		Response response = target
				.path("/usuario")
				.register(JettisonFeature.class)
				.request()
				.header("Authorization", token.getToken())
				.get();
		
		Usuario usuarioInfo = response.readEntity(Usuario.class);
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		assertEquals(login.getUsuario(), usuarioInfo.getUsuario());
	}
	
	@Test
	public void retorna401SeOUsuarioNaoEstiverLogado() {
		Response response = target
				.path("/usuario")
				.register(JettisonFeature.class)
				.request()
				.get();
		
		Usuario usuarioInfo = response.readEntity(Usuario.class);
		
		assertEquals(Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
		assertNull(usuarioInfo);
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
