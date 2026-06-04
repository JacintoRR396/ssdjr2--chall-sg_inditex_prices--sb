package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.factory;

import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.request.PriceSearchQueryDTO;

public class PriceSearchQueryDTOMother {

	private PriceSearchQueryDTOMother() {
		throw new UnsupportedOperationException();
	}

	public static PriceSearchQueryDTO createPriceSearchQueryDTOAboutTest1() {
		return new PriceSearchQueryDTO( BrandMother.ZARA_ID, PriceMother.PRODUCT_ID_VALID, ApplicationDatesMother.test1() );
	}
}
