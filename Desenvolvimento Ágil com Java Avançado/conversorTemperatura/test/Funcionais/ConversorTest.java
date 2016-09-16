package Funcionais;

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

public class ConversorTest {
	
	private static WebDriver driver;

	@BeforeClass
	public static void beforaClass(){
		driver = new FirefoxDriver();
	}
	
	@Before
	public void before(){
		driver.get("http://localhost:8080/conversorTemperatura");
	}
	
	@AfterClass
	public static void afterClass(){
		driver.close();
	}

	@Test
	public void converteDeCelciusParaFahrenheitCorretamente() {
		WebElement inputTemperatura = driver.findElement(By.name("temperatura"));
		inputTemperatura.sendKeys("100");
		
		Select selectTipoConversao = new Select(driver.findElement(By.name("converterPara")));
		selectTipoConversao.selectByVisibleText("de Celcius para Fahrenheit");
		
		inputTemperatura.submit();
		
		WebElement spanResultado = driver.findElement(By.id("resultado"));
		assertEquals("212°F", spanResultado.getText());

	}
	
	@Test
	public void converteDeFahrenheitParaCelciusCorretamente() {
		WebElement inputTemperatura = driver.findElement(By.name("temperatura"));
		inputTemperatura.sendKeys("212");
		
		Select selectTipoConversao = new Select(driver.findElement(By.name("converterPara")));
		selectTipoConversao.selectByVisibleText("de Fahrenheit para Celcius");
		
		inputTemperatura.submit();
		
		WebElement spanResultado = driver.findElement(By.id("resultado"));
		assertEquals("100°C", spanResultado.getText());

	}

}
