package storyTests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import meetingrooms.Service;

public class Story3_Test {
	private File text;
	private Scanner scnr;
	private String bestandInhoud;

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
        rooms.add("HSR-Thames@ucll.be");
        rooms.add("HSR-Moselle@ucll.be");
        
		Service service = new Service(rooms);
		service.printAppointmentsNow();
		service.printAppointmentsToday();

		text = new File("DagSchema.txt");

		scnr = new Scanner(text);

//		// Reading each line of file using Scanner class
//		int lineNumber = 1;
//		while (scnr.hasNextLine()) {
//			String line = scnr.nextLine();
//			bestandInhoud += scnr.nextLine();
//		}

	}

	@Test
	public void fileExists() throws FileNotFoundException {
		// Service service = new Service();
		// service.printAppointmentsToday();

		assertTrue(text.exists());
	}

	@Test
	public void fileStructureCorrect() {
		
	}
	
		@Test
		public void NoDuplicates() throws FileNotFoundException {
		Boolean suc = true;	
			
		java.util.Map<String, Long> map = new HashMap<>();
		ArrayList<String> mapke = new ArrayList();
		Scanner read = new Scanner(text);
		while (read.hasNext()) {
	      String line = read.nextLine();
	      mapke.add(line);
	   }
		if(Collections.frequency(mapke, "Room: HSR-Yangtze@ucll.be")>1){
			suc =false;
		}
		if(Collections.frequency(mapke, "Room: HSR-Schelde@ucll.be")>1){
			suc =false;
		}
		if(Collections.frequency(mapke, "Room: HSR-Sarine@ucll.be")>1){
			suc =false;
		}
		if(Collections.frequency(mapke, "Room: HSR-Rhone@ucll.be")>1){
			suc =false;
		}
		if(Collections.frequency(mapke, "Room: HSR-Po@ucll.be")>1){
			suc =false;
		}
		if(Collections.frequency(mapke, "Room: HSR-Ebro@ucll.be")>1){
			suc =false;
		}
		if(Collections.frequency(mapke, "Room: HSR-Maas@ucll.be")>1){
			suc =false;
		}
		if(Collections.frequency(mapke, "Room: HSR-Douro@ucll.be")>1){
			suc =false;
		}
		if(Collections.frequency(mapke, "Room: HSR-Donau@ucll.be")>1){
			suc =false;
		}
		if(Collections.frequency(mapke, "Room: HSR-Chao-Praya@ucll.be")>1){
			suc =false;
		}
		if(Collections.frequency(mapke, "Room: HSR-Arno@ucll.be")>1){
			suc =false;
		}
		if(Collections.frequency(mapke, "Room: HSR-Thames@ucll.be")>1){
			suc =false;
		}
		if(Collections.frequency(mapke, "Room: HSR-Moselle@ucll.be")>1){
			suc =false;
		}
	    assertTrue(suc);
	    
	    
	}

}
