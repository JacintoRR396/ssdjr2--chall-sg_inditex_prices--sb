package com.ssdjr2.chall.sg.inditex_prices.factory;

import com.ssdjr2.chall.sg.inditex_prices.controller.dto.response.MoneyResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.domain.model.Money;

import java.math.BigDecimal;

import static com.ssdjr2.chall.sg.inditex_prices.factory.MoneyMother.*;

public class MoneyResponseDTOMother {

	public MoneyResponseDTOMother()	{
		throw new UnsupportedOperationException();
	}

	public static MoneyResponseDTO createMoneyResponseDTOAboutTariff1()	{
		return new MoneyResponseDTO(Money.setScaleToPrice( BigDecimal.valueOf( TARIFF1_PRICE ) ), CURRENCY );
	}

	public static MoneyResponseDTO createMoneyResponseDTOAboutTariff2()	{
		return new MoneyResponseDTO(Money.setScaleToPrice( BigDecimal.valueOf( TARIFF2_PRICE ) ), CURRENCY );
	}

	public static MoneyResponseDTO createMoneyResponseDTOAboutTariff3()	{
		return new MoneyResponseDTO(Money.setScaleToPrice( BigDecimal.valueOf( TARIFF3_PRICE ) ), CURRENCY );
	}

	public static MoneyResponseDTO createMoneyResponseDTOAboutTariff4()	{
		return new MoneyResponseDTO(Money.setScaleToPrice( BigDecimal.valueOf( TARIFF4_PRICE ) ), CURRENCY );
	}
}
