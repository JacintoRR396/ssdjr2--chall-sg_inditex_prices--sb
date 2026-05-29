package com.ssdjr2.chall.sg.inditex_prices.controllers.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PriceSearchQueryDTO(
		@JsonProperty("brand_id")
		@NotNull(message = "{validation.price.brandId.notNull}")
		@Min(value = 1, message = "{validation.price.brandId.min}")
		Integer brandId,

		@JsonProperty("product_id")
		@NotNull(message = "{validation.price.productId.notNull}")
		@Min(value = 1, message = "{validation.price.productId.min}")
		Integer productId,

		@JsonProperty("application_date")
		@NotNull(message = "{validation.price.applicationDate.notNull}")
		LocalDateTime applicationDate) { }
