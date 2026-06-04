package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.factory;


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
}
