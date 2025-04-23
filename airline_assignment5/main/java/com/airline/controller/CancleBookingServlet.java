package com.airline.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.airline.dao.BookingDao;

@WebServlet("/cancleBooking")
public class CancleBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int id = Integer.parseInt(request.getParameter("bookingId"));

		
		boolean status = new BookingDao().cancleFlight(id);
		
		if(status) {
			response.getWriter().println("<h1>Flight cancle sucessfully!</h1>");
			request.getRequestDispatcher("show-booking.jsp").include(request, response);
		}
		else
		{
			response.getWriter().println("<h1>Flight `ssfully!</h1>");
			request.getRequestDispatcher("show-booking.jsp").include(request, response);
		}
	}

}
