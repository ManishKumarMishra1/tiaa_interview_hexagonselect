package com.creditcard.manish.resource;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.creditcard.manish.model.CreditCard;
import com.creditcard.manish.service.CardNumberGeneratorService;


@RunWith(SpringRunner.class)
public class CreditCardEngineHelperTest {

	@InjectMocks
	CreditCardEngineHelper creditCardEngineHelper;
	
	@Mock
	CardNumberGeneratorService cardNumberGeneratorService;
	
	@Test
	public void testGenerateCards() {
		
		Mockito.when(cardNumberGeneratorService.generateCreditCard(Mockito.anyString())).thenReturn(Mockito.anyString());
		List<String> cardNumber = creditCardEngineHelper.generateCards("visa", 1);
		Assert.assertNotNull(cardNumber);
	}

	@Test
	public void testValidateCardNumbers() {
		List<String> inputCards= new ArrayList<>();
		inputCards.add("4545453447978670");
		Mockito.when(cardNumberGeneratorService.isValidCreditCardNumber(Mockito.anyString())).thenReturn(true);
		List<CreditCard> cardNumber = creditCardEngineHelper.validateCardNumbers(inputCards);
		Assert.assertNotNull(cardNumber);
	}

}
