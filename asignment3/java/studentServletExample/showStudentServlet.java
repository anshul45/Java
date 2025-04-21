package studentServletExample;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import practise.Student;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/showStudent")
public class showStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Object obj = request.getAttribute("loadedStudent");
		Student st = (Student)obj;
		String name = st.getName();
		String city = st.getCity();
		out.println("<h2>Showing Student Details: </h2>");
		out.println("<h2>Name: " + name + "</h2>");
		out.println("<h2>City: "+ city + "</h2>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
