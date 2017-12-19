/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingrooms;

/**
 *
 * @author Daan
 */
public class Application {

     public static void main(String[] args) throws Exception {
         Service service = new Service();
        //service.printAppointmentsNow();
         service.printAppointmentsToday();
    }
    
}
