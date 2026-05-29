package com.ssdjr2.chall.sg.inditex_prices.controllers.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PriceQueryDTO(
		@NotNull(message = "{validation.price.brandId.notNull}") @Min(value = 1, message = "{validation.price.brandId.min}") Integer brandId,
		@NotNull(message = "{validation.price.productId.notNull}") @Min(value = 1, message = "{validation.price.productId.min}") Integer productId,
		@NotNull(message = "{validation.price.applicationDate.notNull}") LocalDateTime applicationDate) { }
