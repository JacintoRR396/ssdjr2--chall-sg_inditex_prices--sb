package com.ssdjr2.chall.sg.inditex_prices.price.service.application.factory;


import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.Money;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.response.MoneyResponseDTO;

import java.math.BigDecimal;

public class MoneyResponseDTOMother {

	public MoneyResponseDTOMother()	{
		throw new UnsupportedOperationException();
	}

	public static MoneyResponseDTO createMoneyResponseDTOAboutTariff1()	{
		return new MoneyResponseDTO(
				Money.setScaleToPrice( BigDecimal.valueOf( MoneyMother.TARIFF1_PRICE ) ), MoneyMother.CURRENCY );
	}

	public static MoneyResponseDTO createMoneyResponseDTOAboutTariff2()	{
		return new MoneyResponseDTO(
				Money.setScaleToPrice( BigDecimal.valueOf( MoneyMother.TARIFF2_PRICE ) ), MoneyMother.CURRENCY );
	}

	public static MoneyResponseDTO createMoneyResponseDTOAboutTariff3()	{
		return new MoneyResponseDTO(
				Money.setScaleToPrice( BigDecimal.valueOf( MoneyMother.TARIFF3_PRICE ) ), MoneyMother.CURRENCY );
	}

	public static MoneyResponseDTO createMoneyResponseDTOAboutTariff4()	{
		return new MoneyResponseDTO(
				Money.setScaleToPrice( BigDecimal.valueOf( MoneyMother.TARIFF4_PRICE ) ), MoneyMother.CURRENCY );
	}
}
