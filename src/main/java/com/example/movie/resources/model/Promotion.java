package com.example.movie.resources.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="promotion")
public class Promotion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int promoId;
	private String promoCode;
	private String promoDescription;
	private Date startDate;
	private Date endDate;
	private double discountApplied;
	

	public Promotion() {
		super();
	}

	public Promotion(int promoId, String promoCode, String promoDescription, Date startDate, Date endDate,
                     double discountApplied) {
		super();
		this.promoId = promoId;
		this.promoCode = promoCode;
		this.promoDescription = promoDescription;
		this.startDate = startDate;
		this.endDate = endDate;
		this.discountApplied = discountApplied;
	}

	public int getPromoId() {
		return promoId;
	}

	public void setPromoId(int promoId) {
		this.promoId = promoId;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public String getPromoDescription() {
		return promoDescription;
	}

	public void setPromoDescription(String promoDescription) {
		this.promoDescription = promoDescription;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getDiscountApplied() {
		return discountApplied;
	}

	public void setDiscountApplied(double discountApplied) {
		this.discountApplied = discountApplied;
	}

	@Override
	public String toString() {
		return String.format( "We are thrilled to share some exciting news with you – a special promotion that we believe you won't want to miss! \n\n"
                + "Exclusive Promotion Details:\n"
                + "%s\n\n"
                + "Promotion Period: %s to %s\n\n"
                + "How to Redeem: Use code %s while checkout to avail %.2f%% discount\n\n"
                + "Thank you for being a valued customer.We hope this promotion adds a little extra joy to your day.\n\n"
                + "Should you have any questions or need assistance, don't hesitate to reach out to us at popcornpicks.support@gmail.com.\n\n"
                + "Happy shopping!!\n\n"
                + "Best regards,\n"
                + "Team PopcornPicks\n",
          promoDescription, startDate, endDate, promoCode, discountApplied);
	}
	
	
	

}
