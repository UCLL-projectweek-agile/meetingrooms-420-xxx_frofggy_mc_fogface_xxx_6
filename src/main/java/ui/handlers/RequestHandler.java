/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.handlers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import meetingrooms.Service;

/**
 *
 * @author Daan
 */

public interface RequestHandler {

	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	public void setModel(Service model);
	
}
