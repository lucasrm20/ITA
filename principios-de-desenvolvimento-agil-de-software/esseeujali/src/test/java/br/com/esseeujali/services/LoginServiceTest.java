package br.com.esseeujali.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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

import br.com.esseeujali.models.rest.Login;
import br.com.esseeujali.util.token.Token;

public class LoginServiceTest {

	private static WebTarget target;

	@BeforeClass
	public static void beforeAll(){
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());
		
		Client client = ClientBuilder.newClient(config);
		target = client.target("http://localhost:8080/esseeujali/api");
	}
	
	@Test
	public void retornaStatus200AoFazerLoginComDadosCorretos() {
		Login login = new Login("usuario01", "usuario01");
		Entity<Login> entity = Entity.entity(login, MediaType.APPLICATION_JSON);
		
		Response response = target
				.path("/auth/login")
				.register(JettisonFeature.class)
				.request()
				.post(entity);
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void retornaOTokenDeAutenticacaoAoLogarCorretamente(){
		Login login = new Login("usuario01", "usuario01");
		Entity<Login> entity = Entity.entity(login, MediaType.APPLICATION_JSON);
		
		Response response = target
				.path("/auth/login")
				.register(JettisonFeature.class)
				.request()
				.post(entity);
		
		Token token = response.readEntity(Token.class);
		
		assertNotNull(token);
		assertFalse(token.getToken().isEmpty());
	}
	
	@Test
	public void retornaStatus401AoFazerLoginComDadosErrados() {
		Login login = new Login("naoExiste", "naoExiste");
		Entity<Login> entity = Entity.entity(login, MediaType.APPLICATION_JSON);
		
		Response response = target
				.path("/auth/login")
				.register(JettisonFeature.class)
				.request()
				.post(entity);
		
		assertEquals(Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
	}
	
}
