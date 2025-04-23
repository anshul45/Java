package com.airline.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.airline.entity.Flight;
import com.airline.util.JdbcUtil;

public class FlightDao {
	String query = null;

	public List<Flight> searchFlight(String source, String destination, LocalDate date) {
		query = "select * from flight where source = ? and destination = ? and flight_date =?";

		List<Flight> flights = new ArrayList<Flight>();

		try (Connection conn = JdbcUtil.getDbConnection(); PreparedStatement pstmt = conn.prepareStatement(query);) {
			pstmt.setString(1, source);
			pstmt.setString(2, destination);
			pstmt.setDate(3, Date.valueOf(date));

			ResultSet st = pstmt.executeQuery();

			while (st.next()) {
				flights.addLast(new Flight(st.getInt("id"), st.getString("flight_number"), st.getString("source"),
						st.getString("destination"), st.getDate("flight_date").toLocalDate(), st.getDouble("price")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flights;
	}
}
