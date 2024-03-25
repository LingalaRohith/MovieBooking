package com.example.movie.controller;

import com.example.movie.DBR.ShowDBR;
import com.example.movie.resources.enums.ShowTimes;
import com.example.movie.resources.model.Show;
import com.example.movie.DBR.ShowDBR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.*;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class ShowController {
	
	@Autowired
	private ShowDBR showDao;
	
	@PostMapping("/addShow")
	public Map<Integer, Show> addShow(@RequestBody Show show) {
		Map<Integer, Show> map = new HashMap<Integer, Show>();
		if(isShowAvailable(show)) {
		  ResponseEntity.ok(showDao.save(show));
		  map.put(200, show);
		  return map;
		}
		else {
			 map.put(404, show);
			 return map;
		}
	}
	
	@PostMapping("/updateShow")
	public Map<Integer, Show> updateShow(@RequestBody Show show) {
		Map<Integer, Show> map = new HashMap<Integer, Show>();
		if(isShowAvailable(show)) {
		  ResponseEntity.ok(showDao.save(show));
		  map.put(400, show);
		  return map;
		}
		else {
			map.put(404, show);
			return map;
		}
	}
	
	@PostMapping("/deleteShow")
	public boolean deleteShow(@RequestBody Show show) {
		  showDao.delete(show);
		  return true;
	}
	

	public boolean deleteMovieShows(int movieId) {
		  List<Show> shows = showDao.findByMovieId(movieId);
		  for (Show show_entry : shows) {
			  showDao.delete(show_entry);
		  }
		  return true;
	}
	
	@PostMapping("/getShowsByMovieID") 
	public Map<Integer, List<Show>> getShowsByMovieID(@RequestBody Map<String, Integer> movieID) {
		List<Show> shows = showDao.findByMovieId(movieID.get("movieID"));
		Map<Integer, List<Show>> showMap = new HashMap<Integer, List<Show>>();
		showMap.put(400, shows);
		return showMap;
	
	}
	
	@PostMapping("/getAvailableShows") 
	public Map<Integer, List<ShowTimes>> getAvailableShows(@RequestBody Map<String, Date> showDate) {
		List<Show> shows = showDao.findByShowDate(showDate.get("showDate"));
		List<ShowTimes> showsList = new ArrayList<>(Arrays.asList(ShowTimes.values()));
		for (Show show : shows) {
			showsList.remove(show.getShowTime());
		}		
		Map<Integer, List<ShowTimes>> showMap = new HashMap<Integer, List<ShowTimes>>();
		showMap.put(400, showsList);
		return showMap;
	
	}
	
	@PostMapping("/getShowsByDate") 
	public Map<Integer, List<ShowTimes>> getShowsByDate(@RequestBody Show show) {
		List<Show> shows = showDao.findByShowDateAndMovieId(show.getShowDate(), show.getMovieId());
        List<ShowTimes> showsList = new ArrayList<ShowTimes>();
		for (Show show_obj : shows) {
			showsList.add(show_obj.getShowTime());
		}		
		Map<Integer, List<ShowTimes>> showMap = new HashMap<Integer, List<ShowTimes>>();
		showMap.put(400, showsList);
		return showMap;
	
	}
	
	@PostMapping("/getShow") 
	public Map<Integer, Show> getShowID(@RequestBody Show show) {
		List<Show> shows = showDao.findByShowDateAndShowTime(show.getShowDate(), show.getShowTime());	
		Map<Integer, Show> showMap = new HashMap<Integer, Show>();
		showMap.put(400, shows.get(0));
		return showMap;
	
	}
	
	
	
	public boolean isShowAvailable(Show show) {
		List<Show> shows = showDao.findByShowDateAndShowTime(show.getShowDate(), show.getShowTime() );
		if(shows.isEmpty()) {
			return true;
		}
		if(show.getShowId() != 0 && show.getShowId() == shows.get(0).getShowId()) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public Show getShowByShowID(int showID) {
		List<Show> shows = showDao.findByShowId(showID);
		return shows.get(0);	
	}
	
	
	
}
