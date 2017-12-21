/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domain.Lokaal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ConnectingIdType;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.service.folder.CalendarFolder;
import microsoft.exchange.webservices.data.core.service.item.Appointment;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.misc.ImpersonatedUserId;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.Mailbox;
import microsoft.exchange.webservices.data.search.CalendarView;
import microsoft.exchange.webservices.data.search.FindItemsResults;

/**
 *
 * @author Daan
 */
public class EwsReservationsDb {
    
    private final List<Lokaal> rooms;
    private final ExchangeService service;
    private final ExchangeCredentials credentials;
    public EwsReservationsDb(List<Lokaal> rooms, ExchangeCredentials credentials){
        this.rooms = rooms;
        service = new ExchangeService();
        this.credentials = credentials;
    }
    
    private void logIn(String room) throws Exception {
        // user with read access for room information
        //user gets privileges of room
        ImpersonatedUserId impersonatedUserId = new ImpersonatedUserId(ConnectingIdType.SmtpAddress, room);
        service.setImpersonatedUserId(impersonatedUserId);
        service.setCredentials(credentials);
        //find url to send request to (you can check: service.getUrl());
        service.autodiscoverUrl(room);
    }
    
    public List<Appointment> findAppointments(String room, Date startDate, Date endDate) throws Exception {
        logIn(room);
        //binds to the calendar folder of the room
        Mailbox mailbox = new Mailbox(room);
        FolderId folderId = new FolderId(WellKnownFolderName.Calendar, mailbox);
        CalendarFolder calendarFolder = CalendarFolder.bind(service, folderId);
        //read calendar of room
        CalendarView calendarView = new CalendarView(startDate, endDate);
        FindItemsResults<Appointment> findResults
                = calendarFolder.findAppointments(calendarView);
        return findResults.getItems();
    }
    
    public List<domain.Afspraak> findAllAppointments(Date startDate, Date endDate) throws Exception{
        List<domain.Afspraak> list = new ArrayList<>();
        for(Lokaal room : rooms){
            String r = room.getLokaalID();
            List<Appointment> apps = findAppointments(r, startDate, endDate);
            String rsub = r.substring(4);
            rsub = rsub.split("@")[0];
            Lokaal lokaal = new Lokaal(r,rsub,0,0);
            for(Appointment a : apps){
                list.add(new domain.Afspraak(lokaal, a));
            }
        }
        return list;
    }
    
    public List<Appointment> findAllEwsAppointments(Date startDate, Date endDate) throws Exception{
        List<Appointment> list = new ArrayList<>();
        for(Lokaal room : rooms){
            String r = room.getLokaalID();
            List<Appointment> apps = findAppointments(r, startDate, endDate);
            list.addAll(apps);
        }
        return list;
    }
}
