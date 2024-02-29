package com.example.movie.controller.FacadeImp;

import com.example.movie.controller.AdminController;
import com.example.movie.controller.MovieController;
import com.example.movie.controller.PromotionController;
//import com.app.controller.ShowController;
import com.example.movie.controller.Facade.IAdminFacade;
import com.example.movie.resources.model.Admin;
import com.example.movie.resources.model.Customer;
import com.example.movie.resources.model.Movie;
import com.example.movie.resources.model.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:4200") 
public class ConcreteAdminFacade implements IAdminFacade {
	
	@Autowired
	public AdminController adminController;
	@Autowired
	public MovieController movieController;
	@Autowired
	public PromotionController promotionController;
//	@Autowired
//	public ShowController showController;

	@Override
	@PostMapping("/addmovie")
	public Map<Integer, Movie> addMovie(@RequestBody Movie movie) {
		return movieController.addMovie(movie);
	}

	@Override
	@PostMapping("/addadmin")
	public void addAdmin(Admin admin) {
		adminController.register(admin);
	}

	@Override
	@PostMapping("/registerpromo")
	public void addPromotion(Promotion promo) {
		promotionController.addPromo(promo);
	}

	@Override
	@PostMapping("/updatemovie")
	public Map<Integer, Movie> updateMovie(@RequestBody Movie movie) {
		return movieController.editmovie(movie);
	}

	@Override
	public void updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		
	}


	@Override
	@PostMapping("/deletemovie")
	public boolean deleteMovie(Movie movie) {
//		showController.deleteMovieShows(movie.getId());
		movieController.deleteMovie(movie);
		return true;
	}

	@Override
	public void deleteAdmin(Admin admin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePromotion(Promotion promo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}
	
	

}
