package com.airline.entity;

import java.time.LocalDate;

public class Booking {
private int id;
private int userId;
private int flightId;
private LocalDate bookingDate;

public Booking() {
	// TODO Auto-generated constructor stub
}

public Booking(int id, int userId, int flightId, LocalDate bookingDate) {
	this.id = id;
	this.userId = userId;
	this.flightId = flightId;
	this.bookingDate = bookingDate;
}

public int getId() {
	return id;
}


public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

public int getFlightId() {
	return flightId;
}

public void setFlightId(int flightId) {
	this.flightId = flightId;
}

public LocalDate getBookingDate() {
	return bookingDate;
}

public void setBookingDate(LocalDate bookingDate) {
	this.bookingDate = bookingDate;
}

@Override
public String toString() {
	return "Booking [id=" + id + ", userId=" + userId + ", flightId=" + flightId + ", bookingDate=" + bookingDate + "]";
}


}
