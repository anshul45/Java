package com.airline.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.airline.entity.User;
import com.airline.service.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		response.setContentType("text/html");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();

		User user = new UserService().login(email, password);

		if (user != null) {
			out.println("<h3>Login sucessfully!!</h3>");
			out.println("<h3>Welcome " + user.getName() + "</h3>");
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getId());
			response.sendRedirect("search.jsp");
		} else {
			out.println("<h3>Invalid Credential!! Try again</h3>");
			dispatcher.include(request, response);
		}
	}

}
