package com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.mapper;

import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.Money;
import com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.entity.PriceEntity;
import com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.factory.BrandMother;
import com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.factory.MoneyMother;
import com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.factory.PriceEntityMother;
import com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.factory.PriceMother;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.entity.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PriceDataAccessMapperTest {

	private PriceDataAccessMapper mapper;

	@BeforeEach
	void setUp() {
		mapper = Mappers.getMapper(PriceDataAccessMapper.class);

		ApplicationDatesDataAccessMapper datesMapper = Mappers.getMapper(ApplicationDatesDataAccessMapper.class);
		MoneyDataAccessMapper moneyMapper = Mappers.getMapper(MoneyDataAccessMapper.class);

		ReflectionTestUtils.setField(mapper, "applicationDatesEntityMapper", datesMapper);
		ReflectionTestUtils.setField(mapper, "moneyEntityMapper", moneyMapper);
	}

	@Test
	@DisplayName("Map fully populated PriceEntity to Price Domain entity successfully")
	void givenValidPriceEntity_whenMappingToPrice_thenReturnsCompletePriceDomain() {
		PriceEntity entity = PriceEntityMother.createPriceEntity();

		Price result = mapper.fromPriceEntityToPrice(entity);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isNotNull();
		assertThat(result.getId().getId()).isEqualTo(100L);
		assertThat(result.getBrandId()).isNotNull();
		assertThat(result.getBrandId().getId()).isEqualTo(BrandMother.ZARA_ID);
		assertThat(result.getProductId()).isNotNull();
		assertThat(result.getProductId().getId()).isEqualTo(PriceMother.PRODUCT_ID_VALID);
		assertThat(result.getPriceList()).isEqualTo(PriceMother.PRICE_LIST_TARIFF1);
		assertThat(result.getPriority()).isZero();
		assertThat(result.getApplicationDates()).isNotNull();
		assertThat(result.getApplicationDates().startDate()).isEqualTo(PriceEntityMother.START_DATE_TARIFF1);
		assertThat(result.getApplicationDates().endDate()).isEqualTo(PriceEntityMother.END_DATE_TARIFF1);
		assertThat(result.getMoney()).isNotNull();
		assertThat(result.getMoney().price()).isEqualTo(
				Money.setScaleToPrice(BigDecimal.valueOf(MoneyMother.TARIFF1_PRICE)));
		assertThat(result.getMoney().currency()).isEqualTo(MoneyMother.CURRENCY);
	}

	@Test
	@DisplayName("Return null Price Domain entity when input entity is null")
	void givenNullPriceEntity_whenMappingToPrice_thenReturnsNull() {
		Price result = mapper.fromPriceEntityToPrice(null);

		assertThat(result).isNull();
	}
}
