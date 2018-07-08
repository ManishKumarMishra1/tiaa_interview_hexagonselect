package com.creditcard.manish.resource;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.creditcard.manish.model.CreditCard;
import com.creditcard.manish.resource.CreditCardEngine;
import com.creditcard.manish.resource.CreditCardEngineHelper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CreditCardEngine.class, secure = false)
public class CreditCardEngineTest {

	@Autowired 
	private MockMvc mockMvc;
	
	@MockBean
	private CreditCardEngineHelper creditCardEngineHelper; 
	
	@Test
	public void testGenerateCreditCard() throws Exception {
		List<String> mockCardNumbers = new ArrayList<>();
		mockCardNumbers.add("4545453447978670");
		Mockito.when(creditCardEngineHelper.generateCards(Mockito.anyString(),Mockito.anyInt())).thenReturn(mockCardNumbers);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/CCengine/visa/1").accept(
				MediaType.APPLICATION_JSON);
				//
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testValidateCardNumber() throws Exception{
		
		String sampleInputJson = "[ \"4545453447978670\"]";
		List<CreditCard> mockCreditCard = new ArrayList<>();
		CreditCard crCard = new CreditCard("4545453447978670", "0718");
		mockCreditCard.add(crCard);
		Mockito.when(creditCardEngineHelper.validateCardNumbers(Mockito.anyList())).thenReturn(mockCreditCard);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/CCengine/validateCards").accept(
				MediaType.APPLICATION_JSON).content(sampleInputJson)
				.contentType(MediaType.APPLICATION_JSON);
				//
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

}
