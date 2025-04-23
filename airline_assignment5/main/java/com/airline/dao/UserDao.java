package com.airline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.airline.entity.User;
import com.airline.util.JdbcUtil;

public class UserDao {

	private String query;

	public boolean save(User user) {

		boolean status = false;
		query = "insert into user(name,email,password) values(?,?,?)";

		try (Connection conn = JdbcUtil.getDbConnection(); PreparedStatement pstmt = conn.prepareStatement(query);) {
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPassword());

			int updatedCount = pstmt.executeUpdate();

			if (updatedCount > 0) {
				System.out.println(updatedCount + " rows affected!");
				status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}
	
	public List<User> findAll(){
		query = "select * from user";
		List<User> users = new ArrayList<User>();
		try (Connection conn = JdbcUtil.getDbConnection(); Statement stmt = conn.createStatement();) {
			ResultSet st = stmt.executeQuery(query);
			while(st.next())
			{
				users.add(new User(st.getInt("user_id"),st.getString("name"),st.getString("email"),st.getString("password")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return users;
	}
	
	public User searchUserByEmailAndPassword(String email,String password)
	{
		User user = null;
		query = "select * from user where email = ? and password = ?";
		try (Connection conn = JdbcUtil.getDbConnection(); PreparedStatement pstmt = conn.prepareStatement(query);) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet st = pstmt.executeQuery();

			if(st.next()) {
				user = new User(st.getInt("user_id"),st.getString("name"),st.getString("email"),st.getString("password"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
}
