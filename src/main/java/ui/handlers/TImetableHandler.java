/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.handlers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Afspraak;
import meetingrooms.Service;

/**
 *
 * @author Daan
 */
@Mapping("timetable")
public class TImetableHandler implements RequestHandler{
	private Service service;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		List<Afspraak> rooms = service.printAppointmentsvoorWeb();
    	request.setAttribute("afspraken", rooms);
        request.getRequestDispatcher("DemoTime.jsp").forward(request,response);
    }

    @Override
    public void setModel(Service model) {
        
    }
    
}
