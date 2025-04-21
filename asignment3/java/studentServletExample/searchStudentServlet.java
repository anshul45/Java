package studentServletExample;

import jakarta.servlet.RequestDispatcher;
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



@WebServlet("/searchStudent")
public class searchStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int Rollno = Integer.parseInt(request.getParameter("rollno"));
		
		JDBCdao<Student, Integer> dao = new StudentDao();
		Student foundStudent = dao.getOne(Rollno);
		RequestDispatcher dispatcher = null;
		if(foundStudent == null) {
			out.println("<h2> Student Not Found due to invalid Roll number. Please try again.</h2>");
			dispatcher = request.getRequestDispatcher("searchStudent.html");
			dispatcher.include(request, response);
		}else {
			dispatcher = request.getRequestDispatcher("showStudent");
			request.setAttribute("loadedStudent", foundStudent);
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
