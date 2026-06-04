package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.factory;

import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.ApplicationDates;
import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.identifier.BrandId;
import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.identifier.PriceId;
import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.identifier.ProductId;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.entity.Price;

public class PriceMother {

	public static final Integer PRICE_LIST_TARIFF1 = 1;
	public static final Integer PRODUCT_ID_VALID = 35455;

	private PriceMother() {
		throw new UnsupportedOperationException();
	}

	public static Price createPriceSearch(Long brandId, ApplicationDates dates ) {
		 return Price.builder()
				 .brandId(new BrandId(brandId))
				 .productId(new ProductId(PRODUCT_ID_VALID))
				 .applicationDates(dates)
				 .build();
	}

	public static Price createPriceMapping() {
		return Price.builder()
				.id( new PriceId( 100L ) )
				.brandId( new BrandId( BrandMother.ZARA_ID ) )
				.productId( new ProductId( PRODUCT_ID_VALID ) )
				.priceList( PRICE_LIST_TARIFF1 )
				.priority( 0 )
				.applicationDates(
						new ApplicationDates( ApplicationDatesMother.test1(), ApplicationDatesMother.test1() ) )
				.money( MoneyMother.createMoneyTariff1() )
				.build();
	}
	public static Price createPriceMappingIncomplete() {
		return Price.builder()
				.brandId( new BrandId( BrandMother.ZARA_ID ) )
				.productId( new ProductId( PRODUCT_ID_VALID ) )
				.priceList( PRICE_LIST_TARIFF1 )
				.priority( 0 )
				.applicationDates( null )
				.money( null )
				.build();
	}
}
