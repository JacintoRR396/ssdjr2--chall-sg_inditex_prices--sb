package com.ssdjr2.chall.sg.inditex_prices.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class ApplicationDates {
	private LocalDateTime startDate;
	private LocalDateTime endDate;
}
