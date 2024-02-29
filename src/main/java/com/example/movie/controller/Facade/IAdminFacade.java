package com.example.movie.controller.Facade;

import com.example.movie.resources.model.Admin;
import com.example.movie.resources.model.Customer;
import com.example.movie.resources.model.Movie;
import com.example.movie.resources.model.Promotion;

import java.util.Map;


public interface IAdminFacade {

	Map<Integer, Movie> addMovie(Movie movie);
	void addAdmin(Admin admin);
	void addPromotion(Promotion promo);
	
	Map<Integer, Movie> updateMovie(Movie movie);
	void updateAdmin(Admin admin);
	void updateCustomer(Customer customer);

	
	boolean deleteMovie(Movie movie);
	void deleteAdmin(Admin admin);
	void deletePromotion(Promotion promo);
	void deleteCustomer(Customer customer);
	
	
}
