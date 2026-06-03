package com.ssdjr2.chall.sg.inditex_prices.persistence.mapper;

import com.ssdjr2.chall.sg.inditex_prices.domain.model.Money;
import com.ssdjr2.chall.sg.inditex_prices.domain.model.Price;
import com.ssdjr2.chall.sg.inditex_prices.factory.BrandMother;
import com.ssdjr2.chall.sg.inditex_prices.factory.MoneyMother;
import com.ssdjr2.chall.sg.inditex_prices.factory.PriceEntityMother;
import com.ssdjr2.chall.sg.inditex_prices.factory.PriceMother;
import com.ssdjr2.chall.sg.inditex_prices.persistence.entity.PriceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PriceEntityMapperTest {

	private PriceEntityMapper mapper;

	@BeforeEach
	void setUp() {
		mapper = Mappers.getMapper(PriceEntityMapper.class);

		ApplicationDatesEntityMapper datesMapper = Mappers.getMapper(ApplicationDatesEntityMapper.class);
		MoneyEntityMapper moneyMapper = Mappers.getMapper(MoneyEntityMapper.class);

		ReflectionTestUtils.setField(mapper, "applicationDatesEntityMapper", datesMapper);
		ReflectionTestUtils.setField(mapper, "moneyEntityMapper", moneyMapper);
	}

	@Test
	@DisplayName("Map fully populated PriceEntity to Price Domain entity successfully")
	void givenValidPriceEntity_whenMappingToPrice_thenReturnsCompletePriceDomain() {
		PriceEntity entity = PriceEntityMother.createPriceEntity();

		Price result = mapper.fromPriceEntityToPrice(entity);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(100L);
		assertThat(result.getBrandId()).isEqualTo(BrandMother.ZARA_ID);
		assertThat(result.getProductId()).isEqualTo(PriceMother.PRODUCT_ID_VALID);
		assertThat(result.getPriceList()).isEqualTo(PriceMother.PRICE_LIST_TARIFF1);
		assertThat(result.getPriority()).isZero();
		assertThat(result.getApplicationDates()).isNotNull();
		assertThat(result.getApplicationDates().getStartDate()).isEqualTo(PriceEntityMother.START_DATE_TARIFF1);
		assertThat(result.getApplicationDates().getEndDate()).isEqualTo(PriceEntityMother.END_DATE_TARIFF1);
		assertThat(result.getMoney()).isNotNull();
		assertThat(result.getMoney().getPrice()).isEqualTo(
				Money.setScaleToPrice(BigDecimal.valueOf(MoneyMother.TARIFF1_PRICE)));
		assertThat(result.getMoney().getCurrency()).isEqualTo(MoneyMother.CURRENCY);
	}

	@Test
	@DisplayName("Return null Price Domain entity when input entity is null")
	void givenNullPriceEntity_whenMappingToPrice_thenReturnsNull() {
		Price result = mapper.fromPriceEntityToPrice(null);

		assertThat(result).isNull();
	}
}
