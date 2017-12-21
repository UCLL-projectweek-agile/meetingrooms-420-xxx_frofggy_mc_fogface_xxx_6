/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingrooms;

import db.EwsReservationsDb;
import domain.Afspraak;
import domain.Lokaal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daan
 */
public class DataFetchingTask implements Runnable {

    private final EwsReservationsDb db;
    private List<Afspraak> afspraken;
    
    public DataFetchingTask(List<Lokaal> rooms, EwsReservationsDb ews) {
        db = ews;
    }

    @Override
    public void run() {
        
        Logger.getLogger(DataFetchingTask.class.getName()).log(Level.INFO, null, "Fetch");
        Calendar calStart = new GregorianCalendar();
        calStart.setTime(new Date());
        calStart.set(Calendar.HOUR_OF_DAY, 0);
        calStart.set(Calendar.MINUTE, 0);
        calStart.set(Calendar.SECOND, 0);
        calStart.set(Calendar.MILLISECOND, 0);
        Date midnightYesterday = calStart.getTime();

        Calendar calEnd = new GregorianCalendar();
        calEnd.setTime(new Date());
        calEnd.set(Calendar.DAY_OF_YEAR, calEnd.get(Calendar.DAY_OF_YEAR)+1);
        calEnd.set(Calendar.HOUR_OF_DAY, 0);
        calEnd.set(Calendar.MINUTE, 0);
        calEnd.set(Calendar.SECOND, 0);
        calEnd.set(Calendar.MILLISECOND, 0);
        Date midnightTonight = calEnd.getTime();
        try {
            this.afspraken = db.findAllAppointments(midnightYesterday, midnightTonight);
        } catch (Exception ex) {
            Logger.getLogger(DataFetchingTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Afspraak> getAfspraken(){
        return afspraken;
    }

}
