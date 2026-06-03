package com.ssdjr2.chall.sg.inditex_prices.persistence.mapper;

import com.ssdjr2.chall.sg.inditex_prices.domain.model.Money;
import com.ssdjr2.chall.sg.inditex_prices.factory.MoneyMother;
import com.ssdjr2.chall.sg.inditex_prices.factory.PriceEntityMother;
import com.ssdjr2.chall.sg.inditex_prices.persistence.entity.PriceEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyEntityMapperTest {

	private final MoneyEntityMapper mapper = Mappers.getMapper(MoneyEntityMapper.class);

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
		assertThat(result.getCurrency()).isEqualTo(MoneyMother.CURRENCY);
		assertThat(result.getPrice()).isEqualTo(
				Money.setScaleToPrice(new BigDecimal(MoneyMother.TARIFF1_PRICE)));
	}

	@Test
	@DisplayName("Return null Domain entity when both price and currency are null in PriceEntity")
	void givenPriceEntityWithBothFieldsNull_whenMappingToMoney_thenReturnsNull() {
		PriceEntity entity = PriceEntityMother.createPriceEntityWithDataNull();

		Money result = mapper.fromPriceEntityToMoney(entity);

		assertThat(result).isNull();
	}

	@Test
	@DisplayName("Map successfully when only currency is null in PriceEntity")
	void givenPriceEntityWithNullCurrencyOnly_whenMappingToMoney_thenReturnsMoneyWithNullCurrency() {
		PriceEntity entity = PriceEntityMother.createPriceEntityWithOnlyFirstDataNull();

		Money result = mapper.fromPriceEntityToMoney(entity);

		assertThat(result).isNotNull();
		assertThat(result.getPrice()).isEqualTo(
				Money.setScaleToPrice(new BigDecimal(MoneyMother.TARIFF2_PRICE)));
		assertThat(result.getCurrency()).isNull();
	}

	@Test
	@DisplayName("Map successfully when only price is null in PriceEntity")
	void givenPriceEntityWithNullPriceOnly_whenMappingToMoney_thenReturnsMoneyWithNullPrice() {
		PriceEntity entity = PriceEntityMother.createPriceEntityWithPriceNull();

		Money result = mapper.fromPriceEntityToMoney(entity);

		assertThat(result).isNotNull();
		assertThat(result.getPrice()).isNull();
		assertThat(result.getCurrency()).isEqualTo(MoneyMother.CURRENCY);
	}
}
