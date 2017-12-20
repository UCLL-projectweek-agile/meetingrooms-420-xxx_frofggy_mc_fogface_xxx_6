package meetingrooms;

import db.EwsReservationsDb;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Afspraak;
import domain.Klant;
import domain.Lokaal;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

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

    private ExchangeService service = new ExchangeService();

    private List<String> rooms = new ArrayList<>();
    private List<Afspraak> afspraken;
    private double lastChecked;
    private final EwsReservationsDb db;

    public Service(List<String> rooms) {
        this.rooms = rooms;
        ExchangeCredentials credentials = new WebCredentials("sa_uurrooster", "JLxkK4BDUre3");
        db = new EwsReservationsDb(rooms, credentials);
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

    public void findAppointments(String room, ExchangeService service, Date startDate, Date endDate) throws Exception {

        //binds to the calendar folder of the room
        Mailbox mailbox = new Mailbox(room);
        FolderId folderId = new FolderId(WellKnownFolderName.Calendar, mailbox);
        CalendarFolder calendarFolder = CalendarFolder.bind(service, folderId);
        //read calendar of room
        CalendarView calendarView = new CalendarView(startDate, endDate);
        FindItemsResults<Appointment> findResults
                = calendarFolder.findAppointments(calendarView);
        System.out.println("---------------------------------");
        System.out.println("Room: " + room);
        for (Appointment appt : findResults.getItems()) {
            appt.load(PropertySet.FirstClassProperties);
            System.out.println("SUBJECT: " + appt.getSubject());
            System.out.println("FROM: " + appt.getStart());
            System.out.println("TILL: " + appt.getEnd());
            Klant klant = new Klant(appt.getSubject(), appt.getStart(), appt.getEnd());
        }
        System.out.println("---------------------------------");
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

    public String stringFindAppointments(String room, ExchangeService service, Date startDate, Date endDate) throws Exception {

        String appointment = "";
        Mailbox mailbox = new Mailbox(room);
        FolderId folderId = new FolderId(WellKnownFolderName.Calendar, mailbox);
        CalendarFolder calendarFolder = CalendarFolder.bind(service, folderId);
        //read calendar of room
        CalendarView calendarView = new CalendarView(startDate, endDate);
        FindItemsResults<Appointment> findResults
                = calendarFolder.findAppointments(calendarView);
        appointment += "---------------------------------\n";
        appointment += "Room: " + room + "\n";
        for (Appointment appt : findResults.getItems()) {
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = new Date();
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 3600000);

        for (String r : rooms) {
            try {
                logIn(r, service);
                findAppointments(r, service, startDate, endDate);
            } catch (Exception e) {

            }
        }
    }

    public List<Afspraak> printAppointmentsvoorWeb() {
        double now = new Date().getTime();
        if (afspraken != null && (now - lastChecked) < 60000) {
            return afspraken;
        } else {
            lastChecked = now;

            ExchangeService service = new ExchangeService();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startDate = new Date();
            Date endDate = new Date();
            endDate.setTime(endDate.getTime() + 3600000);
            List<Afspraak> roomse = new ArrayList<>();

            for (String r : rooms) {
                try {
                    logIn(r, service);
                    roomse.addAll(findAppointments2(r, service, startDate, endDate));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            afspraken = roomse;
            return roomse;
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
        Date startDate = new Date();
        Date endDate = new Date();
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);

        endDate.setHours(23);
        endDate.setMinutes(59);
        endDate.setSeconds(59);

        for (String r : rooms) {
            try {
                logIn(r, service);
                p.write(stringFindAppointments(r, service, startDate, endDate));
            } catch (Exception e) {

            }
        }
        p.close();
    }

    public List<domain.Afspraak> getCurrentAppointments() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void printLokaal(String r) {

        ExchangeService service = new ExchangeService();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = new Date();
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 3600000);

        try {
            logIn(r, service);
            findAppointments(r, service, startDate, endDate);
        } catch (Exception e) {

        }
    }

    public List<domain.Afspraak> findAllAppointments(Date startDate, Date endDate) {
        try {
            return db.findAllAppointments(startDate, endDate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<domain.Afspraak>();
    }
}
