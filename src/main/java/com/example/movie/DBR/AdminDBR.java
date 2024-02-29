package com.example.movie.DBR;

import com.example.movie.resources.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdminDBR extends JpaRepository<Admin, Integer> {
	
	 //Override the findAll method with different return type
	List<Admin> findAll();
	
	//Override the findByField method with different return type
	List<Admin> findByUserID(int userID);
	
	//Override the findByField method with different return type
	List<Admin> findByEmailAndPassword(String email, String password);
	
	List<Admin> findByEmail(String email);

}
