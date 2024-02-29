package com.example.movie.controller;

import com.example.movie.DBR.PromotionDBR;
import com.example.movie.resources.model.Customer;
import com.example.movie.resources.model.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:4200") 
public class PromotionController {
	
	@Autowired
	private PromotionDBR promotionDao;
	@Autowired
	private CustomerController customerController;
	
	//add promotion
	@PostMapping("/addPromo")
	public Map<Integer, Promotion> addPromo(@RequestBody Promotion promo) {
		
		Map<Integer, Promotion> result = new HashMap<Integer, Promotion>();
		if(!checkPromoExists(promo)) {
			 ResponseEntity.ok(promotionDao.save(promo));
			 List<Customer> list = customerController.getSubscribedCustomers();
			 for (Customer cust : list) {
				 String emailSubject = "Exciting News: Exclusive Promotion Inside!";
				 customerController.sendEmail(cust, promo.toString(),emailSubject);
			 }
			 result.put(200, promo);
			 return result; 
		}
		result.put(208, promo);
		return result; 
	}
	
	
	//get all the promo codes
	@GetMapping("/getAllPromos")
	public List<Promotion> getAllPromotions() {
		 List<Promotion> list =  promotionDao.findAll();
		 return list;
	}
	
	
	//update promotion
	@PostMapping("/updatePromo")
	public ResponseEntity<Promotion> updatePromo(@RequestBody Promotion promo) {
		
		
		if(checkPromoExists(promo)) {
			 ResponseEntity.ok(promotionDao.save(promo));
			 return new ResponseEntity<Promotion>(promo, HttpStatus.OK); 
		}
		return new ResponseEntity<Promotion>(promo, HttpStatus.BAD_REQUEST); 
	}
	
	
	//delete promotion
	@PostMapping("/deletePromo")
	public boolean deleteMovie(@RequestBody Promotion promo) {
		  promotionDao.delete(promo);
		  return true;
	}
	
	@PostMapping("/getPromoByCode") 
	 public ResponseEntity<Promotion> getPromoByCode (@RequestBody Promotion promo ) {
		  
		 
		 String promoCode = promo.getPromoCode();
		 List<Promotion> promoList = promotionDao.findByPromoCode(promoCode);
		  
		 if (!checkPromoExists(promo)) {
			  return new ResponseEntity<Promotion>(HttpStatus.NO_CONTENT); 
		  }

		 Promotion promoResponse = promoList.get(0);
		 return new ResponseEntity<Promotion>(promoResponse,
		 HttpStatus.OK);
	  
	  }
	
	 public boolean checkPromoExists(Promotion promo ) {
		 
		 String promoCode = promo.getPromoCode();
		 List<Promotion> promoList = promotionDao.findByPromoCode(promoCode);
		  
		  if (promoList.isEmpty()) { 
			  System.out.println("No promo code found");
			  return false;
		  }
		  
		  return true;
	 
	 }
	 
	 public double getDiscountByCode(String code) {
		 
		 List<Promotion> promoList = promotionDao.findByPromoCode(code);
		 return promoList.get(0).getDiscountApplied();		 
		 
	 }
	
	

}
