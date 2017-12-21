package meetingrooms;

import db.EwsReservationsDb;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import domain.Afspraak;
import domain.Lokaal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Daan
 */
public class Service {

    private final EwsReservationsDb db;

    private List<Lokaal> rooms = new ArrayList<>();
    private List<Afspraak> afspraken;
    private double lastChecked;

    public Service(List<Lokaal> rooms, EwsReservationsDb ews) {
        this.rooms = rooms;
        db = ews;
    }

    public void logIn(String room, ExchangeService service) throws Exception {
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

    public List<Afspraak> findAppointments2(String room, ExchangeService service, Date startDate, Date endDate) throws Exception {

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

    public List<String> stringFindAppointments(ExchangeService service, Date startDate, Date endDate) throws Exception {
        List<String> string = new ArrayList<>();
        for (Lokaal room : rooms) {
            string.add(stringFindAppointmentsForRoom(room, startDate, endDate));
        }
        return string;
    }

    private String stringFindAppointmentsForRoom(Lokaal room, Date startDate, Date endDate) throws Exception {
        String appointment;
        List<Appointment> findResults = db.findAppointments(room, startDate, endDate);
        appointment = "---------------------------------\n";
        appointment += "Room: " + room + "\n";
        for (Appointment appt : findResults) {
            appt.load(PropertySet.FirstClassProperties);
            appointment += "SUBJECT: " + appt.getSubject() + "\n";
            appointment += "FROM: " + appt.getStart() + "\n";
            appointment += "TILL: " + appt.getEnd() + "\n";

        }
        appointment += "---------------------------------";
        return appointment;
    }

    public void printAppointmentsNow() {

        ExchangeService service = new ExchangeService();
        Date startDate = new Date();
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 3600000);

        try {
            for (String s : this.stringFindAppointments(service, startDate, endDate)) {
                System.out.println(s);
            }
        } catch (Exception ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Afspraak> getAppointmentsToday() {
        double now = new Date().getTime();
        if (afspraken != null && (now - lastChecked) < 60000) {
            return afspraken;
        } else {
            lastChecked = now;

            Calendar calStart = getMidnightYesterday();
            Date midnightYesterday = calStart.getTime();

            Calendar calEnd = getMidnightToday();
            Date midnightTonight = calEnd.getTime();

            List<Afspraak> roomse = new ArrayList<>();
            try {
                afspraken = db.findAllAppointments(midnightYesterday, midnightTonight);
            } catch (Exception ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            }
            return afspraken;
        }
    }

    public void printAppointmentsToday() {
        PrintWriter p = null;
        try {
            p = new PrintWriter("DagSchema.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e1) {
            System.out.println(e1.getMessage());
        }
        ExchangeService service = new ExchangeService();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar calStart = getMidnightYesterday();
        Date midnightYesterday = calStart.getTime();

        Calendar calEnd = getMidnightToday();
        Date midnightTonight = calEnd.getTime();

        try {
            for (String r : stringFindAppointments(service, midnightYesterday, midnightTonight)) {
                p.write(r);
            }
        } catch (Exception ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.close();
    }

    private Calendar getMidnightToday() {
        Calendar calEnd = new GregorianCalendar();
        calEnd.setTime(new Date());
        calEnd.set(Calendar.DAY_OF_YEAR, calEnd.get(Calendar.DAY_OF_YEAR) + 1);
        calEnd.set(Calendar.HOUR_OF_DAY, 0);
        calEnd.set(Calendar.MINUTE, 0);
        calEnd.set(Calendar.SECOND, 0);
        calEnd.set(Calendar.MILLISECOND, 0);
        return calEnd;
    }

    private Calendar getMidnightYesterday() {
        Calendar calStart = new GregorianCalendar();
        calStart.setTime(new Date());
        calStart.set(Calendar.HOUR_OF_DAY, 0);
        calStart.set(Calendar.MINUTE, 0);
        calStart.set(Calendar.SECOND, 0);
        calStart.set(Calendar.MILLISECOND, 0);
        return calStart;
    }

    public List<domain.Afspraak> findAllAppointments(Date startDate, Date endDate) {
        try {
            return db.findAllAppointments(startDate, endDate);
        } catch (Exception ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Map<String, Boolean> getCurrentOccupation() {
        Map<String, Boolean> bezet = new HashMap<>();
        for (Lokaal l : rooms) {
            bezet.put(l.getLokaalnummer(), Boolean.FALSE);
        }
        List<Afspraak> afspraken = getAppointmentsToday();
        Calendar now = new GregorianCalendar();
        now.setTime(new Date());
        for (Afspraak afspraak : afspraken) {
            if (afspraak.isDuring(now)) {
                bezet.put(afspraak.getLokaal().getLokaalnummer(), Boolean.FALSE);
            }
        }
        return bezet;
    }
}
