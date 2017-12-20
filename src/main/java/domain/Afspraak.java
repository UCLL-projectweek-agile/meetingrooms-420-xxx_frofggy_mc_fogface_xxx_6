/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Calendar;
import java.util.Date;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;

/**
 *
 * @author Daan
 */
public class Afspraak {
    
    private Lokaal lokaal;
    private double start;
    private double end;
    private double duration;
    private Calendar startDate;
    private Calendar endDate;
    private Calendar durationCalendar;

    public Afspraak(Lokaal lokaal, microsoft.exchange.webservices.data.core.service.item.Appointment appt) throws Exception {
       
    	this.setLokaal(lokaal);
        appt.load(PropertySet.FirstClassProperties);
        this.setStartDate(appt.getStart());
        this.setEndDate(appt.getEnd());
    }

    
    
    public double getStart() {
		return start;
	}
    
	public void setStart(){
		start = startDate.getTimeInMillis(); 
		this.start = start;
	}
	
	public double getEnd() {
		return end;
	}
	
	public void setEnd() {
		 end = endDate.getTimeInMillis();
		 
		 this.end = end;
		
	}
	public double getDuration() {
		return duration;
	}
	
	public void setDuration(){
		
		duration = du;
		
		this.duration = duration;
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
    
    public Afspraak(Lokaal lokaal, Calendar startDate, Calendar endDate){
        this.lokaal = lokaal;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public Afspraak(){
        
    }
    
    public boolean berekenBeschikbaarheid(){
    	//lokaal
    	return true;
    }
    
    @Override
    public String toString(){
        return lokaal.toString() + " Start: " + startDate + " End: " + endDate;
    }
}
