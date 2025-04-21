package studentServletExample;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import practise.JDBCdao;
import practise.Student;
import practise.StudentDao;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addStudent")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int RollNo = Integer.parseInt(request.getParameter("rollno"));
		String Name = request.getParameter("name");
		String City = request.getParameter("city");
		
		Student currentStudent = new Student(RollNo,Name,City);
		JDBCdao<Student, Integer> dao = new StudentDao();
		dao.add(currentStudent);
		out.println("<h2>Student added successfully.</h2>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
