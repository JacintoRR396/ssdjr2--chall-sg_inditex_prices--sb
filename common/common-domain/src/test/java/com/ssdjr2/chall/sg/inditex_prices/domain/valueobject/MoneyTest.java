package com.ssdjr2.chall.sg.inditex_prices.domain.valueobject;

import com.ssdjr2.chall.sg.inditex_prices.domain.exception.DomainException;
import com.ssdjr2.chall.sg.inditex_prices.domain.factory.MoneyMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoneyTest {

	@Test
	@DisplayName("GIVEN valid price and currency WHEN creating Money THEN data is normalized and stored")
	void givenValidData_whenCreatingMoney_thenSuccessAndNormalized() {
		BigDecimal rawPrice = BigDecimal.valueOf( MoneyMother.TARIFF1_PRICE_ROUNDING );
		String rawCurrency = MoneyMother.CURRENCY_NOT_FORMATTED;

		Money money = new Money(rawPrice, rawCurrency);

		assertEquals( Money.setScaleToPrice( BigDecimal.valueOf( MoneyMother.TARIFF1_PRICE ) ), money.price() );
		assertEquals( MoneyMother.CURRENCY, money.currency()) ;
	}

	@Test
	@DisplayName("GIVEN null price WHEN creating Money THEN throw DomainException")
	void givenNullPrice_whenCreatingMoney_thenThrowException() {
		assertThrows(DomainException.class, MoneyMother::createMoneyWithPriceNull,
				"error.money.price_null");
	}

	@Test
	@DisplayName("GIVEN null currency WHEN creating Money THEN throw DomainException")
	void givenNullCurrency_whenCreatingMoney_thenThrowException() {
		assertThrows(DomainException.class,	MoneyMother::createMoneyWithCurrencyNull,
				"error.money.currency_null");
	}

	@Test
	@DisplayName("GIVEN negative price WHEN creating Money THEN throw DomainException")
	void givenNegativePrice_whenCreatingMoney_thenThrowException() {
		assertThrows(DomainException.class,	MoneyMother::createMoneyWithPriceNegative,
				"error.money.negative_price");
	}
}
