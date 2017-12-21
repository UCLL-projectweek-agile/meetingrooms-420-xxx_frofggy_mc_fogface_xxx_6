/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domain.Lokaal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daan
 */
public class RoomInMemoryDb implements RoomDb{
    
    private final List<Lokaal> rooms;
    
    public RoomInMemoryDb(){
        rooms = new ArrayList<>();
        //D149 D148 D159 D146 D157 D145 D156 D144 D155 D143 D154 D153 D160
        rooms.add(new Lokaal("HSR-Thames@ucll.be","Thames","D143",0,0));
        rooms.add(new Lokaal("HSR-Yangtze@ucll.be","Yangtze","D144",0,0));
        rooms.add(new Lokaal("HSR-Schelde@ucll.be","Schelde","D145",0,0));
        rooms.add(new Lokaal("HSR-Sarine@ucll.be","Sarine","D146",0,0));
        rooms.add(new Lokaal("HSR-Rhone@ucll.be","Rhone","D148",0,0));
        rooms.add(new Lokaal("HSR-Po@ucll.be","Po","D149",0,0));
        rooms.add(new Lokaal("HSR-Moselle@ucll.be","Moselle","D153",0,0));
        rooms.add(new Lokaal("HSR-Ebro@ucll.be","Ebro","D154",0,0));
        rooms.add(new Lokaal("HSR-Maas@ucll.be","Maas","D155",0,0));
        rooms.add(new Lokaal("HSR-Douro@ucll.be","Douro","D156",0,0));
        rooms.add(new Lokaal("HSR-Donau@ucll.be","Donau","D157",0,0));
        rooms.add(new Lokaal("HSR-Chao-Praya@ucll.be","Chao-Praya","D159",0,0));
        rooms.add(new Lokaal("HSR-Arno@ucll.be","Arno","D160",0,0));
    }

    @Override
    public List<Lokaal> getRooms() {
        return rooms;
    }

    @Override
    public void addRoom(Lokaal lokaal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
