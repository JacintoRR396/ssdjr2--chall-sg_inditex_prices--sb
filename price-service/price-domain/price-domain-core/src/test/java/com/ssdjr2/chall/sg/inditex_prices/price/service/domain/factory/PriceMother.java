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

	public static Price createPriceMapping() {
		return Price.builder()
				.id( new PriceId( 100L ) )
				.brandId( new BrandId( BrandMother.ZARA_ID ) )
				.productId( new ProductId( PRODUCT_ID_VALID ) )
				.priceList( PRICE_LIST_TARIFF1 )
				.priority( 1 )
				.applicationDates(
						new ApplicationDates( ApplicationDatesMother.test1(), ApplicationDatesMother.test1() ) )
				.money( MoneyMother.createMoneyTariff1() )
				.build();
	}

	public static Price createPriceWithBrandIdNull( ) {
		return Price.builder()
				.brandId(null)
				.applicationDates(
						new ApplicationDates( ApplicationDatesMother.test1(), ApplicationDatesMother.test1() ) )
				.build();
	}

	public static Price createPriceWithProductIdNull( ) {
		return Price.builder()
				.brandId( new BrandId( BrandMother.ZARA_ID ) )
				.productId( null )
				.applicationDates(
						new ApplicationDates( ApplicationDatesMother.test1(), ApplicationDatesMother.test1() ) )
				.build();
	}

	public static Price createPriceWithPriceListNull( ) {
		return Price.builder()
				.brandId( new BrandId( BrandMother.ZARA_ID ) )
				.productId( new ProductId( PRODUCT_ID_VALID ) )
				.priceList( null )
				.applicationDates(
						new ApplicationDates( ApplicationDatesMother.test1(), ApplicationDatesMother.test1() ) )
				.build();
	}

	public static Price createPriceWithDataZero( ) {
		return Price.builder()
				.brandId(new BrandId(0L))
				.applicationDates(
						new ApplicationDates( ApplicationDatesMother.test1(), ApplicationDatesMother.test1() ) )
				.build();
	}
}
