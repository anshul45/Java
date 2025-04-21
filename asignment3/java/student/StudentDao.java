package practise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class StudentDao implements JdbcDao<Student, Integer> {

	@Override
	public Collection<Student> getAll() {
		Collection<Student> allStudents = new ArrayList<>();
		try(
		Connection dbConnection = jdbcUtil.buildConnection();
		Statement stmt = dbConnection.createStatement();
		ResultSet rs = stmt.executeQuery("select * from student")
				){
		while(rs.next()) {
			allStudents.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3)););
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return allStudents;
	}
	
	@Override
	public Student getOne(Integer Rollno) {
		Student foundStudent = null;
		String sqlQuery = "select * from student where rollno = ?";
		try(
				Connection dbConnection = jdbcUtil.buildConnection();
				PreparedStatement pstmt =
						dbConnection.prepareStatement(sqlQuery);
				){
			pstmt.setInt(1, Rollno);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				foundStudent = new Student(rs.getInt(1),rs.getString(2),rs.getString(3));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
			return foundStudent;
		}

	@Override
	public void add(Student st) {
		String sqlQuery = "insert into student values (?,?,?)";
		try(
				Connection dbConnection = jdbcUtil.buildConnection();
				PreparedStatement pstmt =
						dbConnection.prepareStatement(sqlQuery);
				){
			pstmt.setInt(1, st.getRollno());
			pstmt.setString(2, st.getName());
			pstmt.setString(3, st.getCity());
			
			int updateCount = pstmt.executeUpdate();
			System.out.println(updateCount+" record inserted");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
