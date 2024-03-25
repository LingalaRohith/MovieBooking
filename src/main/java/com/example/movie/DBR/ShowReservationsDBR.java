package com.example.movie.DBR;

import com.example.movie.resources.model.ShowReservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowReservationsDBR extends JpaRepository<ShowReservations, Integer> {

	
	List<ShowReservations> findByShowID(int showID);
	
}
