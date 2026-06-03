package com.ssdjr2.chall.sg.inditex_prices.controller.acceptance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssdjr2.chall.sg.inditex_prices.controller.dto.request.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.domain.model.Money;
import com.ssdjr2.chall.sg.inditex_prices.factory.PriceSearchQueryDTOMother;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PriceSteps {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private PriceSearchQueryDTO requestBuilder;
	private ResultActions responseActions;

	@Given("a query is made with brand {long} and product {int}")
	public void aQueryIsMadeWithBrandAndProduct(Long brandId, Integer productId) {
		this.requestBuilder = new PriceSearchQueryDTO(brandId, productId, LocalDateTime.now());
	}

	@When("the client requests the price rate for the date {string}")
	public void theClientRequestsThePriceRateForTheDate(String applicationDateStr) throws Exception {
		LocalDateTime date = LocalDateTime.parse(applicationDateStr);

		PriceSearchQueryDTO finalRequest = new PriceSearchQueryDTO(
				requestBuilder.brandId(),
				requestBuilder.productId(),
				date
		);

		this.responseActions = mockMvc.perform(
				MockMvcRequestBuilders.post("/prices/search")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(finalRequest))
		);
	}

	@Then("the response should be successful with status {int}")
	public void theResponseShouldBeSuccessfulWithStatus(int statusCode) throws Exception {
		this.responseActions.andExpect(status().is(statusCode));
	}

	@And("^the returned price rate should have the list identifier (\\d+) and a price of ([0-9.]+)$")
	public void theReturnedPriceRateShouldHaveTheListIdentifierAndAPriceOf(int priceList, String price) throws Exception {
		BigDecimal basePrice = new BigDecimal( price );
		BigDecimal basePriceWithScale = Money.setScaleToPrice( basePrice );
		Double finalPrice = basePriceWithScale.doubleValue();

		this.responseActions
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price_list").value(priceList))
				.andExpect(jsonPath("$.money.price").value(Matchers.is(finalPrice)));
	}

	@Given("a query is sent with empty or null data")
	public void aQueryIsSentWithEmptyOrNullData() {
		this.requestBuilder = PriceSearchQueryDTOMother.createPriceSearchQueryDTOWhenDataIsNull();
	}

	@Given("a query is sent with invalid or negative data")
	public void aQueryIsSentWithInvalidOrNegativeData() {
		this.requestBuilder = PriceSearchQueryDTOMother.createPriceSearchQueryDTOWhenDataIsInvalid();
	}

	@When("the price rate search is processed")
	public void thePriceRateSearchIsProcessed() throws Exception {
		this.responseActions = mockMvc.perform(
				MockMvcRequestBuilders.post("/prices/search")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(this.requestBuilder))
		);
	}

	@Then("the response should fail with an error status {int}")
	public void theResponseShouldFailWithAnErrorStatus(int statusCode) throws Exception {
		this.responseActions.andExpect(status().is(statusCode));
	}
}