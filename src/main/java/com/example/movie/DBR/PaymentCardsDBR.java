package com.example.movie.DBR;

import com.example.movie.resources.model.PaymentCards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentCardsDBR extends JpaRepository<PaymentCards, Integer> {

	List<PaymentCards> findByCardID(int cardID);
	
	List<PaymentCards> findByUserID(int userID);
	
	List<PaymentCards> findByCardNumber(String cardNumber);


}
