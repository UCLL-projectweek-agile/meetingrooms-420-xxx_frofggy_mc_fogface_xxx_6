package storyTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ControleNavBar {
	WebDriver driver;
	String url = "http://java.cyclone2.khleuven.be:38034/WebShop/Controller";

	@Before
	public void setUp(){
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/noute/Documents/UCLL/semester 2/Web2/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}
	
	@After
	public void clean() {
		driver.quit();
	}
	
	@Test
	public void test_controleer_navBar_terecht_komen_op_juiste_pagina(){
		
	}
}
