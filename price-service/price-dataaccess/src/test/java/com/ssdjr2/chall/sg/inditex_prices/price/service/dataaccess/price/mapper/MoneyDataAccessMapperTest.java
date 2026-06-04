package com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.mapper;

import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.Money;
import com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.entity.PriceEntity;
import com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.factory.MoneyMother;
import com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.factory.PriceEntityMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyDataAccessMapperTest {

	private final MoneyDataAccessMapper mapper = Mappers.getMapper(MoneyDataAccessMapper.class);

	@Test
	@DisplayName("Return null Domain entity when PriceEntity input is null")
	void givenNullPriceEntity_whenMappingToMoney_thenReturnsNull() {
		Money result = mapper.fromPriceEntityToMoney(null);

		assertThat(result).isNull();
	}

	@Test
	@DisplayName("Map PriceEntity to Money Domain applying correct decimal scale successfully")
	void givenPriceEntityWithUnscaledPrice_whenMappingToMoney_thenReturnsMoneyWithCorrectScale() {
		PriceEntity entity = new PriceEntity();
		entity.setPrice(BigDecimal.valueOf(MoneyMother.TARIFF1_INVALID_FORMATTER));
		entity.setCurrency(MoneyMother.CURRENCY);

		Money result = mapper.fromPriceEntityToMoney(entity);

		assertThat(result).isNotNull();
		assertThat(result.currency()).isEqualTo(MoneyMother.CURRENCY);
		assertThat(result.price()).isEqualTo(
				Money.setScaleToPrice(new BigDecimal(MoneyMother.TARIFF1_PRICE)));
	}

	@Test
	@DisplayName("Return null Domain entity when both price and currency are null in PriceEntity")
	void givenPriceEntityWithBothFieldsNull_whenMappingToMoney_thenReturnsNull() {
		PriceEntity entity = PriceEntityMother.createPriceEntityWithDataNull();

		Money result = mapper.fromPriceEntityToMoney(entity);

		assertThat(result).isNull();
	}
}
