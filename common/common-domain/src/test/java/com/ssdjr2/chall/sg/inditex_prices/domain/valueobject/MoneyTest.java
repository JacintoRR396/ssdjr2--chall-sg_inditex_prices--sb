package com.ssdjr2.chall.sg.inditex_prices.domain.valueobject;

import com.ssdjr2.chall.sg.inditex_prices.domain.exception.DomainException;
import com.ssdjr2.chall.sg.inditex_prices.domain.factory.MoneyMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

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

	@Test
	@DisplayName("GIVEN two Money objects with same values WHEN comparing THEN they are equal")
	void givenSameValues_whenComparing_thenAreEqual() {
		Money money1 = MoneyMother.createMoneyTariff1();
		Money money2 = MoneyMother.createMoneyTariff1();

		assertEquals(money1, money2);
		assertEquals(money1.hashCode(), money2.hashCode());
	}

	@Test
	@DisplayName("GIVEN two Money objects with different values WHEN comparing THEN they are not equal")
	void givenDifferentValues_whenComparing_thenAreNotEqual() {
		Money money1 = MoneyMother.createMoneyTariff1();
		Money money2 = MoneyMother.createMoneyTariff2();

		assertNotEquals(money1, money2);
		assertNotEquals(money1.hashCode(), money2.hashCode());
	}

	@Test
	@DisplayName("GIVEN Money with different scale WHEN comparing THEN they are considered equal")
	void givenDifferentScale_whenComparing_thenAreEqual() {
		Money money1 = MoneyMother.createMoneyTariff1();
		Money money2 = MoneyMother.createMoneyTariff1Rounding();

		assertEquals(money1, money2);
		assertEquals(money1.hashCode(), money2.hashCode());
	}

	@Test
	@DisplayName("GIVEN null or different class WHEN comparing THEN return false")
	void givenDifferentTypes_whenComparing_thenReturnFalse() {
		ApplicationDates dates = new ApplicationDates(
				LocalDateTime.now(), LocalDateTime.now().plusHours(1));

		assertNotEquals(null, dates, "Should not be equal to null");
		assertNotEquals("A String", dates, "Should not be equal to a different class");
	}
}
