package com.example.movie.resources.model;

import java.util.List;

public class CustomerBody {
	
	Customer customer;
	List<PaymentCards> cardDetails;
	
	public CustomerBody(Customer customer, List<PaymentCards> cardDetails) {
		super();
		this.customer = customer;
		this.cardDetails = cardDetails;
	}
	
	public CustomerBody() {
		super();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<PaymentCards> getCardDetails() {
		return cardDetails;
	}

	public void setCardDetails(List<PaymentCards> cardDetails) {
		this.cardDetails = cardDetails;
	}


	
	
	
	
}