package storyTests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import meetingrooms.Service;

public class Story3_Test {
	private File text;
	private Scanner scnr;
	private String bestandInhoud = "";

	@Before
	public void setUp() throws FileNotFoundException {
		List<String> rooms = new ArrayList<>();
        rooms.add("HSR-Yangtze@ucll.be");
        rooms.add("HSR-Schelde@ucll.be");
        rooms.add("HSR-Sarine@ucll.be");
        rooms.add("HSR-Rhone@ucll.be");
        rooms.add("HSR-Po@ucll.be");
        rooms.add("HSR-Ebro@ucll.be");
        rooms.add("HSR-Maas@ucll.be");
        rooms.add("HSR-Douro@ucll.be");
        rooms.add("HSR-Donau@ucll.be");
        rooms.add("HSR-Chao-Praya@ucll.be");
        rooms.add("HSR-Arno@ucll.be");
		
		Service service = new Service(rooms);
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
		assertTrue(!bestandInhoud.isEmpty());
	}

	@Test
	public void fileStructureCorrect() {
		
	}
	


}
