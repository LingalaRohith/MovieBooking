package com.example.movie.resources.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name="show_reservations")
@IdClass(ShowReservationsId.class)
public class ShowReservations {
	
	@Id
	public String seatNumber;
	@Id
	public int showID;
	
	public ShowReservations(String seatNumber, int showID) {
		super();
		this.seatNumber = seatNumber;
		this.showID = showID;
	}

	public ShowReservations() {
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
