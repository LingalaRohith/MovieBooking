
package com.example.movie.resources.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="seat")
public class Seat {
	
	@Id
	private String seatNumber;
	private boolean isReserved;
	
	
	
	public Seat() {
		super();
	}


	public Seat(String seatNumber, boolean isReserved) {
		super();
		this.seatNumber = seatNumber;
		this.isReserved = isReserved;
	}


	public String getSeatNumber() {
		return seatNumber;
	}


	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public boolean isReserved() {
		return isReserved;
	}


	public void setReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}
		

}
