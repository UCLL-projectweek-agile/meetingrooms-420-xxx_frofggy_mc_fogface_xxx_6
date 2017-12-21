/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingrooms;

import db.EwsReservationsDb;
import db.RoomInMemoryDb;
import domain.Lokaal;
import java.util.List;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;

/**
 *
 * @author Daan
 */
public class Application {

    public static void main(String[] args) throws Exception {

        List<Lokaal> rooms = (new RoomInMemoryDb()).getRooms();
        ExchangeCredentials credentials = new WebCredentials("sa_uurrooster", "JLxkK4BDUre3");
        ExchangeService exchange = new ExchangeService();
        exchange.setCredentials(credentials);
        Service service = new Service(rooms, new EwsReservationsDb(rooms, exchange));
        //Daans work to get appointments for now
        service.printAppointmentsNow();
        //Arnolds work to get txt file for whole day
        service.printAppointmentsToday();

    }

}
