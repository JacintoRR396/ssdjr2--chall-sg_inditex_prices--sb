package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PriceSearchResponseDTO(
		@JsonProperty("brand_id")
		Long brandId,

		@JsonProperty("product_id")
		Integer productId,

		@JsonProperty("price_list")
		Integer priceList,

		@JsonProperty("application_dates")
    ApplicationDatesResponseDTO applicationDates,

		MoneyResponseDTO money) { }
