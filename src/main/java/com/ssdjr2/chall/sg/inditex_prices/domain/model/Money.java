package com.ssdjr2.chall.sg.inditex_prices.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Money {
	private BigDecimal price;
	private String currency;

	public Money(BigDecimal price, String currency) {
		this.price = Money.setScaleToPrice( price );
		this.currency = currency;
	}

	public static BigDecimal setScaleToPrice( BigDecimal price ) {
		return Objects.nonNull(price) ? price.setScale(2, RoundingMode.HALF_UP) : null;
	}
}
