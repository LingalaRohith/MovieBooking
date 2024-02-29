package com.example.movie.controller;

import com.example.movie.DBR.MovieDBR;
import com.example.movie.resources.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/v1/movie")
public class MovieController {

    @Autowired
    private MovieDBR movieDBR;


    public Map<Integer, Movie> addMovie(@RequestBody Movie movie) {
        ResponseEntity.ok(movieDBR.save(movie));
        Map<Integer, Movie> map = new HashMap<Integer, Movie>();
        map.put(200, movie);
        return map;
    }

    @GetMapping("/getallmovies")
    public List<Movie> getinfo() {
        List<Movie> list =  movieDBR.findAll();
        return list;
    }

    @PostMapping("/getmoviedetails")
    public ResponseEntity<Movie> getMovieDetailByTitle(@RequestBody Movie movie ) {

        String moviename=movie.getMovieTitle();
        List<Movie> movieList = movieDBR.findByMovieTitle(moviename);

        if (movieList.isEmpty()) {
            return new ResponseEntity<Movie>(HttpStatus.NO_CONTENT);
        }

        Movie movieResponse = movieList.get(0);
        return new ResponseEntity<Movie>(movieResponse,
                HttpStatus.OK);

    }

    public Map<Integer, Movie> editmovie(@RequestBody Movie movie) {
        ResponseEntity.ok(movieDBR.save(movie));
        Map<Integer, Movie> map = new HashMap<Integer, Movie>();
        map.put(400, movie);
        return map;
    }

    public boolean deleteMovie(@RequestBody Movie movie) {
        movieDBR.delete(movie);
        return true;
    }

    public Movie getMovieById(int id) {
        return movieDBR.findById(id).get();
    }
}
