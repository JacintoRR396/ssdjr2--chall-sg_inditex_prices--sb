package com.ssdjr2.chall.sg.inditex_prices.factory;

import com.ssdjr2.chall.sg.inditex_prices.domain.model.ApplicationDates;
import com.ssdjr2.chall.sg.inditex_prices.domain.model.Brand;
import com.ssdjr2.chall.sg.inditex_prices.domain.model.Price;
import com.ssdjr2.chall.sg.inditex_prices.persistence.entity.PriceEntity;

public class FactoryPrice {

	public static final Integer PRICE_LIST_1 = 1;
	public static final Integer PRODUCT_ID_VALID = 35455;
	public static final Integer PRODUCT_ID_INVALID = 99999;

	public static PriceEntity createPriceEntity() {
		PriceEntity priceEntity = new PriceEntity();
		priceEntity.setPriceList( PRICE_LIST_1 );

		return priceEntity;
	}

	public static Price createPriceSearch( Brand brand, ApplicationDates dates ) {
		 return Price.builder()
				 .brand(brand)
				 .productId(PRODUCT_ID_VALID)
				 .applicationDates(dates)
				 .build();
	}

	public static Price createPriceFound( Integer priceList ) {
		return Price.builder()
						.priceList( priceList )
						.build();
	}
}
