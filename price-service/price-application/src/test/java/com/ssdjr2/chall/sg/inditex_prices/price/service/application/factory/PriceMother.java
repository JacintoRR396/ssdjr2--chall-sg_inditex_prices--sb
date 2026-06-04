package com.ssdjr2.chall.sg.inditex_prices.price.service.application.factory;

import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.ApplicationDates;
import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.identifier.BrandId;
import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.identifier.PriceId;
import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.identifier.ProductId;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.entity.Price;

public class PriceMother {

	public static final Integer PRICE_LIST_TARIFF1 = 1;
	public static final Integer PRICE_LIST_TARIFF2 = 2;
	public static final Integer PRICE_LIST_TARIFF3 = 3;
	public static final Integer PRICE_LIST_TARIFF4 = 4;
	public static final Integer PRODUCT_ID_VALID = 35455;
	public static final Integer PRODUCT_ID_NEGATIVE = -5;

	private PriceMother() {
		throw new UnsupportedOperationException();
	}
}
