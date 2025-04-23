package com.airline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import com.airline.entity.Booking;
import com.airline.util.JdbcUtil;

public class BookingDao {
	public boolean save(int userId, int flightId) {
		boolean status = false;
		String query = "insert into booking(user_id,flight_id) values(?,?)";
		try (Connection conn = JdbcUtil.getDbConnection(); PreparedStatement pstmt = conn.prepareStatement(query);) {
			pstmt.setInt(1, userId);
			pstmt.setInt(2, flightId);

			int count = pstmt.executeUpdate();
			if (count > 0) {
				System.out.println(count + " Rows inserted");
				status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public List<Booking> getAllFlights() {
		List<Booking> bookings = new ArrayList<Booking>();
		String query = "select * from booking";
		try (Connection conn = JdbcUtil.getDbConnection(); Statement stmt = conn.createStatement();) {
			ResultSet st = stmt.executeQuery(query);

			while (st.next()) {
				bookings.add(new Booking(st.getInt("id"), st.getInt("user_id"), st.getInt("flight_id"),
						st.getDate("booking_date").toLocalDate()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookings;
	}
	
	public boolean cancleFlight(int id)
	{
		Boolean status = false;
		String query = "delete from booking where id = ?;";
		try (Connection conn = JdbcUtil.getDbConnection(); PreparedStatement pstmt = conn.prepareStatement(query);) {
			pstmt.setInt(1, id);
			
			int count = pstmt.executeUpdate();
			
			if(count > 0)
				status = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
}
