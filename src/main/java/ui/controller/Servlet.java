package ui.controller;

import db.EwsReservationsDb;
import domain.Klant;
import meetingrooms.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import microsoft.exchange.webservices.data.core.service.item.Appointment;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        private static EwsReservationsDb db;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        
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
        ExchangeCredentials credentials = new WebCredentials("sa_uurrooster", "JLxkK4BDUre3");
        db = new EwsReservationsDb(rooms, credentials);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		verwerk(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		verwerk(request, response);
	}
	
	protected void verwerk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String doel = "index.jsp";	
		
		if (action != null){
                    switch(action){
                    case "check":
                            doel = check(request, response);
                            break;
                    case "current":
                            doel = current(request, response);
                            break;
                    case "overview":
                    	doel = overview(request, response);
                    	break;
                    default:
                        doel = "index.jsp";
                    }
		}
		RequestDispatcher rd = request.getRequestDispatcher(doel);
		rd.forward(request, response);
                /*
                String req = request.getParameter("action");
		try{
			RequestHandler rq = this.requestHandlerFactory.create(req);
			rq.handle(request, response);
		}catch(Exception e) {
                    e.printStackTrace();
		}
                */
		
	}
	
	public String check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "LokaaloverviewOud.jsp";
	}

    private String current(HttpServletRequest request, HttpServletResponse response) {
        
        Date startDate = new Date();
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 3600000);
        List<Appointment> appointments;
        List<domain.Appointment> appoints = new ArrayList<>();
        try {
            appoints = db.findAllAppointments(startDate, endDate);
            request.setAttribute("appointments", appoints);
        } catch (Exception ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "currentoccupation.jsp";
    }
    
    private String overview(HttpServletRequest request, HttpServletResponse response) {
    	
    	Service service = new Service();
    	List<List<Klant>> rooms = service.printAppointmentsvoorWeb();
    	service.printAppointmentsvoorWeb();
    	request.setAttribute("klanten", rooms);
    	return "LokaaloverviewOud.jsp";
    }
		
}
