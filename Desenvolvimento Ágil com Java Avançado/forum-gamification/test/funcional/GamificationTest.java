package funcional;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Favor Rodar os Testes Unitários Pelo Menos Uma Vez Antes De Executar 
 * Os Testes Desta Classe Pela Primeira Vez.
 * @author lucas
 *
 */
public class GamificationTest {

	private static WebDriver driver;
	private String urlBase = "http://localhost:8080/forum-gamification/";

	@BeforeClass
	public static void beforaClass(){
		driver = new FirefoxDriver();
	}
	
	@Before
	public void before(){
		driver.get(urlBase);
	}
	
	@AfterClass
	public static void afterClass(){
		driver.close();
	}
	
	public void logar(String login, String senha){
		WebElement inputLogin = driver.findElement(By.name("login"));
		inputLogin.sendKeys(login);
		
		WebElement inputSenha = driver.findElement(By.name("senha"));
		inputSenha.sendKeys(senha);
		
		inputSenha.submit();
	}
	
	@Test
	public void realizaLoginComSucesso(){
		logar("lucas", "12345");
		
		assertTrue(driver.getPageSource().contains("Tópicos"));
	}
	
	@Test
	public void loginInvalido(){
		logar("naoExiste", "naoExiste");
		
		assertTrue(driver.getPageSource().contains("Login ou Senha Inválido"));
	}
	
	@Test
	public void criaUmNovoTopico(){
		logar("lucas", "12345");
		
		WebElement linkInserirTopico = driver.findElement(By.className("btn-primary"));
		linkInserirTopico.click();
		
		WebElement inputTitulo = driver.findElement(By.name("titulo"));
		inputTitulo.sendKeys("Novo Tópico Selenium");
		
		WebElement textBoxConteudo = driver.findElement(By.name("conteudo"));
		textBoxConteudo.sendKeys("Conteúdo Novo Tópico Selenium");
		
		textBoxConteudo.submit();
		
		assertTrue(driver.getPageSource().contains("Novo Tópico Selenium"));
		WebElement linkNovoTopico = driver.findElement(By.linkText("Novo Tópico Selenium"));
		linkNovoTopico.click();
		
		assertTrue(driver.getPageSource().contains("Conteúdo Novo Tópico Selenium"));
	}
	
	@Test
	public void criaUmNovoComentario(){
		logar("lucas", "12345");
		
		WebElement linkTopico01 = driver.findElement(By.linkText("Tópico 01"));
		linkTopico01.click();
		
		WebElement textBoxComentario = driver.findElement(By.name("comentario"));
		textBoxComentario.sendKeys("Novo Comentário Selenium");
		
		textBoxComentario.submit();
		
		assertTrue(driver.getPageSource().contains("Novo Comentário Selenium"));
	}

}
