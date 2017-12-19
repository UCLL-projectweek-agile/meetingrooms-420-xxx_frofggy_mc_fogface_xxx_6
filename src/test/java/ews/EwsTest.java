/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ews;

import java.text.SimpleDateFormat;
import java.util.Date;

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
public class EwsTest {

    private static ExchangeService service = new ExchangeService();

    private static String room ="HSR-Douro@ucll.be";
    
    public static void main(String[] args) throws Exception {
        logIn();
        findAppointments();
    }

    private static void logIn() throws Exception {
        // user with read access for room information
        ExchangeCredentials credentials = new WebCredentials("sa_uurrooster", "JLxkK4BDUre3");
        //user gets privileges of room
        ImpersonatedUserId impersonatedUserId = new ImpersonatedUserId(ConnectingIdType.SmtpAddress, room);
        service.setImpersonatedUserId(impersonatedUserId);
        service.setCredentials(credentials);
        //find url to send request to (you can check: service.getUrl());
        service.autodiscoverUrl("HSR-Douro@ucll.be");
    }

    public static void findAppointments() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = formatter.parse("2017-12-01 00:00:00");
        Date endDate = formatter.parse("2017-12-31 24:00:00");
        //binds to the calendar folder of the room
        Mailbox mailbox = new Mailbox(room);
        FolderId folderId = new FolderId(WellKnownFolderName.Calendar, mailbox);
        CalendarFolder calendarFolder = CalendarFolder.bind(service, folderId);
        //read calendar of room
        CalendarView calendarView = new CalendarView(startDate, endDate);
        FindItemsResults<Appointment> findResults
                = calendarFolder.findAppointments(calendarView);
        for (Appointment appt : findResults.getItems()) {
            appt.load(PropertySet.FirstClassProperties);
            System.out.println("SUBJECT: " + appt.getSubject());
            System.out.println("FROM: " + appt.getStart());
            System.out.println("TILL: " + appt.getEnd());
        }
    }
}
