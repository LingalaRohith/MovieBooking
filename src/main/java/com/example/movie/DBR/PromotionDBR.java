package com.example.movie.DBR;

import com.example.movie.resources.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionDBR extends JpaRepository<Promotion, Integer> {
	

	List<Promotion> findByPromoId(int promoId);
	
	List<Promotion> findByPromoCode(String promocode);
	
	
}
