/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingrooms;

import db.EwsReservationsDb;
import domain.Afspraak;
import domain.Lokaal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.enumeration.misc.ConnectingIdType;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.service.folder.CalendarFolder;
import microsoft.exchange.webservices.data.core.service.item.Appointment;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.misc.ImpersonatedUserId;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.Mailbox;
import microsoft.exchange.webservices.data.search.CalendarView;
import microsoft.exchange.webservices.data.search.FindItemsResults;

/**
 *
 * @author Daan
 */
public class DataFetchingTask implements Runnable {

    private List<String> rooms;
    private final EwsReservationsDb db;
    private List<Afspraak> afspraken;
    
    public DataFetchingTask(List<String> rooms) {
        this.rooms = rooms;
        ExchangeCredentials credentials = new WebCredentials("sa_uurrooster", "JLxkK4BDUre3");
        db = new EwsReservationsDb(rooms, credentials);
    }

    private void logIn(String room, ExchangeService service) throws Exception {
        // user with read access for room information
        ExchangeCredentials credentials
                = new WebCredentials("sa_uurrooster", "JLxkK4BDUre3");
        //user gets privileges of room
        ImpersonatedUserId impersonatedUserId = new ImpersonatedUserId(ConnectingIdType.SmtpAddress, room);
        service.setImpersonatedUserId(impersonatedUserId);
        service.setCredentials(credentials);
        //find url to send request to (you can check: service.getUrl());
        service.autodiscoverUrl(room);
    }

    @Override
    public void run() {
        this.afspraken = getAppointments();
    }

    public List<Afspraak> getAfspraken(){
        return afspraken;
    }
    
    private List<Afspraak> getAppointmentsForRoom(String room, ExchangeService service, Date startDate, Date endDate) throws Exception {

        //binds to the calendar folder of the room
        Mailbox mailbox = new Mailbox(room);
        FolderId folderId = new FolderId(WellKnownFolderName.Calendar, mailbox);
        CalendarFolder calendarFolder = CalendarFolder.bind(service, folderId);
        //read calendar of room
        CalendarView calendarView = new CalendarView(startDate, endDate);
        FindItemsResults<Appointment> findResults
                = calendarFolder.findAppointments(calendarView);

        List<Afspraak> afspraken = new ArrayList<>();
        for (Appointment appt : findResults.getItems()) {
            appt.load(PropertySet.FirstClassProperties);
//            int start = appt.getStart().getHours() + (appt.getStart().getMinutes() / 60) + (appt.getStart().getSeconds() / 3600); 
//            int end = appt.getEnd().getHours() + (appt.getEnd().getMinutes() / 60) + (appt.getEnd().getSeconds() / 3600); 
            Lokaal lokaal = new Lokaal(room);
            Afspraak afspraak = new Afspraak(lokaal, appt);
            afspraken.add(afspraak);
        }
        return afspraken;
    }

    private List<Afspraak> getAppointments() {
        ExchangeService service = new ExchangeService();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = new Date();
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 3600000);
        List<Afspraak> roomse = new ArrayList<>();

        for (String r : rooms) {
            try {
                logIn(r, service);
                roomse.addAll(getAppointmentsForRoom(r, service, startDate, endDate));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return roomse;
    }

}
