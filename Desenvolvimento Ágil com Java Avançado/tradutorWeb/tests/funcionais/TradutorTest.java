package funcionais;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TradutorTest {

	private static WebDriver driver;

	@BeforeClass
	public static void beforaClass(){
		driver = new FirefoxDriver();
	}
	
	@Before
	public void before(){
		driver.get("http://localhost:8080/tradutorWeb");
	}
	
	@AfterClass
	public static void afterClass(){
		driver.close();
	}

	@Test
	public void traduzAPalavraScreen() {
		WebElement inputPalavra = driver.findElement(By.name("palavra"));
		inputPalavra.sendKeys("screen");
		
		inputPalavra.submit();
		
		WebElement spanTraducao = driver.findElement(By.id("traducao"));
		assertEquals("tela", spanTraducao.getText());

	}
	
	@Test
	public void traduzAPalavraBanheiro() {
		WebElement inputPalavra = driver.findElement(By.name("palavra"));
		inputPalavra.sendKeys("banheiro");
		
		inputPalavra.submit();
		
		WebElement spanTraducao = driver.findElement(By.id("traducao"));
		assertEquals("bathroom", spanTraducao.getText());

	}
	
	@Test
	public void naoTraduzAUmaPalavraQueNaoExisteNoArquivo() {
		WebElement inputPalavra = driver.findElement(By.name("palavra"));
		inputPalavra.sendKeys("inexistente");
		
		inputPalavra.submit();
		
		WebElement spanTraducao = driver.findElement(By.id("traducao"));
		assertEquals("inexistente", spanTraducao.getText());

	}

}
