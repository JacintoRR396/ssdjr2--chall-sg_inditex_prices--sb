package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record ApplicationDatesResponseDTO(
		@JsonProperty("start_date")
		LocalDateTime startDate,

		@JsonProperty("end_date")
		LocalDateTime endDate) { }
