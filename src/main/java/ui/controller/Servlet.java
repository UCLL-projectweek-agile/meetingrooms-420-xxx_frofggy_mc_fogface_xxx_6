package ui.controller;

import db.EwsReservationsDb;
import db.RoomInMemoryDb;
import domain.Lokaal;
import meetingrooms.Service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import ui.handlers.RequestHandler;
import ui.handlers.RequestHandlerFactory;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final EwsReservationsDb db;
    private final RequestHandlerFactory requestHandlerFactory;
    private final Service service;

    /**
     * @throws java.lang.Exception
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() throws Exception {
        super();

        List<Lokaal> rooms = (new RoomInMemoryDb()).getRooms();
        ExchangeCredentials credentials = new WebCredentials("sa_uurrooster", "JLxkK4BDUre3");
        db = new EwsReservationsDb(rooms, credentials);
        service = new Service(rooms);
        requestHandlerFactory = new RequestHandlerFactory(service);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        verwerk(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        verwerk(request, response);
    }

    protected void verwerk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String req = request.getParameter("action");
        req = (req == null) ? "index" : req;
        try {
            RequestHandler rq = this.requestHandlerFactory.create(req);
            rq.handle(request, response);
        } catch (IOException | ServletException e) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
