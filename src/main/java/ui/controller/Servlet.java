package ui.controller;

import db.EwsReservationsDb;
import domain.Afspraak;
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
import ui.handlers.RequestHandler;
import ui.handlers.RequestHandlerFactory;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        private final EwsReservationsDb db;
        private RequestHandlerFactory requestHandlerFactory;
        private final Service service;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() throws Exception{
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
        service = new Service();
        requestHandlerFactory = new RequestHandlerFactory(service);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		verwerk(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		verwerk(request, response);
	}
	
	protected void verwerk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String req = request.getParameter("action");
		try{
			RequestHandler rq = this.requestHandlerFactory.create(req);
			rq.handle(request, response);
		}catch(Exception e) {
                    e.printStackTrace();
		}
        }
<<<<<<< HEAD
        return "currentoccupation.jsp";
    }
    
    private String overview(HttpServletRequest request, HttpServletResponse response) {
    	
    	Service service = new Service();
    	List<List<Afspraak>> rooms = service.printAppointmentsvoorWeb();
    	request.setAttribute("afspraken", rooms);
    	return "LokaaloverviewOud.jsp";
    }
		
=======
>>>>>>> f3ccc069389e6d7b85e194d8a6b2f74fd546910b
}
