package com.ssdjr2.chall.sg.inditex_prices.factory;

import com.ssdjr2.chall.sg.inditex_prices.controller.dto.request.PriceSearchQueryDTO;

import static com.ssdjr2.chall.sg.inditex_prices.factory.BrandMother.NEGATIVE_ID;
import static com.ssdjr2.chall.sg.inditex_prices.factory.PriceMother.PRODUCT_ID_NEGATIVE;
import static com.ssdjr2.chall.sg.inditex_prices.factory.PriceMother.PRODUCT_ID_VALID;

public class PriceSearchQueryDTOMother {

	private PriceSearchQueryDTOMother() {
		throw new UnsupportedOperationException();
	}

	public static PriceSearchQueryDTO createPriceSearchQueryDTOAboutTest1() {
		return new PriceSearchQueryDTO( BrandMother.ZARA_ID, PRODUCT_ID_VALID, ApplicationDatesMother.test1() );
	}

	public static PriceSearchQueryDTO createPriceSearchQueryDTOAboutTest2() {
		return new PriceSearchQueryDTO( BrandMother.ZARA_ID, PRODUCT_ID_VALID, ApplicationDatesMother.test2() );
	}

	public static PriceSearchQueryDTO createPriceSearchQueryDTOAboutTest3() {
		return new PriceSearchQueryDTO( BrandMother.ZARA_ID, PRODUCT_ID_VALID, ApplicationDatesMother.test3() );
	}

	public static PriceSearchQueryDTO createPriceSearchQueryDTOAboutTest4() {
		return new PriceSearchQueryDTO( BrandMother.ZARA_ID, PRODUCT_ID_VALID, ApplicationDatesMother.test4() );
	}

	public static PriceSearchQueryDTO createPriceSearchQueryDTOAboutTest5() {
		return new PriceSearchQueryDTO( BrandMother.ZARA_ID, PRODUCT_ID_VALID, ApplicationDatesMother.test5() );
	}

	public static PriceSearchQueryDTO createPriceSearchQueryDTOWhenDataIsNull() {
		return new PriceSearchQueryDTO(null, null, null);
	}

	public static PriceSearchQueryDTO createPriceSearchQueryDTOWhenDataIsInvalid() {
			return new PriceSearchQueryDTO( NEGATIVE_ID, PRODUCT_ID_NEGATIVE, ApplicationDatesMother.testInvalid() );
	}
}
