/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Calendar;
import java.util.Date;
import microsoft.exchange.webservices.data.core.PropertySet;

/**
 *
 * @author Daan
 */
public class Afspraak {

    private Lokaal lokaal;
    
    private Calendar startDate;
    private Calendar endDate;
    
    private int startMinute;
    private int startHour;
    private int endMinute;
    private int endHour;

    public Afspraak(Lokaal lokaal, microsoft.exchange.webservices.data.core.service.item.Appointment appt) throws Exception {
        this.setLokaal(lokaal);
        appt.load(PropertySet.FirstClassProperties);
        this.setStartDate(toCalendar(appt.getStart()));
        this.setEndDate(toCalendar(appt.getEnd()));
        this.setStartHour();
        this.setStartMinute();
        this.setEndHour();
        this.setEndMinute();
    }
    

    public Afspraak(Lokaal lokaal, Calendar startDate, Calendar endDate) {
        this.lokaal = lokaal;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Afspraak() {

    }

    public Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public Lokaal getLokaal() {
        return lokaal;
    }

    public void setLokaal(Lokaal lokaal) {
        this.lokaal = lokaal;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public boolean berekenBeschikbaarheid() {
        //lokaal
        return true;
    }

    @Override
    public String toString() {
        return lokaal.toString() + " Start: " + startDate + " End: " + endDate;
    }
    
    public void setStartHour(){
        startHour = startDate.get(Calendar.HOUR_OF_DAY);
    }

    public int getStartHour() {
        return startHour;
    }
    
    public void setStartMinute(){
        startMinute = startDate.get(Calendar.MINUTE);
    }
    
    public int getStartMinute() {
        return startMinute;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute() {
        this.endMinute = endDate.get(Calendar.MINUTE);
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour() {
        this.endHour = endDate.get(Calendar.HOUR_OF_DAY);
    }
}
