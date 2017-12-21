package storyTests;

import db.EwsReservationsDb;
import db.RoomInMemoryDb;
import domain.Lokaal;
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
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;

public class Story3_Test {

    private File text;
    private Scanner scnr;
    private String bestandInhoud;

    @Before
    public void setUp() throws FileNotFoundException {
        List<Lokaal> rooms = (new RoomInMemoryDb()).getRooms();
        ExchangeCredentials credentials = new WebCredentials("sa_uurrooster", "JLxkK4BDUre3");
        ExchangeService exchange = new ExchangeService();
        exchange.setCredentials(credentials);
        Service service = new Service(rooms, new EwsReservationsDb(rooms, exchange));
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
        if (Collections.frequency(mapke, "Room: HSR-Yangtze@ucll.be") > 1) {
            suc = false;
        }
        if (Collections.frequency(mapke, "Room: HSR-Schelde@ucll.be") > 1) {
            suc = false;
        }
        if (Collections.frequency(mapke, "Room: HSR-Sarine@ucll.be") > 1) {
            suc = false;
        }
        if (Collections.frequency(mapke, "Room: HSR-Rhone@ucll.be") > 1) {
            suc = false;
        }
        if (Collections.frequency(mapke, "Room: HSR-Po@ucll.be") > 1) {
            suc = false;
        }
        if (Collections.frequency(mapke, "Room: HSR-Ebro@ucll.be") > 1) {
            suc = false;
        }
        if (Collections.frequency(mapke, "Room: HSR-Maas@ucll.be") > 1) {
            suc = false;
        }
        if (Collections.frequency(mapke, "Room: HSR-Douro@ucll.be") > 1) {
            suc = false;
        }
        if (Collections.frequency(mapke, "Room: HSR-Donau@ucll.be") > 1) {
            suc = false;
        }
        if (Collections.frequency(mapke, "Room: HSR-Chao-Praya@ucll.be") > 1) {
            suc = false;
        }
        if (Collections.frequency(mapke, "Room: HSR-Arno@ucll.be") > 1) {
            suc = false;
        }
        if (Collections.frequency(mapke, "Room: HSR-Thames@ucll.be") > 1) {
            suc = false;
        }
        if (Collections.frequency(mapke, "Room: HSR-Moselle@ucll.be") > 1) {
            suc = false;
        }
        assertTrue(suc);

    }

}
