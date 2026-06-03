package com.ssdjr2.chall.sg.inditex_prices.factory;

import com.ssdjr2.chall.sg.inditex_prices.domain.model.ApplicationDates;
import com.ssdjr2.chall.sg.inditex_prices.domain.model.Price;

public class PriceMother {

	public static final Integer PRICE_LIST_TARIFF1 = 1;
	public static final Integer PRICE_LIST_TARIFF2 = 2;
	public static final Integer PRICE_LIST_TARIFF3 = 3;
	public static final Integer PRICE_LIST_TARIFF4 = 4;
	public static final Integer PRODUCT_ID_VALID = 35455;
	public static final Integer PRODUCT_ID_INVALID = 99999;
	public static final Integer PRODUCT_ID_NEGATIVE = -5;

	private PriceMother() {
		throw new UnsupportedOperationException();
	}

	public static Price createPriceSearch( Long brandId, ApplicationDates dates ) {
		 return Price.builder()
				 .brandId(brandId)
				 .productId(PRODUCT_ID_VALID)
				 .applicationDates(dates)
				 .build();
	}

	public static Price createPriceFound() {
		return Price.builder()
						.priceList( PRICE_LIST_TARIFF1 )
						.build();
	}

	public static Price createPriceMapping() {
		return Price.builder()
				.id( 100L )
				.brandId( BrandMother.ZARA_ID )
				.productId( PRODUCT_ID_VALID )
				.priceList( PRICE_LIST_TARIFF1 )
				.priority( 0 )
				.applicationDates(
						new ApplicationDates( ApplicationDatesMother.test1(), ApplicationDatesMother.test1() ) )
				.money( MoneyMother.createMoneyTariff1() )
				.build();
	}
	public static Price createPriceMappingIncomplete() {
		return Price.builder()
				.brandId( BrandMother.ZARA_ID )
				.productId( PRODUCT_ID_VALID )
				.priceList( PRICE_LIST_TARIFF1 )
				.priority( 0 )
				.applicationDates( null )
				.money( null )
				.build();
	}
}
