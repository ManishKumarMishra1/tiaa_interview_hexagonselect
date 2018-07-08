package com.creditcard.manish.resource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.creditcard.manish.model.CreditCard;
import com.creditcard.manish.service.CardNumberGeneratorService;

@Component
public class CreditCardEngineHelper {

	@Resource
	private CardNumberGeneratorService cardNumberGeneratorService;
	
	private static Map<String,String> cardTypeBin = new HashMap<>();
	static {
		cardTypeBin.put("VISA", "4");
		cardTypeBin.put("MASTERCARD", "5");
		cardTypeBin.put("AMEX", "37");
		cardTypeBin.put("DISCOVER", "6");
	}
	
	public List<String> generateCards(String cardType,int totalNoCards){
		List<String> creditcardNumbers = new ArrayList<>();
				
		for(int i=0;i<totalNoCards;i++) {			
			creditcardNumbers.add(cardNumberGeneratorService.generateCreditCard(cardTypeBin.get(cardType.toUpperCase())));
		}
		return creditcardNumbers;
	}
	
	/**
	 * Validate generated credit card number and id valid return card number and today exp date.
	 * @param cardNumbers
	 * @return List<CreditCard>
	 */
	public List<CreditCard> validateCardNumbers(List<String> cardNumbers){
		List<CreditCard> creditcards = new ArrayList<>();
		LocalDate today = LocalDate.now();
		String cardExpDate = today.format(DateTimeFormatter.ofPattern("MMYY"));
		for(String number:cardNumbers) { 
			if(cardNumberGeneratorService.isValidCreditCardNumber(number)) {
				CreditCard crdCard = new CreditCard(number,cardExpDate);
				creditcards.add(crdCard);
			}
		}
		return creditcards;
	}
}
