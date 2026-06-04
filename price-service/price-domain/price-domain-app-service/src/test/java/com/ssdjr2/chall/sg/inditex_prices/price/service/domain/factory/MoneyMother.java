package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.factory;

import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.Money;

import java.math.BigDecimal;

public class MoneyMother {

	public static final double TARIFF1_PRICE = 35.50;
	public static final String CURRENCY = "EUR";

	public MoneyMother()	{
		throw new UnsupportedOperationException();
	}

	public static Money createMoneyTariff1() {
		return new Money( BigDecimal.valueOf( TARIFF1_PRICE ), CURRENCY);
	}
}
