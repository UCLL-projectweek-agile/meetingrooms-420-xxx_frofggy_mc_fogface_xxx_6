package storyTests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import meetingrooms.Service;
import microsoft.exchange.webservices.data.core.ExchangeService;

public class Story3_Test {
	private File text;
	private Scanner scnr;
	private String bestandInhoud = "";

	@Before
	public void setUp() throws FileNotFoundException {
		Service service = new Service();
		service.printAppointmentsNow();
		service.printAppointmentsToday();

		text = new File("DagSchema.txt");

		scnr = new Scanner(text);

		// Reading each line of file using Scanner class
		int lineNumber = 1;
		while (scnr.hasNextLine()) {
			String line = scnr.nextLine();
			bestandInhoud += line + "/n";
		}

	}

	@Test
	public void fileExists() throws FileNotFoundException {
		// Service service = new Service();
		// service.printAppointmentsToday();

		assertTrue(text.exists());
	}

	@Test
	public void fileNotEmpty() throws FileNotFoundException {
		assertFalse(bestandInhoud.isEmpty());
	}

	@Test
	public void fileStructureCorrect() {
		
	}

}
