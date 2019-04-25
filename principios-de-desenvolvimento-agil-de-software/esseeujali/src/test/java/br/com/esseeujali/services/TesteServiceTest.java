package br.com.esseeujali.services;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jettison.JettisonFeature;
import org.junit.BeforeClass;
import org.junit.Test;

public class TesteServiceTest {
	
	private static WebTarget target;

	@BeforeClass
	public static void beforeAll(){
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());
		
		Client client = ClientBuilder.newClient(config);
		target = client.target("http://localhost:8080/esseeujali/api");
	}

	@Test
	public void testaSeOServicoEstaRodando() {
		Response response = target
				.path("/test")
				.register(JettisonFeature.class)
				.request()
				.get();
		
		String conteudo = response.readEntity(String.class);
		
		assertEquals(200, response.getStatus());
		assertEquals("Web Service Rodando!", conteudo);
	}

}
