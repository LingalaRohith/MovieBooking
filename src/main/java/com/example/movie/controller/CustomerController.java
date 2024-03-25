package com.example.movie.controller;


import com.example.movie.DBR.CustomerDBR;
import com.example.movie.DBR.PaymentCardsDBR;
import com.example.movie.resources.enums.CustomerStatus;
import com.example.movie.resources.model.CustomerBody;
import com.example.movie.resources.model.Customer;
import com.example.movie.resources.model.CustomBody;
import com.example.movie.resources.model.PaymentCards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class CustomerController {
	
	@Autowired
	private CustomerDBR customerDao;
	@Autowired
	private EmailSenderService emailSender;
	@Autowired
	private PaymentCardsDBR cardsDao;
	@Autowired
	private TextEncryptor textEncryptor;
	
	
	
	
	@PostMapping("/registerCustomer")
	public Map<Integer, Customer> register(@RequestBody CustomBody customerBody) {
		
		Map<Integer, Customer> result = new HashMap<Integer, Customer>();
		Customer customer = customerBody.getCustomer();
		if(!checkCustomerExists(customer.getEmail())) {
	
			//Sending verification code
			int max=99999;
			int min=10000;
			int code = (int) Math.floor(Math.random()*(max-min+1)+min);
			customer.setVerificationCode(code);
			String emailBody = "Please use this verification code to activate your account: " + code;
			String emailSubject = "Verify Account";
			try {
					emailSender.sendEmail(customer.getEmail(), emailBody, emailSubject);
					
			} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result.put(502, customer);
					return result;
	
			}
			
			//Encrypting the password to store in the database
			try {
				customer.setPassword(textEncryptor.encrypt(customer.getPassword()));
			} catch (Exception e) {
					e.printStackTrace();
			}
			ResponseEntity.ok(customerDao.save(customer));
			
			//Saving encrypted card details
			PaymentCards card = customerBody.getCardDetails();
			if (!card.getCardNumber().isEmpty()) {
				card.setUserID(customer.getUserID());
				cardsDao.save(encryptCard(card));
			}
			
			
			//Decrypt the password in response
			try {
					 customer.setPassword(textEncryptor.decrypt(customer.getPassword()));
			} catch (Exception e) {
					 e.printStackTrace();
			}
					 
			result.put(200, customer);
			return result; 
		}
		result.put(208, customer);
		return result; 
	}
	
	
	@PostMapping("/verifyCustomer")
	public Map<Integer, Customer> verifyCustomer(@RequestBody Customer customer) {
		
		Map<Integer, Customer> result = new HashMap<Integer, Customer>();
		//Find the customer by email
		List<Customer> list =  customerDao.findByEmail(customer.getEmail());
		
		if(customer.getVerificationCode() == list.get(0).getVerificationCode()) {
			
			//Set the customer status as active after verifying
			customer.setCustomerStatusID(CustomerStatus.Active);
			try {
				customer.setPassword(textEncryptor.encrypt(customer.getPassword()));
			} catch (Exception e) {
					e.printStackTrace();
			}
			ResponseEntity.ok(customerDao.save(customer));
			
			//Decrypt the password in the response
			try {
				  customer.setPassword(textEncryptor.decrypt(customer.getPassword()));
			} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
			result.put(200, customer);
			String emailBody = "Registration done";
			String emailSubject = "Registration done";
			try {
				emailSender.sendEmail(customer.getEmail(), emailBody, emailSubject);

			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put(502, customer);
				return result;

			}
			return result; 
		}
		result.put(400, customer);
		return result; 

	}
	
	@PostMapping("/updateCustomer")
	public ResponseEntity<Customer> update(@RequestBody Customer customer) {
		
		//Check if customer exists or not
		if(!checkCustomerExists(customer.getEmail())) {
			return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT); 
		}
		
		//Send the profile update email
		String emailBody = "Your profile has been updated successfully!!";
		String emailSubject = "Profile Updated";
		try {
				emailSender.sendEmail(customer.getEmail(), emailBody, emailSubject);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST); 

		}
		
		//Encrypt the password and update the details
		try {
				customer.setPassword(textEncryptor.encrypt(customer.getPassword()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		ResponseEntity.ok(customerDao.save(customer));
		
		
//		//Update encrypted card details
//		for(PaymentCards card: customerBody.getCardDetails()) {
//				 card.setUserID(customer.getUserID());
//				 cardsDao.save(encryptCard(card));
//				 decryptCard(card);
//		}
		
		//Decrypt the password in response
		try {
				 customer.setPassword(textEncryptor.decrypt(customer.getPassword()));
		} catch (Exception e) {
				 e.printStackTrace();
		}
				 
		
		return new ResponseEntity<Customer>(customer, HttpStatus.OK); 
	}
	
	
	@PostMapping("/forgetPassword")
	public Map<Integer, Customer> forgetPassword(@RequestBody Map<String, String> requestBody) {
		
		Map<Integer, Customer> result = new HashMap<Integer, Customer>();

		//Send the verification code email
		String email = requestBody.get("email");
		Customer customer = null;
		if(checkCustomerExists(email)) {
			     int max=99999;
			     int min=10000;
				 int code = (int) Math.floor(Math.random()*(max-min+1)+min);
				 String emailBody = "Please use this verification code to activate your account: " + code;
				 String emailSubject = "Verify Account";
				 try {
						emailSender.sendEmail(email, emailBody, emailSubject);
					
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result.put(502, customer);
					return result; 

				}
				customer = customerDao.findByEmail(email).get(0);
				customer.setVerificationCode(code);
				customerDao.save(customer);
				
				try {
					  customer.setPassword(textEncryptor.decrypt(customer.getPassword()));
				} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
				result.put(200, customer);
				return result;
				
		}
		result.put(204, customer);
		return result; 

	}

	@PostMapping("/forgetPasswordMail")
	public Map<Integer, String> forgetPasswordMail(@RequestBody Map<String, String> requestBody) {

		Map<Integer, String> result = new HashMap<Integer, String>();

		//Send the verification code email
		String email = requestBody.get("email");
		Customer customer = null;
		if(checkCustomerExists(email)) {
			String emailBody = "Please click on the following link to reset your password: http://localhost:3000/forgotPasswordMail";
			String emailSubject = "Forgot Password";
			try {
				emailSender.sendEmail(email, emailBody, emailSubject);

			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put(502, "Error in sending mail");
				return result;

			}

			result.put(200, "Mail sent Successfully");
			return result;

		}
		result.put(204, "No email found");
		return result;

	}
	
	@PostMapping("/changePassword")
	public ResponseEntity<Customer> changePassword(@RequestBody Map<String, String> requestBody) {
		
		//Set the encrypted password and send email
		String email = requestBody.get("email");
		String password = requestBody.get("password");
		
		List<Customer> customerList = customerDao.findByEmail(email);

		if(!customerList.isEmpty()) {
				 
				 Customer customer = customerList.get(0);
				 try {
					 customer.setPassword(textEncryptor.encrypt(password));
					} catch (Exception e) {
						e.printStackTrace();
					}	

				 ResponseEntity.ok(customerDao.save(customer));

				 String emailBody = "Your password has been changed";
				 String emailSubject = "Profile Updated";
				 try {
						emailSender.sendEmail(email, emailBody, emailSubject);
					
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST); 

				}
				 
		}
		return new ResponseEntity<Customer>(HttpStatus.OK); 

	}
	
	
	
	@GetMapping("/getAllcustomers")
	public List<Customer> getAllCustomers() {
		 List<Customer> list =  customerDao.findAll();
		 return list;
	}

	@PostMapping("/getcustomerx")
	public Map<Integer, CustomerBody> getCustomer(@RequestBody Map<String, String> requestBody) {
		Map<Integer, CustomerBody> resultMap = new HashMap<Integer, CustomerBody>();
		String email = requestBody.get("email");
		List<Customer> customerList = customerDao.findByEmail(email);
		if (!checkCustomerExists(email)) {
			resultMap.put(204, new CustomerBody());
			return resultMap;
		}


		//Decrypt the password in response
		Customer customerResponse = customerList.get(0);
		try {
			customerResponse.setPassword(textEncryptor.decrypt(customerResponse.getPassword()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CustomerBody result = new CustomerBody();
		result.setCustomer(customerResponse);

		// Fetch the cards and attach to the response
		List<PaymentCards> cards = cardsDao.findByUserID(customerResponse.getUserID());

		for(PaymentCards card: cards) {
			decryptCard(card);
		}

		result.setCardDetails(cards);
		resultMap.put(200, result);

		return resultMap;

	}
	
	 public boolean checkCustomerExists (String email ) {
		 
		 List<Customer> customerList = customerDao.findByEmail(email);
		  
		  if (customerList.isEmpty()) { 
			  System.out.println("No customer email found");
			  return false;
		  }
		  
		  return true;
	 
	 }
	 
	 @PostMapping("/getCustomer")
	 public Map<Integer, CustomerBody> getCustomer (@RequestBody Customer customer ) {
		 
		 Map<Integer, CustomerBody> resultMap = new HashMap<Integer, CustomerBody>();
		 //Get the customer by email and password
	     String email = customer.getEmail();
	     String password = customer.getPassword();
		 List<Customer> customerList = customerDao.findByEmail(email);
		  
		 if (!checkCustomerExists(customer.getEmail())) {
			 resultMap.put(204, new CustomerBody());
			 return resultMap; 
		 }
		 
		 
		 //Check if the password is similar or not
		 try {
			if (!textEncryptor.decrypt(customerList.get(0).getPassword()).equals(password)) { 
				  System.out.println("Incorrect Password");
				  resultMap.put(203, new CustomerBody());
				  return resultMap; 
			  }
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
		 //Decrypt the password in response
		 Customer customerResponse = customerList.get(0);
		 try {
			  customerResponse.setPassword(textEncryptor.decrypt(customerResponse.getPassword()));
		 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
		  
	     CustomerBody result = new CustomerBody();
	     result.setCustomer(customerResponse);
     
	     // Fetch the cards and attach to the response
	     List<PaymentCards> cards = cardsDao.findByUserID(customerResponse.getUserID());
		
		 for(PaymentCards card: cards) {
			  decryptCard(card);
		 }
		 
		 result.setCardDetails(cards);		
		 resultMap.put(200, result);
		  
		 return resultMap;
	  
	  }
	 
	 @PostMapping("/getCustomerById")
	 public ResponseEntity<CustomerBody> getCustomerById (@RequestBody Customer customer ) {
		  
	     int id = customer.getUserID();
	      
		 List<Customer> customerList = customerDao.findByUserID(id);
		 
		  
		 if (customerList.isEmpty()) {
			  return new ResponseEntity<CustomerBody>(HttpStatus.NO_CONTENT); 
		 }
		 
		 
		 //Decrypt the password in response
		 Customer customerResponse = customerList.get(0);
		 try {
			  customerResponse.setPassword(textEncryptor.decrypt(customerResponse.getPassword()));
		 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
		  
	     CustomerBody result = new CustomerBody();
	     result.setCustomer(customerResponse);
	     // Fetch the cards and attach to the response
	     List<PaymentCards> cards = cardsDao.findByUserID(customerResponse.getUserID());
		
		 for(PaymentCards card: cards) {
			  decryptCard(card);
		 }
		 
		 result.setCardDetails(cards);		    
		  
		 return new ResponseEntity<CustomerBody>(result,
		 HttpStatus.OK);
	  
	  }
	 
	 @PostMapping("/suspendCustomer")
	 public Map<Integer, Customer> suspendCustomer (@RequestBody Customer customer ) {
		 customer = customerDao.findByUserID(customer.getUserID()).get(0);
		 Map<Integer, Customer> map = new HashMap<Integer, Customer>();
		 customer.setCustomerStatusID(CustomerStatus.InActive);
		 String emailSubject = "Notice of Account Suspension";
		 String emailBody = "Dear Customer, \n"
		 		+ "We regret to inform you that your account has been temporarily suspended due to violation of terms and services.\n"
		 		+ "\n"
		 		+ "If you believe that this suspension is some error, please reach out to us at popcornpicks@gmail.com\n"
		 		+ "\n"
		 		+ "Regards"
		 		+ "Team PopcornPicks";
		 sendEmail(customer, emailBody, emailSubject);
		 customerDao.save(customer);
		 map.put(400, customer);
		 return map;
		
	 }
	 
	 @PostMapping("/activateCustomer")
	 public Map<Integer, Customer> activateCustomer (@RequestBody Customer customer ) {
		 customer = customerDao.findByUserID(customer.getUserID()).get(0);
		 Map<Integer, Customer> map = new HashMap<Integer, Customer>();
		 customer.setCustomerStatusID(CustomerStatus.Active);
		 String emailSubject = "Notice of Account Re-Activation";
		 String emailBody = "Dear Customer, \n"
		 		+ "We are delighted to inform you that your account has been re-activated. You can now enjoy the features and services provided by us.\n"
		 		+ "\n"
		 		+ "If you still run into any issues, please reach out to us at popcornpicks@gmail.com\n"
		 		+ "\n"
		 		+ "Regards"
		 		+ "Team PopcornPicks";
		 sendEmail(customer, emailBody, emailSubject);
		 customerDao.save(customer);
		 map.put(400, customer);
		 return map;
		
	 }
	 

	  @PostMapping("/deleteCustomer")
	  public boolean deleteCustomer(@RequestBody Customer customer) {
		  customer = customerDao.findByUserID(customer.getUserID()).get(0);
		  List<PaymentCards> cards = cardsDao.findByUserID(customer.getUserID());
		  for (PaymentCards card : cards) {
			  cardsDao.delete(card);
		  }
		  customerDao.delete(customer);

		 
		  return true;
	  }
	  
	  
	  
	  
	  @PostMapping("/addCard")
	  public Map<Integer, PaymentCards> addCard(@RequestBody PaymentCards paymentCard) {

		  Map<Integer, PaymentCards> result = new HashMap<Integer, PaymentCards>();
		  //Save the card details
		  if(checkCardAvailaibility(paymentCard)) {
			if(!checkCardExists(paymentCard)) {
				ResponseEntity.ok(cardsDao.save(encryptCard(paymentCard)));
				decryptCard(paymentCard);
				result.put(200, paymentCard);
				return result; 
			}
			result.put(208, paymentCard);
			return result;
		  }
		  else {
			  result.put(509, paymentCard);
			  return result;
		  }
		}
	  

	  @PostMapping("/updateCard")
	  public ResponseEntity<PaymentCards> updateCard(@RequestBody PaymentCards paymentCard) {

		  
		  //Save the card details
		  if(checkCardExists(paymentCard)) {
				ResponseEntity.ok(cardsDao.save(encryptCard(paymentCard)));
				decryptCard(paymentCard);
				return new ResponseEntity<PaymentCards>(paymentCard, HttpStatus.OK); 
			}
			return new ResponseEntity<PaymentCards>(paymentCard, HttpStatus.NO_CONTENT);
	}
	  
	  public boolean checkCardExists (PaymentCards card) {
			
			  String cardNumber = card.getCardNumber();
			  List<PaymentCards> cardList = cardsDao.findByUserID(card.getUserID());;
			 
			  for(PaymentCards cardDetails: cardList) {
					  decryptCard(cardDetails);
					  if(cardDetails.getCardNumber().equals(cardNumber)) { 
						  return true;
					  }
			  }
			  
			  return false;
		 
		 }
	  
	  public boolean checkCardAvailaibility( PaymentCards card) {
			 
		  int userID = card.getUserID();
		  List<PaymentCards> cardList = cardsDao.findByUserID(userID);
		  
		  if (cardList.size() < 3) { 
			  return true;
		  }
		  
		  
		  return false;
	 
	  }
	  
	  @PostMapping("/deleteCard")
	  public boolean deleteCard(@RequestBody PaymentCards card) {
		  if(!checkCardExists(card)) {
			  return false;
		  }
		  cardsDao.delete(card);
		  return true;
	  }
	  
	  
	  
	  public PaymentCards encryptCard(PaymentCards card) {
		  
		  try {
			card.setCardNumber(textEncryptor.encrypt(card.getCardNumber()));
			card.setExpiryDate(textEncryptor.encrypt(card.getExpiryDate()));
			card.setNameOnCard(textEncryptor.encrypt(card.getNameOnCard()));
			card.setSecurityNumber(textEncryptor.encrypt(card.getSecurityNumber()));
			card.setCardType(textEncryptor.encrypt(card.getCardType()));
			card.setStreet(textEncryptor.encrypt(card.getStreet()));
			card.setCity(textEncryptor.encrypt(card.getCity()));
			card.setState(textEncryptor.encrypt(card.getState()));
			card.setZipcode(textEncryptor.encrypt(card.getZipcode()));


		  } catch (Exception e) {
			e.printStackTrace();
		  }
		  return card;
		  
		  
	  }
	  
	  public PaymentCards decryptCard(PaymentCards card) {
		  
		  try {
			card.setCardNumber(textEncryptor.decrypt(card.getCardNumber()));
			card.setExpiryDate(textEncryptor.decrypt(card.getExpiryDate()));
			card.setNameOnCard(textEncryptor.decrypt(card.getNameOnCard()));
			card.setSecurityNumber(textEncryptor.decrypt(card.getSecurityNumber()));
			card.setCardType(textEncryptor.decrypt(card.getCardType()));
			card.setStreet(textEncryptor.decrypt(card.getStreet()));
			card.setCity(textEncryptor.decrypt(card.getCity()));
			card.setState(textEncryptor.decrypt(card.getState()));
			card.setZipcode(textEncryptor.decrypt(card.getZipcode()));


		  } catch (Exception e) {
			e.printStackTrace();
		  }
		  return card;
		  
		  
	  }
	  
	  public List<Customer> getSubscribedCustomers() {
			 List<Customer> list =  customerDao.findBypromotionsSubscribed(true);
			 return list;
	  }
	  
	  public void sendEmail(Customer cust, String emailBody, String emailSubject) {
		  
		  Customer customer = customerDao.findByUserID(cust.getUserID()).get(0);
		  try {
			  emailSender.sendEmail(customer.getEmail(), emailBody, emailSubject);
		  } catch (MessagingException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  }
	  }
	  
	  @PostMapping("/getCardById")
	  public ResponseEntity<PaymentCards> getCardById(@RequestBody PaymentCards card ) {
			  
		      int id = card.getCardID();
			  List<PaymentCards> cardList = cardsDao.findByUserID(card.getUserID());
			  
			  if (cardList.isEmpty()) { 
				  return new ResponseEntity<PaymentCards>(HttpStatus.NO_CONTENT); 
			  }
			  		  
			  PaymentCards cardResponse = decryptCard(cardList.get(0));
			  return new ResponseEntity<PaymentCards>(cardResponse,
			  HttpStatus.OK);
	  }  
		
	  
	 

}
