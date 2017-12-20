/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;
import microsoft.exchange.webservices.data.core.PropertySet;

/**
 *
 * @author Daan
 */
public class Afspraak {
    
    private Lokaal lokaal;

    public Afspraak(Lokaal lokaal, microsoft.exchange.webservices.data.core.service.item.Appointment appt) throws Exception {
       setLokaal(lokaal);
       
        appt.load(PropertySet.FirstClassProperties);
        this.setStartDate(appt.getStart());
        this.setEndDate(appt.getEnd());
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
    private Date startDate;
    private Date endDate;
    
    public Afspraak(Lokaal lokaal, Date startDate, Date endDate){
        this.lokaal = lokaal;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public Afspraak(){
        
    }
    
    @Override
    public String toString(){
        return lokaal.toString() + " Start: " + startDate + " End: " + endDate;
    }
}
