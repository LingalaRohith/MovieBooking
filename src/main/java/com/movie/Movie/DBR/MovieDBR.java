package com.movie.Movie.DBR;

import com.movie.Movie.resources.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MovieDBR extends JpaRepository<Movie, Integer> {

    //Override the findAll method with different return type
    List<Movie> findAll();

    //Override the findByField method with different return type
    List<Movie> findByMovieTitle(String movieTitle);

}