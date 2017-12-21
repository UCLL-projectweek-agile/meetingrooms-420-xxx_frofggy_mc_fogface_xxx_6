package storyTestSelenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ControleNavBar {
	WebDriver driver;
	String url = "http://java.cyclone2.khleuven.be:38034/%5B420%5D%20XxX_FrOFgGy_mC_FoGFaCe_xXx_6/Servlet?action=";

	@Before
	public void setUp(){
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/noute/Documents/UCLL/semester 2/Web2/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}
	
	public void navigeerNaar(String plaats){
		driver.get(url + plaats);
		
	}
	
	@After
	public void clean() {
		driver.quit();
	}
	
	@Test
	public void test_controleer_navBar_terecht_komen_op_juiste_pagina(){
		//deze test zal de navbar afgaan en kijken aan de hand van bepaalde 
		//elementen of we wel degelijk op de juiste pagina zijn terecht gekomen.
	}
}
