package com.example.movie.resources.model;

import java.io.Serializable;

public class ShowReservationsId implements Serializable {
	
	public String seatNumber;
	public int showID;
	
	public ShowReservationsId(String seatNumber, int showID) {
		super();
		this.seatNumber = seatNumber;
		this.showID = showID;
	}

	public ShowReservationsId() {
		super();
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public int getShowID() {
		return showID;
	}

	public void setShowID(int showID) {
		this.showID = showID;
	}
	
}
