package com.movie.Movie.controller;

import com.movie.Movie.DBR.MovieDBR;
import com.movie.Movie.resources.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/v1/movie")
public class MovieController {

    @Autowired
    private MovieDBR movieDBR;

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

}
