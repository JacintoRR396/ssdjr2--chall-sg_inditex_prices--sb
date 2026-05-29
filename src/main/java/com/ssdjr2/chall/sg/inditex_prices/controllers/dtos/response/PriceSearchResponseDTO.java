package com.ssdjr2.chall.sg.inditex_prices.controllers.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PriceSearchResponseDTO(
		@JsonProperty("brand_id")
		Integer brandId,

		@JsonProperty("product_id")
		Integer productId,

		@JsonProperty("price_list")
		Integer priceList,

		@JsonProperty("application_dates")
    ApplicationDatesResponseDTO applicationDates,

		MoneyResponseDTO money) { }
