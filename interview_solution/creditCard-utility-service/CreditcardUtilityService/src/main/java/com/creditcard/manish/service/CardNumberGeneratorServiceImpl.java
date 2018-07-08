/**
 * 
 */
package com.creditcard.manish.service;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Manish
 *
 */
@Service
public class CardNumberGeneratorServiceImpl implements CardNumberGeneratorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CardNumberGeneratorServiceImpl.class);
	private int cardLength=16;
	
	private Random random = new Random(System.currentTimeMillis());
	
	/**
	 * @param bin The bank identification number, a set digits at the start of the credit card
     *            number, used to identify the bank that is issuing the credit card.
     * 
     * @return    A randomly generated, valid, credit card number.
	 */
	@Override
	public String generateCreditCard(String bin) {

        // The number of random digits that we need to generate is equal to the
        // total length of the card number minus the start digits given by the
        // user, minus the check digit at the end.
        int randomNumberLength = cardLength - (bin.length() + 1);

        StringBuilder builder = new StringBuilder(bin);
        for (int i = 0; i < randomNumberLength; i++) {
            int digit = this.random.nextInt(10);
            builder.append(digit);
        }

        // Do the Luhn algorithm to generate the check digit.
        int checkDigit = this.getCheckDigit(builder.toString());
        builder.append(checkDigit);

        return builder.toString();
		
	}

	/**
     * Generates the check digit required to make the given credit card number
     * valid (i.e. pass the Luhn check)
     *
     * @param number   The credit card number for which to generate the check digit.
     * @return 		   The check digit required to make the given credit card number valid.
     */
    private int getCheckDigit(String number) {

        
        // The digits we need to replace will be those in an even position for
        // card numbers whose length is an even number, or those is an odd
        // position for card numbers whose length is an odd number. This is
        // because the Luhn algorithm reverses the card number, and doubles
        // every other number starting from the second number from the last
        // position.
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {

            // Get the digit at the current position.
            int digit = Integer.parseInt(number.substring(i, (i + 1)));
            // Double every second digit from right to left. If doubling of a digit results in a two-digit number, 
            // add up the two digits to get a single-digit number 
            if ((i % 2) == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }

        // The check digit is the number required to make the sum a multiple of
        // 10.
        int mod = sum % 10;
        return ((mod == 0) ? 0 : 10 - mod);
    }
    
    /**
     * Luhn check or Mod 10 validation is used to validate credit card number.Below are the steps.
     * 1) Double every second digit from right to left. If doubling of a digit results in a two-digit number, 
     *    add up the two digits to get a single-digit number.
     * 2) Now add all single-digit numbers from Step 1.
     * 3) Add all digits in the odd places from right to left in the card number.
     * 4) Sum the results from Step 2 and Step 3.
     * 5) If the result from Step 4 is divisible by 10, the card number is valid; otherwise, it is invalid.
     */
	@Override
	public boolean isValidCreditCardNumber(String creditCardNumber) {
		boolean isValid = false;

		try {
			String reversedNumber = new StringBuffer(creditCardNumber)
					.reverse().toString();
			int mod10Count = 0;
			for (int i = 0; i < reversedNumber.length(); i++) {
				int augend = Integer.parseInt(String.valueOf(reversedNumber
						.charAt(i)));
				if (((i + 1) % 2) == 0) {
					String productString = String.valueOf(augend * 2);
					augend = 0;
					for (int j = 0; j < productString.length(); j++) {
						augend += Integer.parseInt(String.valueOf(productString
								.charAt(j)));
					}
				}

				mod10Count += augend;
			}

			if ((mod10Count % 10) == 0) {
				isValid = true;
			}
		} catch (NumberFormatException e) {
			LOGGER.error("Number format exception while parsing Card number",e);
		}

		return isValid;
	}

}
