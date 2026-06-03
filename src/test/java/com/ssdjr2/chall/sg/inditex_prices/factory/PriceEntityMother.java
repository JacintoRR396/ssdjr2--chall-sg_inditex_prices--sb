package com.ssdjr2.chall.sg.inditex_prices.factory;

import com.ssdjr2.chall.sg.inditex_prices.domain.model.Money;
import com.ssdjr2.chall.sg.inditex_prices.persistence.entity.PriceEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.ssdjr2.chall.sg.inditex_prices.factory.PriceMother.PRICE_LIST_TARIFF1;

public class PriceEntityMother {

	public static final LocalDateTime START_DATE_TARIFF1 =
			LocalDateTime.of(2020, 6, 14, 0, 0, 0);
	public static final LocalDateTime END_DATE_TARIFF1 =
			LocalDateTime.of(2020, 12, 31, 23, 59, 59);

	private PriceEntityMother() {
		throw new UnsupportedOperationException();
	}

	public static PriceEntity createPriceEntity() {
		PriceEntity priceEntity = new PriceEntity();
		priceEntity.setId( 100L );
		priceEntity.setBrandId( BrandMother.ZARA_ID );
		priceEntity.setProductId( PriceMother.PRODUCT_ID_VALID );
		priceEntity.setPriceList( PRICE_LIST_TARIFF1 );
		priceEntity.setPriority( 0 );
		priceEntity.setStartDate( START_DATE_TARIFF1 );
		priceEntity.setEndDate( END_DATE_TARIFF1 );
		priceEntity.setPrice( BigDecimal.valueOf( MoneyMother.TARIFF1_PRICE ) );
		priceEntity.setCurrency( MoneyMother.CURRENCY );

		return priceEntity;
	}

	public static PriceEntity createPriceEntityWithDataNull() {
		PriceEntity entity = new PriceEntity();
		entity.setStartDate(null);
		entity.setEndDate(null);
		entity.setPrice(null);
		entity.setCurrency(null);

		return entity;
	}

	public static PriceEntity createPriceEntityWithOnlyFirstDataNull() {
		PriceEntity entity = new PriceEntity();
		entity.setStartDate( START_DATE_TARIFF1 );
		entity.setEndDate(null);
		entity.setPrice(Money.setScaleToPrice(new BigDecimal(MoneyMother.TARIFF2_PRICE)));
		entity.setCurrency(null);

		return entity;
	}

	public static PriceEntity createPriceEntityWithPriceNull() {
		PriceEntity entity = new PriceEntity();
		entity.setStartDate( START_DATE_TARIFF1 );
		entity.setEndDate( END_DATE_TARIFF1 );
		entity.setPrice( null );
		entity.setCurrency( MoneyMother.CURRENCY );

		return entity;
	}
}
