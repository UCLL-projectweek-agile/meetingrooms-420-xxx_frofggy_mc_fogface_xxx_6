/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;

/**
 *
 * @author Daan
 */
public class Appointment {
    
    private Lokaal lokaal;

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
    
    public Appointment(Lokaal lokaal, Date startDate, Date endDate){
        this.lokaal = lokaal;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public Appointment(){
        
    }
    
}
