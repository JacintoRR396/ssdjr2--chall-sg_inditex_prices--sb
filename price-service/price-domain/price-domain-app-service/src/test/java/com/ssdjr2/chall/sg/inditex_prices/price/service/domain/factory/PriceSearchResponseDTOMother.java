package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.factory;

import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.response.ApplicationDatesResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.response.MoneyResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.response.PriceSearchResponseDTO;

public class PriceSearchResponseDTOMother {

	private PriceSearchResponseDTOMother() {
		throw new UnsupportedOperationException();
	}

	public static PriceSearchResponseDTO createPriceSearchResponseDTOAboutTest1AndTest3() {
		ApplicationDatesResponseDTO applicationDatesResp =
				ApplicationDatesResponseDTOMother.createApplicationDatesResponseDTOAboutTariff1();
		MoneyResponseDTO moneyResp = MoneyResponseDTOMother.createMoneyResponseDTOAboutTariff1();
		return new PriceSearchResponseDTO(
				BrandMother.ZARA_ID, PriceMother.PRODUCT_ID_VALID,
				PriceMother.PRICE_LIST_TARIFF1,applicationDatesResp, moneyResp );
	}
}
