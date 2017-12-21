/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.handlers;

import domain.Lokaal;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import meetingrooms.Service;

/**
 *
 * @author Daan
 */
@Mapping("index")
public class IndexHandler implements RequestHandler{
private Service service;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Map<String, Boolean> lokalenKleuren = service.getCurrentOccupation();
       for(String lokaal : lokalenKleuren.keySet()){
           if(lokaal != null){
               request.setAttribute(lokaal, lokalenKleuren.get(lokaal) ? "green" : "red");
           }
       }
       request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    @Override
    public void setModel(Service model) {
        service = model;
    }
    
}
