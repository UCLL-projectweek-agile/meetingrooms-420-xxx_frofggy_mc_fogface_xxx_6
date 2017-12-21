/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domain.Afspraak;
import java.util.List;

public interface ReservationDb {
	
    
    public List<Afspraak> getReservations();
    
    public Afspraak getreservation(int id);
    
    public void addReservation(Afspraak afspraak);
    
}
