package com.creditcard.manish.resource;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creditcard.manish.model.CreditCard;

@RestController
@RequestMapping("/CCengine")
public class CreditCardEngine {
	
	@Resource
	private CreditCardEngineHelper creditCardEngineHelper; 
	

	@PostMapping("/{cardType}/{totalNoCards}")
public List<String> generateCreditCard(@PathVariable("cardType") final String cardType,@PathVariable("totalNoCards") final int totalNoCards){
		
		return creditCardEngineHelper.generateCards(cardType,totalNoCards);
	}
	
	@PostMapping("/validateCards")
	public List<CreditCard> validateCardNumber(@RequestBody List<String> cardNumbers){
		
		return creditCardEngineHelper.validateCardNumbers(cardNumbers);		
	}

}
