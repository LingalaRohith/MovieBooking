package com.example.movie.DBR;

import com.example.movie.resources.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDBR extends JpaRepository<Customer, Integer> {
	
	    //Override the findAll method with different return type
		List<Customer> findAll();
		
		//Override the findByField method with different return type
		List<Customer> findByUserID(int userID);
		
		//Override the findByField method with different return type
		List<Customer> findByEmailAndPassword(String email, String password);
		
		List<Customer> findByEmail(String email);
		
		//Get the customers who subscribed for promotions
		List<Customer> findBypromotionsSubscribed(boolean promotionsSubscribed);



}
