package storyTestSelenium;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class story6_OverzichtLokalen {
	private WebDriver driver;
	private String url = "http://java.cyclone2.khleuven.be:38034/%5B420%5D%20XxX_FrOFgGy_mC_FoGFaCe_xXx_6/Servlet?action=";
	private List<String> lokaalnamen = new ArrayList<>();

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/noute/Documents/UCLL/semester 2/Web2/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url + "index");

		// alle lokalen die er momenteel moeten zijn, momenteel geen methode via
		// de DB om de namen te kunnen opvragen.
		lokaalnamen.add("DONAU");
		lokaalnamen.add("ARNO");
		lokaalnamen.add("PO");
		lokaalnamen.add("SCHELDE");
		lokaalnamen.add("CHAO-PRAYA");
		lokaalnamen.add("DOURO");
		lokaalnamen.add("MAAS");
		lokaalnamen.add("EBRO");
		lokaalnamen.add("MOSELLE");
		lokaalnamen.add("RHONE");
		lokaalnamen.add("SARINE");
		lokaalnamen.add("YANGTZE");
		lokaalnamen.add("THAMES");
	}

	public boolean vindAlleLokalen() {
		boolean found = true;
		ArrayList<WebElement> headings = (ArrayList<WebElement>) driver.findElements(By.className("row-heading"));
		for (WebElement webelement : headings) {
			//System.out.println(webelement.getText());
			if (!lokaalnamen.contains(webelement.getText().trim().toUpperCase()))
				found = false;
			
		}
		System.out.println(found);
		return found;
	}

	@After
	public void clean() {
		lokaalnamen.clear();
		driver.quit();
	}

	@Test
	public void test_controleer_of_alle_lokalen_aanwezig_zijn_in_OverzichtLokalen() {
		driver.get(url + "timetable");
		assertTrue(vindAlleLokalen());
	}

	@Test
	public void test_controleer_of_uren_aanwezig_zijn(){
		driver.get(url + "timetable");
		assertNotNull(driver.findElements(By.className("time-label")));
	}
}
