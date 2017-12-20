/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

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
    private Date startDate;
    private Date endDate;

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
		start = startDate.getHours() + (startDate.getMinutes() / 60) + (startDate.getSeconds() / 3600); 
		this.start = start;
	}
	
	public double getEnd() {
		return end;
	}
	
	public void setEnd(microsoft.exchange.webservices.data.core.service.item.Appointment appt) throws ServiceLocalException {
		 end = appt.getEnd().getHours() + (appt.getEnd().getMinutes() / 60) + (appt.getEnd().getSeconds() / 3600);
		 
		 this.end = end;
		
	}
	public double getDuration() {
		return duration;
	}
	
	public void setDuration(microsoft.exchange.webservices.data.core.service.item.Appointment appt) throws ServiceLocalException {
		
		duration = appt.getDuration().getHours() + (appt.getDuration().getMinutes() / 60) + (appt.getDuration().getSeconds() / 3600);
		
		this.duration = duration;
	}
	
	public Lokaal getLokaal() {
        return lokaal;
    }

    public void setLokaal(Lokaal lokaal) {
        this.lokaal = lokaal;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public Afspraak(Lokaal lokaal, Date startDate, Date endDate){
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
