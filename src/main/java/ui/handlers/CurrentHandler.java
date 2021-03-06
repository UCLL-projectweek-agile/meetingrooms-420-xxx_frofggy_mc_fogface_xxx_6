/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import meetingrooms.Service;
import microsoft.exchange.webservices.data.core.service.item.Appointment;
import ui.controller.Servlet;

/**
 *
 * @author Daan
 */
@Mapping("timetable")
public class CurrentHandler implements RequestHandler{

    private Service service;
    
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<domain.Afspraak> appoints;
        try {
            //TODO
            appoints = service.getAppointmentsToday();
            request.setAttribute("afspraken", appoints);
        } catch (Exception ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("TimeTable.jsp").forward(request, response);
    }

    @Override
    public void setModel(Service model) {
        this.service = model;
    }
    
}
