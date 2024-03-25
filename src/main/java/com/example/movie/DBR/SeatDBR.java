package com.example.movie.DBR;

import com.example.movie.resources.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatDBR extends JpaRepository<Seat, String> {

	List<Seat> findBySeatNumber(String seatNumber);
	
	List<Seat> findByIsReserved(boolean isReserved);
	
	
}
