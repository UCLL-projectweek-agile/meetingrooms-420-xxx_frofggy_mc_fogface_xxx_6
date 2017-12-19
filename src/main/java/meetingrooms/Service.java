package meetingrooms;


import db.EwsReservationsDb;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Klant;
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
    
    
    private ExchangeService service = new ExchangeService();

    private List<String> rooms = new ArrayList<>();
    private final EwsReservationsDb db;
    
    public Service(){
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
        System.out.println("Room: "+room);
        for (Appointment appt : findResults.getItems()) {
            appt.load(PropertySet.FirstClassProperties);
            System.out.println("SUBJECT: " + appt.getSubject());
            System.out.println("FROM: " + appt.getStart());
            System.out.println("TILL: " + appt.getEnd());
            Klant klant = new Klant(appt.getSubject(), appt.getStart(), appt.getEnd());
        }
        System.out.println("---------------------------------");
    }
    
 public List<Klant> findAppointments2(String room, ExchangeService service, Date startDate, Date endDate) throws Exception {
        
        //binds to the calendar folder of the room
        Mailbox mailbox = new Mailbox(room);
        FolderId folderId = new FolderId(WellKnownFolderName.Calendar, mailbox);
        CalendarFolder calendarFolder = CalendarFolder.bind(service, folderId);
        //read calendar of room
        CalendarView calendarView = new CalendarView(startDate, endDate);
        FindItemsResults<Appointment> findResults
                = calendarFolder.findAppointments(calendarView);
        System.out.println("---------------------------------");
        System.out.println("Room: "+room);
        List<Klant>klanten = new ArrayList<>();
        for (Appointment appt : findResults.getItems()) {
            appt.load(PropertySet.FirstClassProperties);
            System.out.println("SUBJECT: " + appt.getSubject());
            System.out.println("FROM: " + appt.getStart());
            System.out.println("TILL: " + appt.getEnd());
            Klant klant = new Klant(appt.getSubject(), appt.getStart(), appt.getEnd());
            klanten.add(klant);
        }
        System.out.println("---------------------------------");
        return klanten;
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
        appointment += "Room: "+room + "\n";
        for (Appointment appt : findResults.getItems()) {
            appt.load(PropertySet.FirstClassProperties);
            appointment += "SUBJECT: " + appt.getSubject() + "\n";
            appointment += "FROM: " + appt.getStart() + "\n";
            appointment += "TILL: " + appt.getEnd() + "\n";
            
        }
        appointment += "---------------------------------";
        return appointment;
    }
    
    public void printAppointmentsNow(){
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
        
        
        ExchangeService service = new ExchangeService();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = new Date();
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 3600000);
        
        for(String r : rooms){
            try{
                logIn(r, service);
                findAppointments(r, service, startDate, endDate);
            }catch (Exception e){
                
            }
        }
    }
    
    public List<Klant> printAppointmentsvoorWeb(){
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
        
        
        ExchangeService service = new ExchangeService();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = new Date();
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 3600000);
        List<Klant> klanten = new ArrayList<>();
        
        for(String r : rooms){
            try{
                logIn(r, service);
                klanten = findAppointments2(r, service, startDate, endDate);
            }catch (Exception e){
                
            }
        }
        return klanten;
    }
    
    public void printAppointmentsToday(){
		PrintWriter p = null;
		try{
			p = new PrintWriter("DagSchema.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			System.out.println(e1.getMessage());
		}
		
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
        
        for(String r : rooms){
            try{
                logIn(r, service);
                p.write(stringFindAppointments(r, service, startDate, endDate));          
            }catch (Exception e){
                
            }
        }
        p.close();
    }
    

    public List<domain.Appointment> getCurrentAppointments() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void printLokaal(String r) {
        
        
        ExchangeService service = new ExchangeService();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = new Date();
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 3600000);
        
        try{
            logIn(r, service);
            findAppointments(r, service, startDate, endDate);
        }catch (Exception e){
            
        }
    }

    public List<domain.Appointment> findAllAppointments(Date startDate, Date endDate) {
        try {
            return db.findAllAppointments(startDate, endDate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<domain.Appointment>();
    }
}
