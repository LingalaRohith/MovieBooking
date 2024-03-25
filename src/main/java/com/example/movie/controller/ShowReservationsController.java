package com.example.movie.controller;

import com.example.movie.DBR.ShowReservationsDBR;
import com.example.movie.resources.model.ShowReservations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class ShowReservationsController {
	
	@Autowired
	private ShowReservationsDBR reservationsDao;
	
	@PostMapping("/getReservedSeats")
	public Map<Integer, List<String>> getReservedSeats(@RequestBody Map<String, Integer> request) {
		
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		List<ShowReservations> reservations = reservationsDao.findByShowID(request.get("showID"));
		
		List<String> reservedSeats = new ArrayList<String>();
		for (ShowReservations reservation : reservations) {
			reservedSeats.add(reservation.getSeatNumber());
		}
		
		map.put(400, reservedSeats);
		return map;
		
	}
	

	public Map<Integer, String> reserveSeat(ShowReservations request) {
		
		Map<Integer, String> result = new HashMap<Integer, String>();
		String[] seats = request.seatNumber.split(",");
		for (int i = 0; i < seats.length; i++) {
			ShowReservations reservation = new ShowReservations(seats[i], request.getShowID());
			reservationsDao.save(reservation);
		}
		result.put(400, "Successful");
		return result;
		
	}
	
	
		
	
	
}
