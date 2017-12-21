/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingrooms;

import db.RoomSqlDb;
import domain.Lokaal;
import java.util.List;

/**
 *
 * @author Daan
 */
public class Application {

    public static void main(String[] args) throws Exception {

        List<Lokaal> rooms = (new RoomSqlDb()).getRooms();
        Service service = new Service(rooms);
        //Daans work to get appointments for now
        service.printAppointmentsNow();
        //Arnolds work to get txt file for whole day
        service.printAppointmentsToday();

    }

}
