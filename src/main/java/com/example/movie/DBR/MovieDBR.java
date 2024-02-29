package com.example.movie.DBR;

import com.example.movie.resources.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MovieDBR extends JpaRepository<Movie, Integer> {

    //Override the findAll method with different return type
    List<Movie> findAll();

    //Override the findByField method with different return type
    List<Movie> findByMovieTitle(String movieTitle);


}