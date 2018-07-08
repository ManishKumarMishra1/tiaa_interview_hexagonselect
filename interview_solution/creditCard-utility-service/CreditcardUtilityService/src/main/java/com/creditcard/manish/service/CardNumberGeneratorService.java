/**
 * 
 */
package com.creditcard.manish.service;

/**
 * @author DELL
 *
 */
public interface CardNumberGeneratorService {

	String generateCreditCard(String bin);
	boolean isValidCreditCardNumber(String creditCardNumber);
}
