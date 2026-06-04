package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.entity;

import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.exception.PriceDomainException;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.factory.PriceMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

	@Test
	@DisplayName("GIVEN a valid Price WHEN initPrice is called THEN no exception is thrown")
	void givenValidPrice_whenInitPrice_thenSuccess() {
		Price price = PriceMother.createPriceMapping();

		assertDoesNotThrow(price::initPrice);
	}

	@Test
	@DisplayName("GIVEN Price with brand id null WHEN initPrice is called THEN throw PriceDomainException")
	void givenBrandIdNull_whenInitPrice_thenThrowException() {
		Price nullPrice = PriceMother.createPriceWithBrandIdNull();

		PriceDomainException exception = assertThrows(PriceDomainException.class,
				nullPrice::initPrice);

		assertEquals("error.brand_id.null", exception.getMessage());
	}

	@Test
	@DisplayName("GIVEN Price with product id null WHEN initPrice is called THEN throw PriceDomainException")
	void givenProductIdNull_whenInitPrice_thenThrowException() {
		Price nullPrice = PriceMother.createPriceWithProductIdNull();

		PriceDomainException exception = assertThrows(PriceDomainException.class,
				nullPrice::initPrice);

		assertEquals("error.product_id.null", exception.getMessage());
	}

	@Test
	@DisplayName("GIVEN Price with brand id value null WHEN initPrice is called THEN throw PriceDomainException")
	void givenBrandIdValueListNull_whenInitPrice_thenThrowException() {
		Price nullPrice = PriceMother.createPriceWithBrandIdValueNull();

		PriceDomainException exception = assertThrows(PriceDomainException.class,
				nullPrice::initPrice);

		assertEquals("error.price.number.null", exception.getMessage());
	}

	@Test
	@DisplayName("GIVEN Price with zero or negative fields WHEN initPrice is called THEN throw PriceDomainException")
	void givenInvalidFields_whenInitPrice_thenThrowException() {
		Price invalidPrice = PriceMother.createPriceWithDataZero();

		PriceDomainException exception = assertThrows(PriceDomainException.class,
				invalidPrice::initPrice);

		assertEquals("error.price.number.invalid_range", exception.getMessage());
	}

	@Test
	@DisplayName("GIVEN two identical Prices WHEN comparing THEN they are equal")
	void givenSamePrices_whenComparing_thenAreEqual() {
		Price price1 = PriceMother.createPriceMapping();
		Price price2 = PriceMother.createPriceMapping();

		assertEquals(price1, price2);
		assertEquals(price1.hashCode(), price2.hashCode());
	}
}
