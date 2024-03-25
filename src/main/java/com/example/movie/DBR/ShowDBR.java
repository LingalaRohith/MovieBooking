package com.example.movie.DBR;

import com.example.movie.resources.enums.ShowTimes;
import com.example.movie.resources.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ShowDBR extends JpaRepository<Show, Integer> {

	List<Show> findByShowId(int showId);
	
	List<Show> findByMovieId(int movieId);
	
	List<Show> findByShowDateAndShowTime(Date showDate, ShowTimes showTime);
	
	List<Show> findByShowDate(Date showDate);
	
	List<Show> findByShowDateAndMovieId(Date showDate, int movieId);

}
