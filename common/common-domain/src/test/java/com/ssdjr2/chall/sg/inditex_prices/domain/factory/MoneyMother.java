package com.ssdjr2.chall.sg.inditex_prices.domain.factory;

import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.Money;

import java.math.BigDecimal;

public class MoneyMother {

	public static final double TARIFF1_PRICE = 35.50;
	public static final double TARIFF1_PRICE_ROUNDING = 35.5000;
	public static final double TARIFF_NEGATIVE = -1;
	public static final String CURRENCY = "EUR";
	public static final String CURRENCY_NOT_FORMATTED = " eur  ";

	public MoneyMother()	{
		throw new UnsupportedOperationException();
	}

	public static void createMoneyWithPriceNull() {
		new Money(null, CURRENCY);
	}

	public static void createMoneyWithCurrencyNull() {
		new Money(BigDecimal.valueOf(TARIFF1_PRICE), null);
	}

	public static void createMoneyWithPriceNegative() {
		new Money(BigDecimal.valueOf(TARIFF_NEGATIVE), CURRENCY);
	}
}
