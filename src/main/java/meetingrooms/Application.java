/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingrooms;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daan
 */
public class Application {

     public static void main(String[] args) throws Exception {
         
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
         //Daans work to get appointments for now
         service.printAppointmentsNow();
         //Arnolds work to get txt file for whole day
         service.printAppointmentsToday();
         
    }
    
}
