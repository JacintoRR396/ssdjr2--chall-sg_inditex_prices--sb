package com.ssdjr2.chall.sg.inditex_prices.price.service.application.factory;

import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.request.PriceSearchQueryDTO;

public class PriceSearchQueryDTOMother {

	private PriceSearchQueryDTOMother() {
		throw new UnsupportedOperationException();
	}

	public static PriceSearchQueryDTO createPriceSearchQueryDTOAboutTest1() {
		return new PriceSearchQueryDTO( BrandMother.ZARA_ID, PriceMother.PRODUCT_ID_VALID, ApplicationDatesMother.test1() );
	}

	public static PriceSearchQueryDTO createPriceSearchQueryDTOAboutTest2() {
		return new PriceSearchQueryDTO( BrandMother.ZARA_ID, PriceMother.PRODUCT_ID_VALID, ApplicationDatesMother.test2() );
	}

	public static PriceSearchQueryDTO createPriceSearchQueryDTOAboutTest3() {
		return new PriceSearchQueryDTO( BrandMother.ZARA_ID, PriceMother.PRODUCT_ID_VALID, ApplicationDatesMother.test3() );
	}

	public static PriceSearchQueryDTO createPriceSearchQueryDTOAboutTest4() {
		return new PriceSearchQueryDTO( BrandMother.ZARA_ID, PriceMother.PRODUCT_ID_VALID, ApplicationDatesMother.test4() );
	}

	public static PriceSearchQueryDTO createPriceSearchQueryDTOAboutTest5() {
		return new PriceSearchQueryDTO( BrandMother.ZARA_ID, PriceMother.PRODUCT_ID_VALID, ApplicationDatesMother.test5() );
	}

	public static PriceSearchQueryDTO createPriceSearchQueryDTOWhenDataIsNull() {
		return new PriceSearchQueryDTO(null, null, null);
	}

	public static PriceSearchQueryDTO createPriceSearchQueryDTOWhenDataIsInvalid() {
			return new PriceSearchQueryDTO( BrandMother.NEGATIVE_ID, PriceMother.PRODUCT_ID_NEGATIVE, ApplicationDatesMother.testInvalid() );
	}
}
