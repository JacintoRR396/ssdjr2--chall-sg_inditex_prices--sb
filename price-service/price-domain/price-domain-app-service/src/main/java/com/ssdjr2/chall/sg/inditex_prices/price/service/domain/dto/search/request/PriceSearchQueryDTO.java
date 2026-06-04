package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record PriceSearchQueryDTO(
		@JsonProperty("brand_id")
		@NotNull(message = "validation.price.brandId.notNull")
		@Positive(message = "validation.price.brandId.min")
		Long brandId,

		@JsonProperty("product_id")
		@NotNull(message = "validation.price.productId.notNull")
		@Positive(message = "validation.price.productId.min")
		Integer productId,

		@JsonProperty("application_date")
		@NotNull(message = "validation.price.applicationDate.notNull")
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
		LocalDateTime applicationDate) { }
