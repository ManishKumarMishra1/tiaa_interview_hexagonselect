package com.creditcard.service;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.creditcard.manish.service.CardNumberGeneratorServiceImpl;

@RunWith(SpringRunner.class)
public class CardNumberGeneratorServiceTest {

	@InjectMocks
	CardNumberGeneratorServiceImpl cardNumberGeneratorService;
	
	@Test
	public void testGenerateCreditCard() {
		String cardNumber = cardNumberGeneratorService.generateCreditCard("4");
		Assert.assertNotNull(cardNumber);
	}

	@Test
	public void testIsValidCreditCardNumberSuccess() {
		boolean isValid = cardNumberGeneratorService.isValidCreditCardNumber("4267603134620609");
		Assert.assertEquals(true, isValid);
	}

	@Test
	public void testIsValidCreditCardNumberFail() {
		boolean isValid = cardNumberGeneratorService.isValidCreditCardNumber("4267603563563206");
		Assert.assertEquals(false, isValid);
	}
	
	@Test
	public void testIsValidCreditCardNumberException() {
		boolean isValid = cardNumberGeneratorService.isValidCreditCardNumber("42676035635ab206");
		Assert.assertEquals(false, isValid);
	}
	
}
