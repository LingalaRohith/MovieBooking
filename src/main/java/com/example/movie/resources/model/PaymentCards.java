package com.example.movie.resources.model;


import jakarta.persistence.*;

@Entity
@Table(name="payment_cards")
public class PaymentCards {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cardID;
	private int userID;
	private String cardType;
	private String cardNumber;
	private String expiryDate; 
	private String securityNumber;
	private String nameOnCard;
	private String street;
	private String city;
	private String state;
	private String zipcode;
	
	public PaymentCards(int cardID, int userID, String cardType, String cardNumber, String expiryDate,
                        String securityNumber, String nameOnCard, String street, String city, String state, String zipcode ) {
		super();
		this.cardID = cardID;
		this.userID = userID;
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.securityNumber = securityNumber;
		this.nameOnCard = nameOnCard;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}
	
	public PaymentCards() {
		super();
	}
	public int getCardID() {
		return cardID;
	}
	public void setCardID(int cardID) {
		this.cardID = cardID;
	}

	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getSecurityNumber() {
		return securityNumber;
	}
	public void setSecurityNumber(String securityNumber) {
		this.securityNumber = securityNumber;
	}
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	
}
