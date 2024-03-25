package com.example.movie.resources.model;

//import com.app.resources.enums.ShowTimes;
import com.example.movie.resources.enums.ShowTimes;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="show_schedule")
public class Show {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int showId;
	private int movieId;
	private Date showDate;
	@Enumerated(EnumType.ORDINAL)
	private ShowTimes showTime;
	private int duration_minutes;
	
	
	
	public Show() {
		super();
	}


	public Show(int showId, int movieId, Date showDate, ShowTimes showTime, int duration_minutes) {
		super();
		this.showId = showId;
		this.movieId = movieId;
		this.showDate = showDate;
		this.showTime = showTime;
		this.duration_minutes = duration_minutes;
	}


	public int getShowId() {
		return showId;
	}


	public void setShowId(int showId) {
		this.showId = showId;
	}


	public int getMovieId() {
		return movieId;
	}


	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public Date getShowDate() {
		return showDate;
	}


	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}


	public ShowTimes getShowTime() {
		return showTime;
	}


	public void setShowTime(ShowTimes showTime) {
		this.showTime = showTime;
	}


	public int getDuration_minutes() {
		return duration_minutes;
	}


	public void setDuration_minutes(int duration_minutes) {
		this.duration_minutes = duration_minutes;
	}
	
	
}
