package com.ssdjr2.chall.sg.inditex_prices.domain.valueobject;

import com.ssdjr2.chall.sg.inditex_prices.domain.exception.DomainException;
import lombok.Builder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Builder(toBuilder = true)
public record Money(BigDecimal price, String currency) {

	public Money(BigDecimal price, String currency) {
		this.checkArgs(price, currency);

		this.price = Money.setScaleToPrice(price);
		this.currency = currency.toUpperCase().trim();
	}

	@Override
	public boolean equals(Object o) {
		if( this == o ) return true;
		if( o == null || getClass() != o.getClass() ) return false;
		Money money = (Money) o;

		return this.price.compareTo(money.price) == 0 && Objects.equals(currency, money.currency);
	}

	@Override
	public int hashCode() {
		return Objects.hash(price.setScale(2, RoundingMode.HALF_UP), currency);
	}

	private void checkArgs(BigDecimal price, String currency) {
		if( price == null ) {
			throw new DomainException("error.money.price_null");
		}
		if( currency == null || currency.isBlank() ) {
			throw new DomainException("error.money.currency_null");
		}

		if( price.compareTo(BigDecimal.ZERO) < 0 ) {
			throw new DomainException("error.money.negative_price");
		}
	}

	public static BigDecimal setScaleToPrice(BigDecimal price) {
		return price.setScale(2, RoundingMode.HALF_UP);
	}
}
