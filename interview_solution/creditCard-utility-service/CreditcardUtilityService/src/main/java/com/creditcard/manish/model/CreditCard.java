/**
 * 
 */
package com.creditcard.manish.model;


/**
 * @author Manish
 *
 */
public class CreditCard {

	private String cardNumber;
	private String expDate;
	public CreditCard(String cardNumber, String expDate) {
		super();
		this.cardNumber = cardNumber;
		this.expDate = expDate;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
		
}
