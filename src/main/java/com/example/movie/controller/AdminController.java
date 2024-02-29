package com.example.movie.controller;

import com.example.movie.DBR.AdminDBR;
import com.example.movie.resources.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins="http://localhost:4200") 
public class AdminController {
	
	@Autowired
	private AdminDBR adminDao;

	
	
	@PostMapping("/registerAdmin")
	public ResponseEntity<Admin> register(@RequestBody Admin admin) {
		
		
		if(!checkAdminExists(admin)) {
			 ResponseEntity.ok(adminDao.save(admin));
			 return new ResponseEntity<Admin>(admin, HttpStatus.OK); 
		}
		return new ResponseEntity<Admin>(admin, HttpStatus.BAD_REQUEST); 
	}
	
	
	@PostMapping("/updateAdmin")
	public ResponseEntity<Admin> update(@RequestBody Admin admin) {
		if(!checkAdminExists(admin)) {
			return new ResponseEntity<Admin>(admin, HttpStatus.NO_CONTENT); 
		}
		ResponseEntity.ok(adminDao.save(admin));
		return new ResponseEntity<Admin>(admin, HttpStatus.OK); 
	}

	
	@GetMapping("/getAllAdmins")
	public List<Admin> getAllCustomers() {
		 List<Admin> list =  adminDao.findAll();
		 return list;
	}
	 
	
	 public boolean checkAdminExists (@RequestBody Admin admin ) {
		 
		 String email = admin.getEmail();
		 List<Admin> adminList = adminDao.findByEmail(email);
		  
		  if (adminList.isEmpty()) { 
			  System.out.println("No admin email found");
			  return false;
		  }
		  
		  return true;
	 
	 }
	 
	 @PostMapping("/getAdmin") 
	 public ResponseEntity<Admin> getAdmin (@RequestBody Admin admin ) {
		  
	      String email = admin.getEmail();
	      String password = admin.getPassword();
		  List<Admin> adminList = adminDao.findByEmail(email);
		  
		 if (!checkAdminExists(admin)) {
			  return new ResponseEntity<Admin>(HttpStatus.NO_CONTENT); 
		  }
		  	
		  adminList = adminDao.findByEmailAndPassword(email, password);
		  
		  if (adminList.isEmpty()) { 
			  System.out.println("Incorrect Password");
			  return new ResponseEntity<Admin>(HttpStatus.NON_AUTHORITATIVE_INFORMATION); 
		  }

		  Admin customerResponse = adminList.get(0);
		  return new ResponseEntity<Admin>(customerResponse,
		  HttpStatus.OK);
	  
	  }

	  @PostMapping("/deleteAdmin")
	  public boolean deleteAdmin(@RequestBody Admin admin) {
		  adminDao.delete(admin);
		  return true;
	  }
	

}
