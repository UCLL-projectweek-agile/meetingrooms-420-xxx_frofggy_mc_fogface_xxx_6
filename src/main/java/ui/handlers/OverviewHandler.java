/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.handlers;

import domain.Klant;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import meetingrooms.Service;

/**
 *
 * @author Daan
 */
@Mapping("overview")
public class OverviewHandler implements RequestHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Service service = new Service();
    	List<List<Klant>> klanten = service.printAppointmentsvoorWeb();
    	service.printAppointmentsvoorWeb();
    	request.setAttribute("klanten", klanten);
    	request.getRequestDispatcher("Lokaaloverview.jsp").forward(request, response);
    }

    @Override
    public void setModel(Service model) {
        
    }
    
}
