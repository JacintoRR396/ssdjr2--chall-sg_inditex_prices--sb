package com.ssdjr2.chall.sg.inditex_prices.domain.model;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Money {
	private BigDecimal price;
	private String currency;
}
