package com.example.movie.resources.model;


import com.example.movie.resources.enums.CustomerStatus;
import com.example.movie.resources.enums.UserRole;
import jakarta.persistence.*;

@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userID;
	private String email;
	private String password;
	@Enumerated(EnumType.ORDINAL)
	private UserRole userRole;
	private String firstName;
	private String lastName;
	@Column(length = 15)
	private String phoneNumber;
	private String street;
	private String city;
	private String state;
	private int zipcode;
	@Enumerated(EnumType.ORDINAL)
	private CustomerStatus customerStatusID;
	private int verificationCode;
	private boolean promotionsSubscribed;

	public Customer() {
	}

	public Customer(int userID, String email, String password, UserRole userRole, String firstName, String lastName,
                    String phoneNumber, CustomerStatus customerStatusID, int verificationCode, boolean promotionsSubscribed, String street, String city, String state, int zipcode) {
		super();
		this.userID = userID;
		this.email = email;
		this.password = password;
		this.userRole = userRole;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.customerStatusID = customerStatusID;
		this.verificationCode = verificationCode;
//		this.promotionsSubscribed = promotionsSubscribed;
		this.promotionsSubscribed = true;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public CustomerStatus getCustomerStatusID() {
		return customerStatusID;
	}

	public void setCustomerStatusID(CustomerStatus customerStatusID) {
		this.customerStatusID = customerStatusID;
	}

	public int getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(int verificationCode) {
		this.verificationCode = verificationCode;
	}

	public boolean isPromotionsSubscribed() {
		return promotionsSubscribed;
	}

	public void setPromotionsSubscribed(boolean promotionsSubscribed) {
//		this.promotionsSubscribed = promotionsSubscribed;
		this.promotionsSubscribed = true;
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


	public int getZipcode() {
		return zipcode;
	}


	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	
	
}
