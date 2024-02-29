package com.example.movie.resources.model;

public class CustomBody {
	Customer customer;
	PaymentCards cardDetails;
	
	public CustomBody(Customer customer, PaymentCards cardDetails) {
		super();
		this.customer = customer;
		this.cardDetails = cardDetails;
	}
	
	public CustomBody() {
		super();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public PaymentCards getCardDetails() {
		return cardDetails;
	}

	public void setCardDetails(PaymentCards cardDetails) {
		this.cardDetails = cardDetails;
	}


	
	
	
	
}