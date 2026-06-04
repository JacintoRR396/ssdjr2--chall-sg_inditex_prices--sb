package com.ssdjr2.chall.sg.inditex_prices.price.service.application.factory;

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

	public static PriceSearchResponseDTO createPriceSearchResponseDTOAboutTest2() {
		ApplicationDatesResponseDTO applicationDatesResp =
				ApplicationDatesResponseDTOMother.createApplicationDatesResponseDTOAboutTariff2();
		MoneyResponseDTO moneyResp = MoneyResponseDTOMother.createMoneyResponseDTOAboutTariff2();
		return new PriceSearchResponseDTO(
				BrandMother.ZARA_ID, PriceMother.PRODUCT_ID_VALID,
				PriceMother.PRICE_LIST_TARIFF2, applicationDatesResp, moneyResp );
	}

	public static PriceSearchResponseDTO createPriceSearchResponseDTOAboutTest4() {
		ApplicationDatesResponseDTO applicationDatesResp =
				ApplicationDatesResponseDTOMother.createApplicationDatesResponseDTOAboutTariff3();
		MoneyResponseDTO moneyResp = MoneyResponseDTOMother.createMoneyResponseDTOAboutTariff3();
		return new PriceSearchResponseDTO(
				BrandMother.ZARA_ID, PriceMother.PRODUCT_ID_VALID,
				PriceMother. PRICE_LIST_TARIFF3, applicationDatesResp, moneyResp );
	}

	public static PriceSearchResponseDTO createPriceSearchResponseDTOAboutTest5() {
		ApplicationDatesResponseDTO applicationDatesResp =
				ApplicationDatesResponseDTOMother.createApplicationDatesResponseDTOAboutTariff4();
		MoneyResponseDTO moneyResp = MoneyResponseDTOMother.createMoneyResponseDTOAboutTariff4();
		return new PriceSearchResponseDTO(
				BrandMother.ZARA_ID, PriceMother.PRODUCT_ID_VALID,
				PriceMother.PRICE_LIST_TARIFF4, applicationDatesResp, moneyResp );
	}
}
