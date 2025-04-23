package com.airline.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.airline.service.BookingService;

@WebServlet("/bookFlight")
public class BookFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int userId = (int)(request.getSession().getAttribute("userId"));
		int flightId =  Integer.parseInt(request.getParameter("flightId"));
		boolean isBooked = new BookingService().bookFlight(userId, flightId);
		
		if(isBooked)
		{
			request.getRequestDispatcher("show-booking.jsp").include(request, response);		}
		else {
			out.println("<h2 style='color:red'>Failed to Book Flight !!!</h2>");
			 request.getRequestDispatcher("search-result.jsp").include(request, response);
		}
	}

}
