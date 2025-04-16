package jdbcpractice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class StudentDao implements JdbcDao<Student, Integer> {

	@Override
	public Collection<Student> getAll() {
		Collection<Student> students = new ArrayList<Student>();
		String sqlQuery = "select * from student";
		try (Connection conn = JdbcUtil.getConnnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sqlQuery);) {
			while (rs.next()) {
				students.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}

	@Override
	public Student getone(Integer rollno) {
		String sqlQuery = "select * from student where rollNo = ?";
		Student std = null;
		try (Connection conn = JdbcUtil.getConnnection(); 
			PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
			pstmt.setInt(1, rollno);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				std = new Student(rs.getInt(1), rs.getString(2), rs.getString(3));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return std;
	}

	@Override
	public void add(Student std) {
		String sqlQuery = "insert into student values(?,?,?)";
		try(Connection conn = JdbcUtil.getConnnection();
				PreparedStatement pstmt = conn.prepareStatement(sqlQuery))
		{
			pstmt.setInt(1, std.getRollno());
			pstmt.setString(2, std.getName());
			pstmt.setString(3, std.getCity());
			
			int count = pstmt.executeUpdate();
			System.out.println(count+" lines updated!");
		}
		catch (Exception e) {
		e.printStackTrace();	
		}

	}

}
