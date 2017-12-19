/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingrooms.ui;

import db.EwsReservationsDb;
import domain.Lokaal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import meetingrooms.Service;
import microsoft.exchange.webservices.data.core.service.item.Appointment;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;

/**
 *
 * @author Daan
 */
@WebServlet(name = "Current", urlPatterns = {"/Current"})
public class Current extends HttpServlet {

    Service service = new Service();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> rooms = new ArrayList<>();
        rooms.add("HSR-Yangtze@ucll.be");
        rooms.add("HSR-Schelde@ucll.be");
        rooms.add("HSR-Sarine@ucll.be");
        rooms.add("HSR-Rhone@ucll.be");
        rooms.add("HSR-Po@ucll.be");
        rooms.add("HSR-Ebro@ucll.be");
        rooms.add("HSR-Maas@ucll.be");
        rooms.add("HSR-Douro@ucll.be");
        rooms.add("HSR-Donau@ucll.be");
        rooms.add("HSR-Chao-Praya@ucll.be");
        rooms.add("HSR-Arno@ucll.be");
        ExchangeCredentials credentials
                = new WebCredentials("sa_uurrooster", "JLxkK4BDUre3");
        EwsReservationsDb db = new EwsReservationsDb(rooms, credentials);
        
        Date startDate = new Date();
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 3600000);
        List<Appointment> appointments;
        List<domain.Appointment> appoints = new ArrayList<>();
        try {
            appoints = db.findAllAppointments(startDate, endDate);
            request.setAttribute("appointments", appoints);
        } catch (Exception ex) {
            Logger.getLogger(Current.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        request.getRequestDispatcher("currentoccupation.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
