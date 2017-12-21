/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domain.Lokaal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ConnectingIdType;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.service.folder.CalendarFolder;
import microsoft.exchange.webservices.data.core.service.item.Appointment;
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
    public EwsReservationsDb(List<Lokaal> rooms, ExchangeService service){
        this.rooms = rooms;
        this.service = service;
        try {
            discover();
        } catch (Exception ex) {
            Logger.getLogger(EwsReservationsDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void discover() throws Exception{
        service.autodiscoverUrl(rooms.get(0).getLokaalID());
        System.out.println(service.getUrl());
    }
    
    public List<Appointment> findAppointments(String room, Date startDate, Date endDate) throws Exception {
        
        ImpersonatedUserId impersonatedUserId = new ImpersonatedUserId(ConnectingIdType.SmtpAddress, room);
        service.setImpersonatedUserId(impersonatedUserId);
        
        Mailbox mailbox = new Mailbox(room);
        FolderId folderId = new FolderId(WellKnownFolderName.Calendar, mailbox);
        CalendarFolder calendarFolder = CalendarFolder.bind(service, folderId);
        //read calendar of room
        CalendarView calendarView = new CalendarView(startDate, endDate);
        FindItemsResults<Appointment> findResults
                = calendarFolder.findAppointments(calendarView);
        return findResults.getItems();
    }
    
    public List<Appointment> findAppointments(Lokaal room, Date startDate, Date endDate) throws Exception {
        return this.findAppointments(room.getLokaalID(), startDate, endDate);
    }
    
    public List<domain.Afspraak> findAllAppointments(Date startDate, Date endDate) throws Exception{
        List<domain.Afspraak> list = new ArrayList<>();
        for(Lokaal room : rooms){
            String r = room.getLokaalID();
            List<Appointment> apps = findAppointments(r, startDate, endDate);
            for(Appointment a : apps){
                list.add(new domain.Afspraak(room, a));
            }
        }
        return list;
    }
    
    public List<Appointment> findAllEwsAppointments(Date startDate, Date endDate) throws Exception{
        List<Appointment> list = new ArrayList<>();
        for(Lokaal room : rooms){
            String r = room.getLokaalID();
            List<Appointment> apps = findAppointments(r,startDate, endDate);
            list.addAll(apps);
        }
        return list;
    }
}
