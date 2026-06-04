package com.ssdjr2.chall.sg.inditex_prices.price.service.application.factory;

import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.Money;

import java.math.BigDecimal;

public class MoneyMother {

	public static final double TARIFF1_PRICE = 35.50;
	public static final double TARIFF2_PRICE = 25.45;
	public static final double TARIFF3_PRICE = 30.50;
	public static final double TARIFF4_PRICE = 38.95;
	public static final String CURRENCY = "EUR";

	public MoneyMother()	{
		throw new UnsupportedOperationException();
	}

	public static Money createMoneyTariff1() {
		return new Money( BigDecimal.valueOf( TARIFF1_PRICE ), CURRENCY);
	}
}
